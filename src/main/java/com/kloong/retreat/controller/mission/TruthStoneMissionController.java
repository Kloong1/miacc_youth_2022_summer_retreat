package com.kloong.retreat.controller.mission;

import com.kloong.retreat.log.MissionLogger;
import com.kloong.retreat.password.MissionPasswordChecker;
import com.kloong.retreat.upload.FileUploadManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/missions/truth-stone")
public class TruthStoneMissionController extends StoneMissionController{

    public TruthStoneMissionController(MissionPasswordChecker missionPasswordChecker, MissionLogger missionLogger, FileUploadManager fileUploadManager) {
        super("html/missions", "truth");

        this.missionPasswordChecker = missionPasswordChecker;
        this.missionLogger = missionLogger;
        this.fileUploadManager = fileUploadManager;
    }
}
