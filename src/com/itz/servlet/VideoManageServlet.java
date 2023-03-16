package com.itz.servlet;

import com.alibaba.fastjson.JSON;
import com.itz.entity.PageBean;
import com.itz.entity.Video;
import com.itz.service.VideoService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig(location="D:\\",fileSizeThreshold=1024)
@WebServlet(name = "VideoManageServlet", value = "/videoManage")
public class VideoManageServlet extends HttpServlet {
    private VideoService service = new VideoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        //获取客户端指定参数operator
        String operator =request.getParameter("operator");
        Class clazz =this.getClass();
        //获取执行的放法
        try {
            Method method  = clazz.getMethod(operator, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询所有视频信息
    public void showall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        VideoService service = new VideoService();
        List<Video> list = new ArrayList<>();
        list=service.findAllVideo();
        String videoList = JSON.toJSONString(list); // "{key:value}"
        response.getWriter().println(videoList);
    }

    //添加视频
    public void addVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String v_title= request.getParameter("v_title");
        String score= request.getParameter("score");
        String classify= request.getParameter("classify");
        String protagonist= request.getParameter("protagonist");
        String description= request.getParameter("description");
        String v_url= request.getParameter("v_url");
        String v_img=request.getParameter("v_img");
        Video video = new Video(v_title,v_img,Double.parseDouble(score),classify,protagonist,description,v_url);
          service.addVideo(video);
        queryByPage(request,response);
    }

    //删除视频
    public void delVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vid=request.getParameter("vid");
        boolean b = service.delVideo(vid);
        queryByPage(request,response);
    }

    //通过id查找视频信息，回显数据
    public void findByID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vid=request.getParameter("vid");
        Video video = service.findByID(vid);
        request.getSession().setAttribute("video",video);
        request.getRequestDispatcher("/admin/updateVideo.jsp").forward(request,response);
    }

    //修改视频信息
    public void searchVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String v_title=request.getParameter("v_title");
        List<Video> videoList = service.searchVideo(v_title);
        request.getSession().setAttribute("searchList",videoList);
        request.getRequestDispatcher("searchList.jsp").forward(request,response);
    }

    //修改视频信息
    public void updateVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vid=request.getParameter("vid");
        String v_title= request.getParameter("v_title");
        String score= request.getParameter("score");
        String classify= request.getParameter("classify");
        String protagonist= request.getParameter("protagonist");
        String description= request.getParameter("description");
        String v_url= request.getParameter("v_url");
        String v_img=request.getParameter("v_img");
         Video video = new Video(Integer.parseInt(vid),v_title,v_img,Double.parseDouble(score),classify,protagonist,description,v_url);
        service.updateVideo(video);
        queryByPage(request,response);
}

    //分页查询
    public void queryByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Video v = new Video();
        String vid=request.getParameter("vid");
        String v_title=request.getParameter("v_title");
        String classify=request.getParameter("classify");

        if(vid!=null&&vid.trim()!="")
            v.setVid(Integer.parseInt(vid));
            v.setV_title(v_title);
            v.setClassify(classify);
        request.getSession().setAttribute("v",v);
        String currentPage = request.getParameter("currentPage");
        if (currentPage == null){
            currentPage = "1";
        }
        int pageSize = 10;
        PageBean<Video> pageBean = service.queryByPage(Integer.parseInt(currentPage), pageSize,v);
        //对象存储在域中
        request.setAttribute("pageBean",pageBean);
        //转发
        request.getRequestDispatcher("/admin/videoManage.jsp").forward(request,response);
    }

    //视频详情
    public void videoDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String vid=request.getParameter("vid");

        Video video = service.findByID(vid);
       request.getSession().setAttribute("video",video);
       request.getRequestDispatcher("play.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }
}
