package com.kloong.retreat.upload;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class FileUploadManager {

    private static long id = 0;

//    public final static String UPLOAD_DIR_PATH = "/home/ubuntu/upload";
    public final static String UPLOAD_DIR_PATH = "/Users/kloong/temp";

    public File upload(MultipartFile file) {
        String fileName = ++id + "_" + file.getOriginalFilename();
        String path = UPLOAD_DIR_PATH + File.separator + fileName;
        File newFile = new File(path);

        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            --id;
            throw new RuntimeException(e);
        }

        return newFile;
    }
}
