Android KCVerticalDrawerHandler
===========
纵向抽屉导航。

## 如何引用此组件：
### 安装
```
git clone https://github.com/mindpin/android-menudrawer
cd android-menudrawer
mvn clean install
```

### maven引用
在maven项目，pom.xml添加以下依赖引用：

```
<dependency>
    <groupId>com.github.destinyd</groupId>
    <artifactId>menudrawer</artifactId>
    <version>0.1.0-SNAPSHOT</version>
    <type>apklib</type>
</dependency>
```

## 使用说明
```
public class MainActivity extends Activity{
  @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      //注入
      KCVerticalDrawerHandler kcVerticalDrawerHandler = new KCVerticalDrawerHandler(this);
      //设置背景view，即menu view
      kcVerticalDrawerHandler.add_background_view(R.layout.menu);
      //设置前景view，即content view，如果已经setContentView，可省略
      kcVerticalDrawerHandler.add_foreground_view(R.layout.activity_main);
    }
}
```

## 其余api介绍
```
// 增加背景 View
public void add_background_view(View view);

// 增加前景 View
public void add_foreground_view(View view);

// 打开和关闭背景View
public void open();
public void close();

// 切换手势支持
public void enable_gesture(boolean flag);

// 存在或者不存在默认标题栏
enable_default_titlebar(boolean isShow);

// 设置 open 时，前景向下滑动后保留的露出的部分高度，以dp为单位。
// 当标题栏存在时，底部留空的是 blank_dp + titlebar_height
// 当标题栏不存在时，底部留空的是 blank_dp
public void set_foreground_opening_offset(int offset_dp);
```
