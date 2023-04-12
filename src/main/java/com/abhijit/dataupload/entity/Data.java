package com.abhijit.dataupload.entity;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FileData")
@lombok.Data
@AllArgsConstructor
public class Data {
    @Id
    private String fileNumber;
    private String version;

    private String Variable;

    private int Value;
}
