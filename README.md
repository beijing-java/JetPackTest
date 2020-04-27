# JetPackTest
JetPack  demo练习

#### ViewModel

ViewModel类是被设计用来感知生命周期的方式存储和管理UI相关**数据**，ViewModel中数据会一直存活即使
configuration发生改变，（切换系统语言，横竖屏切换等）
![viewmodel生命周期](https://upload-images.jianshu.io/upload_images/9764942-f2b3f9b162334ff0.png?imageMogr2/auto-orient/strip|imageView2/2/w/522/format/webp)

ViewModel的生命周期贯穿Activity的整个生命周期，直到Activity真正意义上的销毁才会结束。

#### LiveData

LiveData是一个数据持有类(**被观察者**)。

**优点：**
+ 数据可以被观察者订阅
+ 能够感知组件（Activity，Fragment，Service）的生命周期
+ 只有在组件处于激活状态下，才会通知观察者有数据更新


#### DataBinding

DataBinding是**数据绑定**，将数据绑定再UI页面上,数据发生变化带动UI发生变化，UI变化带动数据发生变化（双向绑定）

``` build.gradle
  android{
    ...
    dataBinding{
      enabled = true
    }
  }
```






-------------------------------------

![The End](https://github.com/beijing-java/JetPackTest/blob/master/static/image/the_end.gif)
