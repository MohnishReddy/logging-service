package com.example.loggingservice.repositories;

import com.example.loggingservice.entities.Logs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends MongoRepository<Logs, String> {
}
