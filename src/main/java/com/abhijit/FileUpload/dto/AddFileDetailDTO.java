package com.abhijit.FileUpload.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "This contains file detail to be added by user")
public class AddFileDetailDTO {
    @Schema(description = "Add alphabets")
    private String alphabet;
    @Schema(description = "add count")
    private int count;


}
