package com.itz.service;

import com.itz.dao.CollectionDao;
import com.itz.dao.impl.CollectionDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollectionService {
    CollectionDao dao = new CollectionDaoImpl();

    public List<Map<String, Object>> queryAll(String uid) {
        List<Map<String, Object>> collectionList = new ArrayList<>();
        try {
            collectionList=dao.queryAll(uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collectionList;
    }


    public boolean addeCollection(String uid,String vid) {
        boolean b=false;
        try {
           b= dao.addeCollection(uid,vid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    public boolean deleteCollection(String vid){
        boolean b=false;
        try {
           b= dao.deleteCollection(vid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }
}
