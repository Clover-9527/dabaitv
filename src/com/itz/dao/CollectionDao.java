package com.itz.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CollectionDao {
    List<Map<String, Object>> queryAll(String uid) throws SQLException;

    boolean addeCollection(String uid,String vid) throws SQLException;

    boolean deleteCollection(String vid) throws SQLException;


}
