package com.kads.demo.service;

import com.kads.demo.exception.DocumentStorageException;
import com.kads.demo.model.DocumentStorageProps;
import com.kads.demo.repo.DocumentDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class DocumentStorageService {
    @Autowired
    DocumentDBRepository documentDBRepository;

    public DocumentStorageProps store(MultipartFile file, Integer userId, Integer refNumber, String docType, Date validTill) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
            }
                throw new DocumentStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            } catch (Exception e) {

        }
        DocumentStorageProps documentStorageProps = new DocumentStorageProps();
        documentStorageProps.setUserId(userId);
        documentStorageProps.setFileName(fileName);
        documentStorageProps.setDocType(docType);
        documentStorageProps.setRefNumber(refNumber);
        documentStorageProps.setValidTill(validTill);
        documentStorageProps.setData(file.getBytes());

        return documentDBRepository.save(documentStorageProps);
    }

    public DocumentStorageProps getFile(String id) {
        return documentDBRepository.findById(id).get();
    }

    public Stream<DocumentStorageProps> getAllFiles() {
        return documentDBRepository.findAll().stream();
    }
}
