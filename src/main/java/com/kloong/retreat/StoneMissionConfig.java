package com.kloong.retreat;

import com.kloong.retreat.controller.mission.*;
import com.kloong.retreat.log.StoneMissionLogger;
import com.kloong.retreat.password.StoneMissionPasswordChecker;
import com.kloong.retreat.stone.Stone;
import com.kloong.retreat.stone.Stones;
import com.kloong.retreat.upload.FileUploadManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class StoneMissionConfig {

    private final static String missionViewDir = "html/missions";

    @Bean
    public Stones stones() {
        List<Stone> stoneList = new ArrayList<>();
        stoneList.add(new Stone("agape"));
        stoneList.add(new Stone("disciple"));
        stoneList.add(new Stone("mission"));
        stoneList.add(new Stone("truth"));

        return new Stones(stoneList);
    }

    /*
     * StoneMissionController - agape, disciple, mission, truth
     */
    @Bean
    public StoneMissionController agapeStoneMissionController(StoneMissionPasswordChecker stoneMissionPasswordChecker, StoneMissionLogger stoneMissionLogger) {
        return new AgapeStoneMissionController(missionViewDir, "agape", stoneMissionPasswordChecker, stoneMissionLogger);
    }

    @Bean
    public StoneMissionController discipleStoneMissionController(StoneMissionPasswordChecker stoneMissionPasswordChecker, StoneMissionLogger stoneMissionLogger) {
        return new DiscipleStoneMissionController(missionViewDir, "disciple", stoneMissionPasswordChecker, stoneMissionLogger);
    }

    @Bean
    public StoneMissionController missionStoneMissionController(StoneMissionPasswordChecker stoneMissionPasswordChecker, StoneMissionLogger stoneMissionLogger) {
        return new MissionStoneMissionController(missionViewDir, "mission", stoneMissionPasswordChecker, stoneMissionLogger);
    }

    @Bean
    public StoneMissionController truthStoneMissionController(StoneMissionPasswordChecker stoneMissionPasswordChecker, StoneMissionLogger stoneMissionLogger) {
        return new TruthStoneMissionController(missionViewDir, "truth", stoneMissionPasswordChecker, stoneMissionLogger);
    }

    /*
     * StoneMissionFileUploadController
     */
    @Bean
    public StoneMissionFileUploadController stoneMissionFileUploadController(StoneMissionLogger stoneMissionLogger, FileUploadManager fileUploadManager) {
        return new StoneMissionFileUploadController(missionViewDir, stoneMissionLogger, fileUploadManager, stones());
    }

    /**
     * stoneMissionLogger
     */
    @Bean
    public StoneMissionLogger stoneMissionLogger() {
        Map<String, String> missionInfoMap = new HashMap<>();

        missionInfoMap.put("disciple", "[디사이플 스톤 | 광화문]");
        missionInfoMap.put("mission", "[미션 스톤 | 배재학당]");
        missionInfoMap.put("truth", "[트루 스톤 | 연세대]");
        missionInfoMap.put("agape", "[아가페 스톤 | 양화진]");

        return new StoneMissionLogger(missionInfoMap);
    }
}
