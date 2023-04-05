package com.abhijit.FileUpload.service;

import com.abhijit.FileUpload.entity.FileNumber;
import com.abhijit.FileUpload.repo.FileNumberRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FileNumberServiceImpl implements FileNumberService {
    @Autowired
    private FileNumberRepo fileNumberRepo;


    @Override
    @Retryable(value = OptimisticLockingFailureException.class, maxAttempts = 15)
    public String getNewFileNumber() {
        try {
            FileNumber fileNumber = fileNumberRepo.findAll().get(0);
            Long newFileNumber = fileNumber.getFileNumber() + 1;
            fileNumber.setFileNumber(newFileNumber);
            fileNumberRepo.save(fileNumber);
            return String.valueOf(newFileNumber);
        } catch (OptimisticLockingFailureException e) {
            log.error("exception in account creation will retry.");
            throw e;
        }
    }
}
