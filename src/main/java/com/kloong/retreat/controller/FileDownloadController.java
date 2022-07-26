package com.kloong.retreat.controller;

import com.kloong.retreat.upload.FileUploadManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/files")
public class FileDownloadController {

    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> file(@PathVariable String fileName) {
        String filePath = FileUploadManager.UPLOAD_DIR_PATH + File.separator + fileName;
        File file = new File(filePath);

        if (!file.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<>(fileInputStream.readAllBytes(), httpHeaders, HttpStatus.OK);
        } catch (IOException ignored) {}

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
