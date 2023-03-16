package com.itz.service;

import com.itz.dao.VideoDao;
import com.itz.dao.impl.VideoDaoImpl;
import com.itz.entity.PageBean;
import com.itz.entity.Video;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoService {
    VideoDao dao = new VideoDaoImpl();

    public List<Video> searchVideo(String v_title){
        List<Video> searchList = new ArrayList<>();
        try {
            searchList= dao.searchVideo(v_title);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchList;
    }

    public List<Video> findAllVideo() {
        List<Video> list = new ArrayList<>();
        try {
            list=dao.findAllVideo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addVideo(Video video) {
        boolean b = false;
        try {
            b= dao.addVideo(video);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    public boolean delVideo(String vid) {
        boolean b = false;
        try {
            b= dao.delVideo(vid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    public Video findByID(String vid) {
        Video video = new Video();
        try {
           video = dao.findByID(vid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return video;
    }

    public boolean updateVideo(Video video){
        boolean b=false;
        try {
            dao.updateVideo(video);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }


    public PageBean<Video> queryByPage(int currentPage, int pageSize, Video v){
        PageBean<Video> pb = new PageBean<Video>();
        try {
            //存储当前页数
            pb.setCurrentPage(currentPage);
            //存储每页显示的条数
            pb.setPageSize(pageSize);
            //存储要显示的权限数据 List集合,找dao层获取
            //调用dao层方法queryByPage,传递计算好的参数,获取查询结果集
            List<Video> list =
                    dao.queryByPage((currentPage - 1) * pageSize, pageSize,v);
            //集合List,存在到pageBean中
            pb.setList(list);

            //存储共有多少条数据,找dao层获取
            long totalCount = VideoDaoImpl.countList.size();
            pb.setTotalCount(totalCount);

            //存储一共有多少页
            //计算 (总条数/每页显示的条数) 向上去整  14.0 / 5 = 2.22
            int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
            pb.setTotalPage(totalPage);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return pb;
    }


}
