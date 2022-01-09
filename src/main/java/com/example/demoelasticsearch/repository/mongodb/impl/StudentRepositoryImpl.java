package com.example.demoelasticsearch.repository.mongodb.impl;

import com.example.demoelasticsearch.entity.Student;
import com.example.demoelasticsearch.model.request.StudentRequest;
import com.example.demoelasticsearch.repository.mongodb.BaseRepository;
import com.example.demoelasticsearch.repository.mongodb.MongoManager;
import com.example.demoelasticsearch.repository.mongodb.base.StudentRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@AllArgsConstructor
public class StudentRepositoryImpl extends BaseRepository implements StudentRepository {

    private final String NAME = "name";
    private final String CLASS = "class";
    private final String MATH_SCORE = "math_score";
    private final String PHYSIC_SCORE = "physic_score";
    private final String CHEMISTRY_SCORE = "chemistry_score";

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

    @Override
    public void insert(StudentRequest student) {
        DBObject studentDBObject = castToDbObject(student);
        getCollection().insert(studentDBObject);
    }

    @Override
    public List<Student> findAll(){
        List<Student> students = new ArrayList<>();
        DBCursor dbObjects = getCollection().find();
        while (dbObjects.hasNext()){
            DBObject dbObject = dbObjects.next();
            Student student = castToObject(dbObject);
            students.add(student);
        }
        return students;
    }


    private DBObject castToDbObject(StudentRequest student){
        BasicDBObject dbObject = new BasicDBObject();
        put(dbObject, NAME, student.getName());
        put(dbObject, CLASS, student.getClassName());
        put(dbObject, MATH_SCORE, student.getMathScore());
        put(dbObject, PHYSIC_SCORE, student.getPhysicScore());
        put(dbObject, CHEMISTRY_SCORE, student.getChemistryScore());
        return dbObject;
    }

    private Student castToObject(DBObject dbObject){
        Student student = new Student();
        BasicDBObject objBasic = (BasicDBObject) dbObject;
        student.setStudentId(objBasic.getObjectId(ID).toString());
        student.setName(objBasic.getString(NAME));
        student.setClassName(objBasic.getString(CLASS));
        student.setMathScore((float) objBasic.getDouble(MATH_SCORE));
        student.setPhysicScore((float) objBasic.getDouble(PHYSIC_SCORE));
        student.setChemistryScore((float) objBasic.getDouble(CHEMISTRY_SCORE));
        return student;
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

