package com.abhijit.FileUpload.repo;

import com.abhijit.FileUpload.entity.UploadedFiles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadedFileRepo extends MongoRepository<UploadedFiles,String> {
}
