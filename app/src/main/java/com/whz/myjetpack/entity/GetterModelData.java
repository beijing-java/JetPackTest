package com.whz.myjetpack.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王鹏程 on 2020/5/13.
 * 类(GetterModelData)解释:
 * model生成get方法，添加判空操作
 * String/List添加判断
 *
 */
public class GetterModelData {
    private String name;
    private int age;
    private List list;
    private boolean bool;
    public static int test;



    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List getList() {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }


}
