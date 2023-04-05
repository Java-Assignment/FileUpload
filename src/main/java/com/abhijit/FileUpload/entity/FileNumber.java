package com.abhijit.FileUpload.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FileNumber")
@Data
@AllArgsConstructor
public class FileNumber {
    @Id
    private String id;
    @Version
    private Long version;
    private Long FileNumber;
}
