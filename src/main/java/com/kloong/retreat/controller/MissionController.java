package com.kloong.retreat.controller;

import com.kloong.retreat.log.MissionClearLogger;
import com.kloong.retreat.password.MissionPasswordChecker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/missions")
public class MissionController {

    private final static String MISSION_DIR_PREFIX = "html/missions/";

    private final MissionPasswordChecker missionPasswordChecker;
    private final MissionClearLogger missionClearLogger;

    public MissionController(MissionPasswordChecker missionPasswordChecker, MissionClearLogger missionClearLogger) {
        this.missionPasswordChecker = missionPasswordChecker;
        this.missionClearLogger = missionClearLogger;
    }

    @GetMapping("/{stone}/1")
    public String firstMission(@PathVariable String stone) {
        return MISSION_DIR_PREFIX + stone + "/1";
    }

    @PostMapping("/{stone}/{number}")
    public String mission(@PathVariable String stone, @PathVariable int number, @RequestParam String password) {
        String currentMission = stone + (number - 1);

        if (missionPasswordChecker.checkPassword(currentMission, password)) {
            missionClearLogger.log(currentMission, password, true);
            return MISSION_DIR_PREFIX + stone + "/" + number;
        }

        missionClearLogger.log(currentMission, password, false);
        return MISSION_DIR_PREFIX + "/wrongPassword";
    }
}
