package com.kloong.retreat.controller;

import com.kloong.retreat.log.MissionClearLogger;
import com.kloong.retreat.password.MissionPasswordChecker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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

    @PostMapping("/{stone}/{missionNumber}")
    public String mission(@PathVariable String stone, @PathVariable int missionNumber, @RequestParam String password) {
        String currentMission = stone + (missionNumber - 1);

        if (missionPasswordChecker.checkPassword(currentMission, password)) {
            missionClearLogger.log(stone, missionNumber - 1, password, true);
            return MISSION_DIR_PREFIX + stone + "/" + missionNumber;
        }

        missionClearLogger.log(stone, missionNumber - 1, password, false);
        return MISSION_DIR_PREFIX + "wrongPassword";
    }

    @PostMapping("/{stone}/{number}/img")
    public String uploadImage(@RequestParam MultipartFile image, @PathVariable String stone, @PathVariable String number) {
        if (!image.isEmpty()) {
            try {
                File upload = new File("/Users/kloong/Downloads/image.png");
                System.out.println(image.getOriginalFilename());
                System.out.println(image.getSize());
                System.out.println(upload.getAbsolutePath());
                image.transferTo(upload);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return "/html/missions/wrongPassword";
    }
}
