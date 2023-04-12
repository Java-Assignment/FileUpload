package com.abhijit.dataupload.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "This contains file detail to be added by user")
public class DataDTO {
    @Schema(description = "Add Variable")
    private String Variable;
    @Schema(description = "add value")
    private int Value;


}
