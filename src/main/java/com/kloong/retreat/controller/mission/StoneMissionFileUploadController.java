package com.kloong.retreat.controller.mission;

import com.kloong.retreat.log.StoneMissionLogger;
import com.kloong.retreat.stone.Stones;
import com.kloong.retreat.upload.FileUploadManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RequestMapping
public class StoneMissionFileUploadController {

    private final StoneMissionLogger stoneMissionLogger;
    private final FileUploadManager fileUploadManager;
    private final Stones stones;
    private final String missionViewDir;

    public StoneMissionFileUploadController(String missionViewDir, StoneMissionLogger stoneMissionLogger, FileUploadManager fileUploadManager, Stones stones) {
        this.missionViewDir = missionViewDir;
        this.stoneMissionLogger = stoneMissionLogger;
        this.fileUploadManager = fileUploadManager;
        this.stones = stones;
    }

    @PostMapping("/missions/{stoneURI}/{missionNumber}/upload")
    public String uploadImage(HttpServletRequest request, @RequestParam MultipartFile image, @PathVariable String stoneURI, @PathVariable int missionNumber, Model model) {

        if (!stones.containsURI(stoneURI) || image.isEmpty()) {
            model.addAttribute("result", "Error!");
            model.addAttribute("message", "잘못된 접근입니다.");
            return missionViewDir + "/result";
        }

        String stoneName = stones.getName(stoneURI);

        File uploadedFile = fileUploadManager.upload(image);
        stoneMissionLogger.log(stoneName, missionNumber, uploadedFile.getName(), extractHostAndPort(request));

        model.addAttribute("result", "Success!");
        model.addAttribute("message", "이미지 업로드 성공! 스톤 가디언즈의 연락을 기다리세요.");

        return missionViewDir + "/result";
    }

    private String extractHostAndPort(HttpServletRequest request) {
        return request.getRequestURL().toString().replace(request.getRequestURI(), "");
    }
}
