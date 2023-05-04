package com.example.loggingservice.repositories;

import com.example.loggingservice.entities.Logs;
import com.example.loggingservice.enums.LogType;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LogsRepository extends MongoRepository<Logs, String> {

    @Aggregation(pipeline = {
            "{ '$match': { 'logType': {$in: ?0} } }",
            "{ '$match': { 'createdAt': {$gte: ?1} } }",
            "{ '$match': { 'createdAt': {$lte: ?2} } }",
            "{ '$match': { 'tag': ?3 } }",
            "{ '$match': { 'trusted': {$in: ?4} } }",
            "{ '$match': { 'visible': {$in: ?5} } }",
            "{ '$skip': ?6 }",
            "{ '$limit': ?7 }",
            "{ '$sort': { ?8 : ?9 } }"
    })
    List<Logs> find(List<LogType> logTypeList, Date createdAfter, Date createdBefore, String tag, List<Boolean> trusted, List<Boolean> visible, int skip, int limit, String sortBy, int sortOrder);

    @Aggregation(pipeline = {
            "{ '$match': { 'logType': {$in: ?0} } }",
            "{ '$match': { 'createdAt': {$gte: ?1} } }",
            "{ '$match': { 'createdAt': {$lte: ?2} } }",
            "{ '$match': { 'trusted': {$in: ?3} } }",
            "{ '$match': { 'visible': {$in: ?4} } }",
            "{ '$skip': ?5 }",
            "{ '$limit': ?6 }",
            "{ '$sort': { ?7 : ?8 } }"
    })
    List<Logs> find(List<LogType> logTypeList, Date createdAfter, Date createdBefore, List<Boolean> trusted, List<Boolean> visible, int skip, int limit, String sortBy, int sortOrder);

}
