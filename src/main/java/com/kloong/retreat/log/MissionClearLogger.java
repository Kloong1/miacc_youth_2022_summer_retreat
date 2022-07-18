package com.kloong.retreat.log;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class MissionClearLogger {
    private final static String WEBHOOKS_FILE_PATH = "data/webhooks.txt";

    private final Map<String, String> webhookMap;
    private final Map<String, String> stoneMissionMap;

    public MissionClearLogger() {
        webhookMap = new HashMap<>();
        stoneMissionMap = new HashMap<>();
        initWebhookMap();
        initStoneMissionMap();
    }


    public void log(String stone, int missionNumber, String password, boolean clear) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        String messageBody = getMessageBody(stone, missionNumber, password, clear);

        HttpEntity<String> httpEntity = new HttpEntity<>(messageBody, httpHeaders);

        restTemplate.postForEntity(webhookMap.get(stone), httpEntity, String.class);
    }

    private String getMessageBody(String stone, int missionNumber, String password, boolean clear) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{\"text\":\"");

        stringBuilder.append(stoneMissionMap.get(stone)).append("-");
        stringBuilder.append("미션").append(missionNumber).append("-");

        if (clear) {
            stringBuilder.append("클리어");
        }
        else {
            stringBuilder.append("실패-");
            stringBuilder.append("입력한 암호:[").append(password).append("]");
        }

        stringBuilder.append("\"}");

        return stringBuilder.toString();
    }

    private void initWebhookMap() {
        File webhooksFile = new File(WEBHOOKS_FILE_PATH);

        if (!webhooksFile.exists()) {
            throw new RuntimeException("Webhook 파일이 존재하지 않습니다. - " + webhooksFile.getAbsolutePath());
        }

        try (FileReader fr = new FileReader(webhooksFile);
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] stoneAndWebhook = line.split(";");
                webhookMap.put(stoneAndWebhook[0], stoneAndWebhook[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException("잘못된 Webhook 파일입니다. - " + webhooksFile.getAbsolutePath());
        }
    }

    private void initStoneMissionMap() {
        stoneMissionMap.put("disciple", "[디사이플 스톤 | 광화문]");
        stoneMissionMap.put("vision", "[비전 스톤 | 배재학당]");
        stoneMissionMap.put("truth", "[트루 스톤 | 연세대]");
        stoneMissionMap.put("agape", "[아가페 스톤 | 양화진]");
    }
}
