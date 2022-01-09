package com.example.demoelasticsearch.repository.mongodb.impl;

import com.example.demoelasticsearch.repository.mongodb.BaseRepository;
import com.example.demoelasticsearch.repository.mongodb.MongoManager;
import com.example.demoelasticsearch.repository.mongodb.base.StudentRepository;
import com.mongodb.DB;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@AllArgsConstructor
public class StudentRepositoryImpl extends BaseRepository implements StudentRepository {

    private final String USER_COLLECTION = "student";

    private final MongoManager mongoManager;

    @Override
    protected DB getDatabase() {
        return mongoManager.getPersonDB();
    }

    @Override
    protected String getCollectionName() {
        return USER_COLLECTION;
    }

//    @Override
//    public int count() {
//        int count = getCollection().find().count();
//        return count;
//    }
//
//    @Override
//    public List<User> find() {
//        List<User> result = new ArrayList<>();
//        int count = getCollection().find().count();
//        System.out.println(count);
//        DBCursor dbObjects = getCollection().find();
//        while (dbObjects.hasNext()){
//            User user = castFromDbObject(dbObjects.next());
//            result.add(user);
//        }
//        return result;
//    }
//
//    private User castFromDbObject(DBObject object){
//        BasicDBObject objBasic = (BasicDBObject) object;
//        User user = new User();
//        user.setUserId(objBasic.getObjectId(ID).toString());
//        user.setName(objBasic.getString("name").toString());
//        String date = objBasic.getString("birth_day");
//        LocalDate localDate = LocalDate.parse(date);
//        user.setBirthDay(localDate);
//        return user;
//    }
}

