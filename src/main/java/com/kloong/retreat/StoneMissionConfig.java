package com.kloong.retreat;

import com.kloong.retreat.controller.mission.*;
import com.kloong.retreat.log.MissionLogger;
import com.kloong.retreat.password.MissionPasswordChecker;
import com.kloong.retreat.stone.Stone;
import com.kloong.retreat.stone.Stones;
import com.kloong.retreat.upload.FileUploadManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

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
    public StoneMissionController agapeStoneMissionController(MissionPasswordChecker missionPasswordChecker, MissionLogger missionLogger) {
        return new AgapeStoneMissionController(missionViewDir, "agape", missionPasswordChecker, missionLogger);
    }

    @Bean
    public StoneMissionController discipleStoneMissionController(MissionPasswordChecker missionPasswordChecker, MissionLogger missionLogger) {
        return new DiscipleStoneMissionController(missionViewDir, "disciple", missionPasswordChecker, missionLogger);
    }

    @Bean
    public StoneMissionController missionStoneMissionController(MissionPasswordChecker missionPasswordChecker, MissionLogger missionLogger) {
        return new MissionStoneMissionController(missionViewDir, "mission", missionPasswordChecker, missionLogger);
    }

    @Bean
    public StoneMissionController truthStoneMissionController(MissionPasswordChecker missionPasswordChecker, MissionLogger missionLogger) {
        return new TruthStoneMissionController(missionViewDir, "truth", missionPasswordChecker, missionLogger);
    }

    /*
     * StoneMissionFileUploadController
     */
    @Bean
    public StoneMissionFileUploadController stoneMissionFileUploadController(MissionLogger missionLogger, FileUploadManager fileUploadManager) {
        return new StoneMissionFileUploadController(missionViewDir, missionLogger, fileUploadManager, stones());
    }
}
