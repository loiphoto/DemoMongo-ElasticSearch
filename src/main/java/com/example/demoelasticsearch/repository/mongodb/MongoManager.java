package com.example.demoelasticsearch.repository.mongodb;

import com.example.demoelasticsearch.constant.MongoDatabase;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@Data
public class MongoManager {

    private MongoClient mongoClient;

    @Bean
    public DB getPersonDB(){
//        MongoIterable<String> strings = mongoClient.listDatabaseNames();
//        MongoCursor<String> iterator = strings.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        return mongoClient.getDB(MongoDatabase.PERSONDB);
    }
}
