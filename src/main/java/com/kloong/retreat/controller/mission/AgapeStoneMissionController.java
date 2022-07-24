package com.kloong.retreat.controller.mission;

import com.kloong.retreat.log.MissionLogger;
import com.kloong.retreat.password.MissionPasswordChecker;
import com.kloong.retreat.upload.FileUploadManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/missions/agape-stone")
public class AgapeStoneMissionController extends StoneMissionController{

    public AgapeStoneMissionController(MissionPasswordChecker missionPasswordChecker, MissionLogger missionLogger, FileUploadManager fileUploadManager) {
        super("html/missions", "agape");

        this.missionPasswordChecker = missionPasswordChecker;
        this.missionLogger = missionLogger;
        this.fileUploadManager = fileUploadManager;
    }
}
