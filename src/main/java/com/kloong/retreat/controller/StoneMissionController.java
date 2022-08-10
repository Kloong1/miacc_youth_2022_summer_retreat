package com.kloong.retreat.controller;

import com.kloong.retreat.log.StoneMissionLogger;
import com.kloong.retreat.password.StoneMissionPasswordChecker;
import com.kloong.retreat.stone.Stones;
import com.kloong.retreat.upload.FileUploadManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/missions")
public class StoneMissionController {

    private final static String MISSION_VIEW_DIR = "html/missions";

    private final StoneMissionPasswordChecker stoneMissionPasswordChecker;
    private final StoneMissionLogger stoneMissionLogger;
    private final FileUploadManager fileUploadManager;

    private final Stones stones;

    public StoneMissionController(StoneMissionPasswordChecker stoneMissionPasswordChecker, StoneMissionLogger stoneMissionLogger, FileUploadManager fileUploadManager, Stones stones) {
        this.stoneMissionPasswordChecker = stoneMissionPasswordChecker;
        this.stoneMissionLogger = stoneMissionLogger;
        this.fileUploadManager = fileUploadManager;
        this.stones = stones;
    }

    @GetMapping("/{stoneURI}")
    public String info(@PathVariable String stoneURI) {
        if (!isValidStoneURI(stoneURI)) {
            return "html/error/4xx";
        }
        return MISSION_VIEW_DIR + "/" + stones.getName(stoneURI)+ "/info";
    }

    @GetMapping("/{stoneURI}/1")
    public String firstMission(@PathVariable String stoneURI) {
        if (!isValidStoneURI(stoneURI)) {
            return "html/error/4xx";
        }
        return MISSION_VIEW_DIR + "/" + stones.getName(stoneURI)+ "/mission1";
    }

    @GetMapping("/{stoneURI}/{missionNumber}")
    public String missions(@PathVariable String stoneURI, @PathVariable int missionNumber, @RequestParam String password, Model model) {
        if (!isValidStoneURI(stoneURI)) {
            return "html/error/4xx";
        }

        String stoneName = stones.getName(stoneURI);

        if (stoneMissionPasswordChecker.checkPassword(stoneName, missionNumber - 1, password)) {
            stoneMissionLogger.log(stoneName, missionNumber - 1, password, true);
            return MISSION_VIEW_DIR + "/" + stoneName + "/mission" + missionNumber;
        }

        stoneMissionLogger.log(stoneName, missionNumber - 1, password, false);
        return "html/error/password";
    }

    @PostMapping("/{stoneURI}/{missionNumber}/upload")
    public String uploadImage(HttpServletRequest request, @RequestParam MultipartFile image, @PathVariable String stoneURI, @PathVariable int missionNumber, Model model) {

        if (!isValidStoneURI(stoneURI)) {
            return "html/error/4xx";
        }

        if (image.isEmpty()) {
            return "html/error/4xx";
        }

        String stoneName = stones.getName(stoneURI);

        File uploadedFile = fileUploadManager.upload(image);
        stoneMissionLogger.log(stoneName, missionNumber, uploadedFile.getName(), extractHostAndPort(request));

        return MISSION_VIEW_DIR + "/uploadResult";
    }

    private String extractHostAndPort(HttpServletRequest request) {
        return request.getRequestURL().toString().replace(request.getRequestURI(), "");
    }

    private boolean isValidStoneURI(String stoneURI) {
        return stones.containsURI(stoneURI);
    }

}
