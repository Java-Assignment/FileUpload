package com.abhijit.dataupload.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UploadedFile")
@Data
@AllArgsConstructor
public class UploadedFile {
    @Id
    private String fileNumber;
    private String Variable;

    private int Value;
}
