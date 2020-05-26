package com.whz.myjetpack;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import com.whz.myjetpack.kotlin.spreadfun.StringKt;
import com.whz.myjetpack.utils.LogUtils;

import java.util.*;


/**
 * Created by 王鹏程 on 2020/5/6.
 * 类(TestJava)解释:
 * {@link TestKotlinKt}
 */
public class TestJava {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };

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
        String result = String.format( "my name is %s","占位符");// 底层调用new Formatter().format
        String result1=new Formatter().format( "my name is %s","占位符").toString();
        System.out.println(result);
    }

    public void a() {
        b();
        f();
    }

    public void b() {
        c();
        e();
    }

    public void f() {

    }

    public void c() {

    }

    public void e() {

    }

    public void d() {

    }

}


