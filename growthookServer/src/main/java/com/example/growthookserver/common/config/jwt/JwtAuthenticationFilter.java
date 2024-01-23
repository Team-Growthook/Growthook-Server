package com.example.growthookserver.common.config.jwt;

import com.example.growthookserver.common.exception.UnAuthorizedException;
import com.example.growthookserver.common.response.ErrorStatus;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  private static final String ISSUE_TOKEN_API_URL = "/api/v1/auth/token";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws ServletException, IOException {
    try {
      String accessToken = jwtTokenProvider.resolveToken(request);
      if (ISSUE_TOKEN_API_URL.equals(request.getRequestURI())) {
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);

        if (jwtTokenProvider.validateToken(refreshToken) == JwtExceptionType.EMPTY_JWT || jwtTokenProvider.validateToken(accessToken) == JwtExceptionType.EMPTY_JWT) {
          jwtAuthenticationEntryPoint.setResponse(response, HttpStatus.BAD_REQUEST, ErrorStatus.NO_TOKEN);
          return;
        } else if (jwtTokenProvider.validateToken(accessToken) == JwtExceptionType.EXPIRED_JWT_TOKEN) {
          if (jwtTokenProvider.validateToken(refreshToken) == JwtExceptionType.EXPIRED_JWT_TOKEN) {
            // access, refresh 둘 다 만료
            jwtAuthenticationEntryPoint.setResponse(response, HttpStatus.UNAUTHORIZED, ErrorStatus.SIGNIN_REQUIRED);
            return;
          } else if (jwtTokenProvider.validateToken(refreshToken) == JwtExceptionType.VALID_JWT_TOKEN) {
            // 토큰 재발급
            Long memberId = jwtTokenProvider.validateMemberRefreshToken(refreshToken);

            String newAccessToken = jwtTokenProvider.generateAccessToken(memberId);

            setAuthentication(newAccessToken);
            request.setAttribute("newAccessToken", newAccessToken);
          }
        } else if (jwtTokenProvider.validateToken(accessToken) == JwtExceptionType.VALID_JWT_TOKEN) {
          jwtAuthenticationEntryPoint.setResponse(response, HttpStatus.UNAUTHORIZED, ErrorStatus.VALID_ACCESS_TOKEN);
          return;
        } else {
          throw new UnAuthorizedException(ErrorStatus.UNAUTHORIZED_TOKEN.getMessage());
        }
      }
      else {
        JwtExceptionType jwtException = jwtTokenProvider.validateToken(accessToken);

        if (accessToken != null) {
          // 토큰 검증
          if (jwtException == JwtExceptionType.VALID_JWT_TOKEN) {
            setAuthentication(accessToken);
          }
        }
      }
    } catch (Exception e) {
      throw new UnAuthorizedException(ErrorStatus.UNAUTHORIZED_TOKEN.getMessage());
    }

    chain.doFilter(request, response);
  }

  private void setAuthentication(String token) {
    Claims claims = jwtTokenProvider.getAccessTokenPayload(token);
    Authentication authentication = new UserAuthentication(Long.valueOf(String.valueOf(claims.get("id"))), null, null);
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
