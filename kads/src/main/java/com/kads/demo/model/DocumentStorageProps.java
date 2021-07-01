package com.kads.demo.model;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.*;
import java.util.Date;

@ConfigurationProperties(prefix = "file")
@Entity
@Table(name = "staff_documents")
public class DocumentStorageProps {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "document_id")
    private Integer documentId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "document_type")
    private String docType;
    @Column(name = "document_reference_number")
    private Integer refNumber;
    @Column(name = "valid_till")
    private Date validTill;
    @Lob
    private byte[] data;

    public DocumentStorageProps() {
    }

    public DocumentStorageProps(Integer userId, String fileName, String docType, Integer refNumber, Date validTill, byte[] data) {
        this.userId = userId;
        this.fileName = fileName;
        this.docType = docType;
        this.refNumber = refNumber;
        this.validTill = validTill;
        this.data = data;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public Integer getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(Integer refNumber) {
        this.refNumber = refNumber;
    }

    public Date getValidTill() {
        return validTill;
    }

    public void setValidTill(Date validTill) {
        this.validTill = validTill;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
