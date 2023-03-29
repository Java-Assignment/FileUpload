package com.abhijit.FileUpload.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FileData")
@Data
@AllArgsConstructor
public class FileData {
    @Id
    private String fileNumber;
    private String version;

    private String alphabet;

    private int count;
}
