package com.example.growthookserver.external.slack.service;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class SlackService {
    @Value(value = "${slack.token}")
    String slackToken;

    public void sendSlackMessage(String userName, Long totalMember, String channel) {
        String message = userName +"님이 새로 가입 했습니다. 현재 총 인원은 " + totalMember.toString() + "명 입니다.";
        try {
            MethodsClient methods = Slack.getInstance().methods(slackToken);

            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel(channel)
                    .text(message)
                    .build();
            methods.chatPostMessage(request);
        } catch (SlackApiException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
