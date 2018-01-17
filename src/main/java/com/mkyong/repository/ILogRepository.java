package com.mkyong.repository;

import com.mkyong.model.LogModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ILogRepository extends MongoRepository<LogModel, String>
{
}
