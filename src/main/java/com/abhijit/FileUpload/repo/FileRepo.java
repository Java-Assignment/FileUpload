package com.abhijit.FileUpload.repo;

import com.abhijit.FileUpload.entity.File;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepo extends MongoRepository<File,String> {
}
