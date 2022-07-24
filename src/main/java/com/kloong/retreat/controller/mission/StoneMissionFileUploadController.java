package com.kloong.retreat.controller.mission;

import com.kloong.retreat.log.MissionLogger;
import com.kloong.retreat.upload.FileUploadManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Controller
public class StoneMissionFileUploadController {

    private final MissionLogger missionLogger;
    private final FileUploadManager fileUploadManager;

    private final static Set<String> stoneSet = new HashSet<>();

    private final static String MISSION_VIEW_DIR = "html/missions";

    public StoneMissionFileUploadController(MissionLogger missionLogger, FileUploadManager fileUploadManager) {
        this.missionLogger = missionLogger;
        this.fileUploadManager = fileUploadManager;

        initStoneSet();
    }

    private void initStoneSet() {
        stoneSet.add("disciple-stone");
        stoneSet.add("truth-stone");
        stoneSet.add("mission-stone");
        stoneSet.add("agape-stone");
    }

    @PostMapping("/missions/{stone}/{missionNumber}/upload")
    public String uploadImage(HttpServletRequest request, @RequestParam MultipartFile image, @PathVariable String stone, @PathVariable int missionNumber, Model model) {

        if (!stoneSet.contains(stone) || image.isEmpty()) {
            model.addAttribute("result", "Error!");
            model.addAttribute("message", "잘못된 접근입니다.");
            return MISSION_VIEW_DIR + "/result";
        }

        stone = stone.substring(0, stone.indexOf("-"));

        File uploadedFile = fileUploadManager.upload(image);
        String requestHostAndPort = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        missionLogger.log(stone, missionNumber, uploadedFile.getName(), requestHostAndPort);

        model.addAttribute("result", "Success!");
        model.addAttribute("message", "이미지 업로드 성공! 스톤 가디언즈의 연락을 기다리세요.");

        return MISSION_VIEW_DIR + "/result";
    }
}
