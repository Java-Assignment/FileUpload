package com.abhijit.FileUpload.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "File")
@Data
@AllArgsConstructor
public class File {

    @Id
    private String fileNumber;
    @Version
    private String version;

    private String alphabet;

    private int count;

    public String print() {
        String formattedString = fileNumber + "," + version + "," + alphabet + "," + count;
        return formattedString;
    }


}
