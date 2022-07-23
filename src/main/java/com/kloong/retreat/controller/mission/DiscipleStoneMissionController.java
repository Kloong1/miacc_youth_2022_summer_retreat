package com.kloong.retreat.controller.mission;

import com.kloong.retreat.log.MissionLogger;
import com.kloong.retreat.password.MissionPasswordChecker;
import com.kloong.retreat.upload.FileUploadManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/missions/disciple-stone")
public class DiscipleStoneMissionController extends StoneMissionController{

    public DiscipleStoneMissionController(MissionPasswordChecker missionPasswordChecker, MissionLogger missionLogger, FileUploadManager fileUploadManager) {
        super("html/missions", "disciple");

        this.missionPasswordChecker = missionPasswordChecker;
        this.missionLogger = missionLogger;
        this.fileUploadManager = fileUploadManager;
    }
}
