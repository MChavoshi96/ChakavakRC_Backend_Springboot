package com.repository;

import com.model.LogModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ILogRepository extends MongoRepository<LogModel, String>
{
}
