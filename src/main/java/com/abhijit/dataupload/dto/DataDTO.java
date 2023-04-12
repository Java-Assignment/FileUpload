package com.abhijit.dataupload.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "This contains file detail to be added by user")
public class DataDTO {
    @Schema(description = "Add Variable")
    private String Variable1;
    @Schema(description = "add value")
    private int Value1;
    @Schema(description = "Add Variable ")
    private String Variable2;
    @Schema(description = "Add Value ")
    private int Value2;
    @Schema(description = "Add Variable ")
    private String Variable3;
    @Schema(description = "Add Value ")
    private int Value3;


}
