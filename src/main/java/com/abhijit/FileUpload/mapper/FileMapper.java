package com.abhijit.FileUpload.mapper;

import com.abhijit.FileUpload.dto.AddFileDetailDTO;
import com.abhijit.FileUpload.dto.FileDTO;
import com.abhijit.FileUpload.entity.File;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface FileMapper {

    @Mapping(target = "fileNumber",ignore = true)
    @Mapping(target = "version",ignore = true)
    File convertaddFileDetailDTOToFile(AddFileDetailDTO addFileDetailDTO);

    FileDTO convertfileToFileDTO(File file1);
}
