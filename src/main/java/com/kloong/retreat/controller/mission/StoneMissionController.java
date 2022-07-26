package com.kloong.retreat.controller.mission;

import com.kloong.retreat.log.MissionLogger;
import com.kloong.retreat.password.MissionPasswordChecker;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

abstract public class StoneMissionController {

    protected final String missionViewDir;
    protected final String stoneName;

    protected final MissionPasswordChecker missionPasswordChecker;
    protected final MissionLogger missionLogger;

    public StoneMissionController(String missionViewDir, String stoneName, MissionPasswordChecker missionPasswordChecker, MissionLogger missionLogger) {
        this.missionViewDir = missionViewDir;
        this.stoneName = stoneName;
        this.missionPasswordChecker = missionPasswordChecker;
        this.missionLogger = missionLogger;
    }

    @GetMapping
    public String info() {
        return missionViewDir + "/" + stoneName + "/info";
    }

    @GetMapping("/1")
    public String firstMission() {
        return missionViewDir + "/" + stoneName + "/mission1";
    }

    @GetMapping("/{missionNumber}")
    public String missions(@PathVariable int missionNumber, @RequestParam String password, Model model) {

        if (missionPasswordChecker.checkPassword(stoneName, missionNumber - 1, password)) {
            missionLogger.log(stoneName, missionNumber - 1, password, true);
            return missionViewDir + "/" + stoneName + "/mission" + missionNumber;
        }

        missionLogger.log(stoneName, missionNumber - 1, password, false);
        model.addAttribute("result", "Error!");
        model.addAttribute("message", "잘못된 암호입니다.");
        return missionViewDir + "/result";
    }

}
