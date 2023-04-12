package com.abhijit.dataupload.dto;

import lombok.Data;

@Data
public class FileDataDTO {
    private String fileNumber;
    private String Variable1;
    private int Value1;
    private String Variable2;
    private int Value2;
    private String Variable3;
    private int Value3;

    public String print() {
        String formattedString = fileNumber + "," + Variable1 + "," + Value1 + "," + Variable2 + "," + Value2 + "," + Variable3 + "," + Value3;
        return formattedString;
    }
}
