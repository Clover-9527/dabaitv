package com.itz.dao.impl;

import com.itz.dao.VideoDao;
import com.itz.entity.Video;
import com.itz.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoDaoImpl implements VideoDao {
    QueryRunner qr = C3P0Util.getQueryRunner();
    public static List<Video> countList = new ArrayList<>();
    @Override
    public List<Video> searchVideo(String v_title) throws SQLException {
        String sql="select * from video where v_title like '"+ "%"+v_title+"%'";
        List<Video> searchList = qr.query(sql, new BeanListHandler<Video>(Video.class));
        return searchList;
    }

    @Override
    public List<Video> findAllVideo() throws SQLException {
        List<Video> videoList = new ArrayList<>();
        String sql ="select * from video";
        videoList = qr.query(sql,new BeanListHandler<Video>(Video.class));
        return videoList;
    }

    @Override
    public Video findByID(String vid) throws SQLException {
        String sql="select * from video where vid=?";
        Video video = qr.query(sql, new BeanHandler<Video>(Video.class), vid);
        return video;
    }

    @Override
    public boolean addVideo(Video video) throws SQLException {
        String sql = "insert into video values(?,?,?,?,?,?,?,?)";
        Object[] params={null,video.getV_title(),video.getV_img(),video.getScore(),video.getClassify(),video.getProtagonist(),video.getDescription(),video.getV_url()};
        int num = qr.update(sql,params);
        if(num>0)
            return true;
        else
            return false;
    }

    @Override
    public boolean delVideo(String vid) throws SQLException {
        String sql="delete from video where vid=?";
        int num = qr.update(sql, vid);
        if(num>0)
            return true;
        else
            return false;
    }

    @Override
    public boolean updateVideo(Video video) throws SQLException {
        String sql="update video set v_title=?, v_img=?, score=?, classify=?, protagonist=?, description=?, v_url=? where vid=? ";
        Object[] params={video.getV_title(),video.getV_img(),video.getScore(),video.getClassify(),video.getProtagonist(),video.getDescription(),video.getV_url(),video.getVid()};
        int num = qr.update(sql, params);
        if(num>0)
            return true;
        else
            return false;
    }

    public List<Video> queryByPage(int currentPage,int pageSize,Video v) throws SQLException {
        List<Object> paramslist = new ArrayList<>();
        List<Video> s2 = null;
        int vid = v.getVid();
        String v_title = v.getV_title();
        String classify = v.getClassify();
        String sql = "select * from video where 1=1";

        if(vid != 0) {
            sql += " and vid=?";
            paramslist.add(vid);
        }
        if(v_title != null && v_title.trim().length()>0){
            sql += " and v_title like  '"+ "%"+v_title+"%'";

        }
        if(classify != null && classify.trim().length()>0){
            sql += " and classify= ?";
            paramslist.add(classify);
        }
        Object[] params1 = paramslist.toArray();
        countList = qr.query(sql,new BeanListHandler<Video>(Video.class),params1);

        sql+=" limit ?,?";

        paramslist.add(currentPage);
        paramslist.add(pageSize);
        Object[] params = paramslist.toArray();

        List<Video> list = qr.query(sql,new BeanListHandler<Video>(Video.class),params);
        return list;
    }

}
