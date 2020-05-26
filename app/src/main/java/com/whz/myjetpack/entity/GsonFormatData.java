package com.whz.myjetpack.entity;

import java.util.List;

/**
 * Created by 王鹏程 on 2020/5/13.
 * 类(GsonFormatData)解释:
 */
public class GsonFormatData {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * name : 王怀智
         * age : 12
         */

        private String name;
        private int age;

        public String getName() {
            return name;
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
    }
}

