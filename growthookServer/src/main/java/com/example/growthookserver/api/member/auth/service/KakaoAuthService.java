package com.example.growthookserver.api.member.auth.service;

import com.example.growthookserver.api.member.auth.dto.SocialInfoDto;
import com.example.growthookserver.common.exception.BaseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class KakaoAuthService  {

    //이 login은 카카오 서버에 AccessToken으로 접속할때의 login
    public SocialInfoDto login(String socialAccessToken) {
        return getKakaoSocialData(socialAccessToken);
    }

    private SocialInfoDto getKakaoSocialData(String socialAccessToken) {

        try{
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", socialAccessToken);
            HttpEntity<JsonNode> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<String> responseData = restTemplate.postForEntity("https://kapi.kakao.com/v2/user/me", httpEntity, String.class);
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(responseData.getBody());

            String nickname = jsonNode.get("kakao_account").get("profile").get("nickname").asText();
            String email = jsonNode.get("email").asText();
            String kakaoId = jsonNode.get("id").asText();

            return new SocialInfoDto(kakaoId, nickname, email);
        } catch (JsonProcessingException e) {
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "카카오 계정 데이터 가공 실패");
        }

    }

}