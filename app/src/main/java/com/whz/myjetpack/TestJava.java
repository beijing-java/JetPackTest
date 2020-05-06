package com.whz.myjetpack;

import com.whz.myjetpack.entity.TestToData;
import com.whz.myjetpack.kotlin.spreadfun.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 王鹏程 on 2020/5/6.
 * 类(TestJava)解释:
 * {@link TestKotlinKt}
 */
public class TestJava {

    public static void main(String[] args) {
        //java 的{{}} 调用对象内部方法进行初始化操作
        List<Integer> arr = new ArrayList<Integer>() {{
            add(1);
            add(3);
        }};
        Map m = new HashMap() {{
            put("main", "TestJava");
        }};

//        System.out.println(arr);
//        System.out.println(m);
        // 调用kotlin的默认参数函数
        TestKotlin testKotlin = new TestKotlin();
        String toStr = testKotlin.joinToString(arr, "-", "【", "】");
//        System.out.println(toStr);
        // 调用kotlin的扩展函数，需要使用文件名来进行调用
        String str = "王怀智";
        System.out.println(StringKt.lastChar(str));//扩展函数
        StringKt.setLastChar1(str, 'a');//扩展属性

    }
}


