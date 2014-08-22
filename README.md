Android KCVerticalDrawerHandler
===========
纵向抽屉导航。

默认绑定时，底部留空的高度正好为titlebar（actionbar）高度，支持sherlock actionbar。

可以通过**set_foreground_opening_offset(int offset)**设置底部留空高度。0为不留空，不能通过点击或者滑动关闭。

## 版本(last version)
### 最新稳定版(release)
0.1.2版（tag：v0.1.2）
### 最新开发版(SNAPSHOT)
0.1.2-SNAPSHOT版（branch：master）

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
    <version>(last version)</version>
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

// 设置前景向下滑动后保留的露出的部分高度，以dp为单位。
// 默认为titlebar_height
// 若自定义titlebar时，请记得调用此函数设置露出高度。
public void set_foreground_opening_offset(int offset_dp);
```
