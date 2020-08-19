package com.zr.tinyurl.vo;


import javax.persistence.*;

@Entity
@Table(name="tinyurl")
public class Url {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private  int id;
    @Column
    private String oldUrl;
    @Column
    private String newUrl;
    @Column
    private int min;
    @Column
    private long createTime;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOldUrl() {
        return oldUrl;
    }

    public void setOldUrl(String oldUrl) {
        this.oldUrl = oldUrl;
    }

    public String getNewUrl() {
        return newUrl;
    }

    public void setNewUrl(String newUrl) {
        this.newUrl = newUrl;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
