package com.kads.demo.controller;

import com.kads.demo.message.ResponseMessage;
import com.kads.demo.service.DocumentStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class DocumentController {

    @Autowired
    DocumentStorageService documentStorageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") Integer userId,
                                                      @RequestParam("refNumber") Integer refNumber,
                                                      @RequestParam("docType") String docType,
                                                      @RequestParam("validTill") Date validTill) {
        String message = "";
        try {
            documentStorageService.store(file, userId, refNumber, docType, validTill);

            message = "Uploaded the document successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the document: " + file.getOriginalFilename() + "Please Retry!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @GetMapping("/files")
    public ResponseEntity<List<ResponseDocument>> getListFiles() {
        List<ResponseDocument> files = documentStorageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(String.valueOf(dbFile.getDocumentId()))
                    .toUriString();

            return new ResponseDocument(
                    dbFile.getFileName(),
                    fileDownloadUri,
                    dbFile.getDocType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
}
