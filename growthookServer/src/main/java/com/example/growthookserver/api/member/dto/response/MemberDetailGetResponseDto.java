package com.example.growthookserver.api.member.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class MemberDetailGetResponseDto {
  private String nickname;
  private String email;
  private String profileImage;
}
