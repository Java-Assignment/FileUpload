package com.abhijit.dataupload.repo;

import com.abhijit.dataupload.entity.Data;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepo extends MongoRepository<Data, String> {

}
