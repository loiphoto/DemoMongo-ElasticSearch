package com.example.demoelasticsearch.repository.mongodb;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public abstract class BaseRepository {
    protected static final String ID = "_id";

    protected static final String PUSH = "$push";
    protected static final String PULL = "$pull";
    protected static final String SET = "$set";
    protected static final String UNSET = "$unset";
    protected static final String NOT_EQUALS = "$ne";
    protected static final String LESS_THAN = "$lt";
    protected static final String GREATER_THAN = "$gt";
    protected static final String INCREASE = "$inc";
    protected static final String OR = "$or";
    protected static final String IN = "$in";
    protected static final String NOT_IN = "$nin";
    protected static final String EXISTS = "$exists";
    protected static final String AND = "$and";
    protected static final String ELEMENT_MATCH = "$elemMatch";
    protected static final String GREATER_THAN_OR_EQUAL = "$gte";
    protected static final String LESS_THAN_OR_EQUAL = "$lte";
    protected static final String ADD_TO_SET = "$addToSet";
    protected static final String GROUP = "$group";
    protected static final String MATCH = "$match";
    protected static final String MAX = "$max";
    protected static final String SUM = "$sum";
    protected static final String SORT = "$sort";
    protected static final String SKIP = "$skip";
    protected static final String LIMIT = "$limit";

    protected static final int ASCENDING_ORDER = 1;
    protected static final int DESCENDING_ORDER = -1;

    protected static final int PROJECT_SELECT = 1;

    protected abstract DB getDatabase();

    protected abstract String getCollectionName();

    protected DBCollection getCollection() {
        return getDatabase().getCollection(getCollectionName());
    }

    protected void put(DBObject obj, String field, Object value) {
        if (value != null) {
            obj.put(field, value);
        }
    }
}
