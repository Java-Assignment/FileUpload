package com.abhijit.dataupload.repo;

import com.abhijit.dataupload.entity.UploadedFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadedFileRepo extends MongoRepository<UploadedFile, String> {
}
