package com.itz.dao;

import com.itz.entity.Video;

import java.sql.SQLException;
import java.util.List;

public interface VideoDao {
    List<Video> searchVideo(String v_title) throws SQLException;
    List<Video> findAllVideo() throws SQLException;
    Video findByID(String vid) throws SQLException;
    boolean addVideo(Video video) throws SQLException;
    boolean delVideo(String v_tittle) throws SQLException;
    boolean updateVideo(Video video) throws SQLException;
    public List<Video> queryByPage(int currentPage,int pageSize,Video v) throws SQLException;
}
