package com.example.growthookserver.common.config.jwt;

import com.example.growthookserver.common.response.ApiResponse;
import com.example.growthookserver.common.response.ErrorStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException {
    setResponse(response, HttpStatus.UNAUTHORIZED, ErrorStatus.UNAUTHORIZED_TOKEN);
  }


  public void setResponse(HttpServletResponse response, HttpStatus statusCode, ErrorStatus status) throws IOException {
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    ApiResponse apiResponse = ApiResponse.fail(statusCode.value(), status.getMessage());
    response.getWriter().println(mapper.writeValueAsString(apiResponse));
  }

}
