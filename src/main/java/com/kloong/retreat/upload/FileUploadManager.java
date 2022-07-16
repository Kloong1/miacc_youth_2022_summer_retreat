package com.kloong.retreat.upload;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUploadManager {

    private static long id = 0;

    private final static String UPLOAD_DIR_PATH = "/home/ubuntu/upload";

    public File upload(MultipartFile file, String... dirs) {
        String fileName = ++id + "_" + file.getOriginalFilename();

        String path = UPLOAD_DIR_PATH + File.separator +
                String.join(File.separator, dirs) + File.separator +
                fileName;

        File uploadedFile = new File(path);
        try {
            file.transferTo(uploadedFile);
        } catch (IOException e) {
            --id;
            throw new RuntimeException(e);
        }

        return uploadedFile;
    }
}
