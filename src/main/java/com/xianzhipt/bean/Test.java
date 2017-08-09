package com.xianzhipt.bean;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/8/9.
 */
@Entity
@Table(name = "tbl_test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String TestVal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTestVal() {
        return TestVal;
    }

    public void setTestVal(String testVal) {
        TestVal = testVal;
    }
}
