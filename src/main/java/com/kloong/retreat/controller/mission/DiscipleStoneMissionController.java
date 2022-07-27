package com.kloong.retreat.controller.mission;

import com.kloong.retreat.log.StoneMissionLogger;
import com.kloong.retreat.password.StoneMissionPasswordChecker;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/missions/disciple-stone")
public class DiscipleStoneMissionController extends StoneMissionController{

    public DiscipleStoneMissionController(String missionViewDir, String stoneName, StoneMissionPasswordChecker stoneMissionPasswordChecker, StoneMissionLogger stoneMissionLogger) {
        super(missionViewDir, stoneName, stoneMissionPasswordChecker, stoneMissionLogger);
    }
}
