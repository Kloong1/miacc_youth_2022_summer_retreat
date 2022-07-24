package com.kloong.retreat.controller.mission;

import com.kloong.retreat.log.MissionLogger;
import com.kloong.retreat.password.MissionPasswordChecker;
import com.kloong.retreat.upload.FileUploadManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

abstract public class StoneMissionController {

    protected String missionViewDir;
    protected String stone;

    protected MissionPasswordChecker missionPasswordChecker;
    protected MissionLogger missionLogger;
    protected FileUploadManager fileUploadManager;

    public StoneMissionController(String missionViewDir, String stone) {
        this.missionViewDir = missionViewDir;
        this.stone = stone;
    }

    @GetMapping
    public String info() {
        return missionViewDir + "/" + stone + "/info";
    }

    @GetMapping("/1")
    public String firstMission() {
        return missionViewDir + "/" + stone + "/mission1";
    }

    @GetMapping("/{missionNumber}")
    public String missions(@PathVariable int missionNumber, @RequestParam String password, Model model) {
        String currentMission = stone + (missionNumber - 1);

        if (missionPasswordChecker.checkPassword(currentMission, password)) {
            missionLogger.log(stone, missionNumber - 1, password, true);
            return missionViewDir + "/" + stone + "/mission" + missionNumber;
        }

        missionLogger.log(stone, missionNumber - 1, password, false);
        model.addAttribute("result", "Error!");
        model.addAttribute("message", "잘못된 암호입니다.");
        return missionViewDir + "/result";
    }

}
