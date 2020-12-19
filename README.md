### 管理软件 文档说明 
### @DQL

#### 1.1 组件自动注入策略

#### 2.1 组件自动监听策略

1. 定义页面元素组件池 ComponentPool 包含了ClickButtons 页面按钮队列 mainFrame 主页面框架 ，container 主页面容器

2. 页面初始化的时候 会调用initViewComponent 初始化手动添加的按钮到ComponentPool中的ClickButtons

3. 随后遍历ClickButtons的元素 添加到页面的Container中

4. 随后调用ButtonListenerSetter 自动设置监听器的自动链接监听功能

*代码改动区域*： 以后所有自定义的监听器都要放入

```java
public ButtonListenerSetter() {
        pool.addListener(new ShowMemberListener());
}
```
