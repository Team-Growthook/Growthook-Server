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
        String message = userName +"님은 " + totalMember.toString() + "번째 쑥쑥이에서 곰이 됐어요.";
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
