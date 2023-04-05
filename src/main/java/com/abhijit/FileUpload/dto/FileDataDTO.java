package com.abhijit.FileUpload.dto;

import lombok.Data;

@Data
public class FileDataDTO {
    private String fileNumber;
    private String alphabet;
    private int count;
    public String print() {
        String formattedString = fileNumber + "," + alphabet + "," + count ;
        return formattedString;
    }
}
