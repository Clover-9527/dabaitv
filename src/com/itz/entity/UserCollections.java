package com.itz.entity;

import java.util.Date;

public class UserCollections {
    private int cid;
    private int uid;
    private int vid;
    private Date collectTime;

    public UserCollections() {
    }

    public UserCollections(int cid, int uid, int vid, Date collectTime) {
        this.cid = cid;
        this.uid = uid;
        this.vid = vid;
        this.collectTime = collectTime;
    }

    @Override
    public String toString() {
        return "UserCollections{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", vid=" + vid +
                ", collectTime=" + collectTime +
                '}';
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }
}
