package com.abhijit.dataupload.dto;

import lombok.Data;

@Data
public class FileDataDTO {
    private String fileNumber;
    private String Variable;
    private int Value;
    public String print() {
        String formattedString = fileNumber + "," + Variable + "," + Value ;
        return formattedString;
    }
}
