package com.kloong.retreat.controller;

import com.kloong.retreat.log.MissionLogger;
import com.kloong.retreat.password.MissionPasswordChecker;
import com.kloong.retreat.upload.FileUploadManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/missions")
public class MissionController {

    private final static String MISSION_DIR_PREFIX = "html/missions";

    private final MissionPasswordChecker missionPasswordChecker;
    private final MissionLogger missionLogger;
    private final FileUploadManager fileUploadManager;

    public MissionController(MissionPasswordChecker missionPasswordChecker, MissionLogger missionLogger, FileUploadManager fileUploadManager) {
        this.missionPasswordChecker = missionPasswordChecker;
        this.missionLogger = missionLogger;
        this.fileUploadManager = fileUploadManager;
    }

    @GetMapping("/{stone}/1")
    public String firstMission(@PathVariable String stone) {
        return MISSION_DIR_PREFIX + "/" + stone.substring(0, stone.indexOf("-")) + "/1";
    }

    @PostMapping("/{stone}/{missionNumber}")
    public String missions(@PathVariable String stone, @PathVariable int missionNumber, @RequestParam String password, Model model) {
        String currentMission = stone + (missionNumber - 1);

        if (missionPasswordChecker.checkPassword(currentMission, password)) {
            missionLogger.log(stone, missionNumber - 1, password, true);
            return MISSION_DIR_PREFIX + "/" + stone.substring(0, stone.indexOf("-")) + "/" + missionNumber;
        }

        missionLogger.log(stone, missionNumber - 1, password, false);
        model.addAttribute("result", "Error!");
        model.addAttribute("message", "잘못된 암호입니다.");
        return MISSION_DIR_PREFIX + "/result";
    }

    @PostMapping("/{stone}/{missionNumber}/upload")
    public String uploadImage(HttpServletRequest request, @RequestParam MultipartFile image, @PathVariable String stone, @PathVariable int missionNumber, Model model) {
        if (image.isEmpty()) {
            model.addAttribute("result", "Error!");
            model.addAttribute("message", "잘못된 이미지 파일입니다.");
            return MISSION_DIR_PREFIX + "/result";
        }

        File uploadedFile = fileUploadManager.upload(image);
        String requestHostAndPort = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        missionLogger.log(stone, missionNumber, uploadedFile.getName(), requestHostAndPort);

        model.addAttribute("result", "Success!");
        model.addAttribute("message", "이미지 업로드 성공! 스톤 가디언즈의 연락을 기다리세요.");

        return MISSION_DIR_PREFIX + "/result";
    }
}
