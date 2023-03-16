package com.itz.dao.impl;

import com.itz.dao.CollectionDao;
import com.itz.entity.CollectList;
import com.itz.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CollectionDaoImpl implements CollectionDao {
    private QueryRunner qr = C3P0Util.getQueryRunner();

    @Override
    public List<Map<String, Object>> queryAll(String uid) throws SQLException {
        List<Map<String, Object>> collectionList = new ArrayList<>();

        String sql="select v.vid,v_title,classify,score,collectTime from collections as c,user as u,video as v where c.vid=v.vid and c.uid=u.uid and u.uid=?";
        collectionList = qr.query(sql, new MapListHandler(), uid);
        return collectionList;
    }

    @Override
    public boolean addeCollection(String uid, String vid) throws SQLException {
        String collect="select * from collections where uid=? and vid=?";
        Object[] info={uid,vid};
        CollectList queryed = qr.query(collect, new BeanHandler<CollectList>(CollectList.class), info);
        if(queryed==null){
            String sql ="insert into collections values(?,?,?,?)";
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String collectTime = sdf.format(date);
            Object[] params={null,Integer.parseInt(uid),Integer.parseInt(vid),collectTime};
            int n = qr.update(sql, params);
            if(n>0)
                return true;
            else
                return false;
        }else
            return false;
    }

    @Override
    public boolean deleteCollection(String vid) throws SQLException {
        boolean b=false;
        String sql ="delete from collections where vid=?";
        int n =  qr.update(sql,vid);
        if(n>0)
            return true;
        else
            return false;
    }
}
