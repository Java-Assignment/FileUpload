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
    private String Variable1;
    private int Value1;
    private String Variable2;
    private int Value2;
    private String Variable3;
    private int Value3;
}
