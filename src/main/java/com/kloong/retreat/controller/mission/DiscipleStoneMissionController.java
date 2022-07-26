package com.kloong.retreat.controller.mission;

import com.kloong.retreat.log.MissionLogger;
import com.kloong.retreat.password.MissionPasswordChecker;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/missions/disciple-stone")
public class DiscipleStoneMissionController extends StoneMissionController{

    public DiscipleStoneMissionController(String missionViewDir, String stoneName, MissionPasswordChecker missionPasswordChecker, MissionLogger missionLogger) {
        super(missionViewDir, stoneName, missionPasswordChecker, missionLogger);
    }
}
