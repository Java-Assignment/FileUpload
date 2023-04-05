package com.abhijit.FileUpload.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UploadedFiles")
@Data
@AllArgsConstructor
public class UploadedFiles {
    @Id
    private String fileNumber;
    private String alphabet;

    private int count;
}
