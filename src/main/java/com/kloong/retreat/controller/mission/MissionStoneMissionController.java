package com.kloong.retreat.controller.mission;

import com.kloong.retreat.log.StoneMissionLogger;
import com.kloong.retreat.password.StoneMissionPasswordChecker;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/missions/mission-stone")
public class MissionStoneMissionController extends StoneMissionController {

    public MissionStoneMissionController(String missionViewDir, String stoneName, StoneMissionPasswordChecker stoneMissionPasswordChecker, StoneMissionLogger stoneMissionLogger) {
        super(missionViewDir, stoneName, stoneMissionPasswordChecker, stoneMissionLogger);
    }
}
