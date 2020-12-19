### 管理软件 文档说明 
### @DQL

#### 1.1 组件自动注入策略

#### 2.1 组件自动监听策略

1. 定义单个监听器  ShowMemberListener为例，继承ActionListener 内部要写好监听器名字

2. 自动监听器  定义接口防止以后改动自动监听器，IListenerSetter内部设置了泛型，只有一个作用方法 设置监听

3. 定义接口池 ListenerPool 以后所有新建的监听都可以放入

4. 实现自动化监听器  ButtonListenerSetter

*代码改动区域*： 以后所有自定义的监听器都要放入

```java
public ButtonListenerSetter() {
        pool.addListener(new ShowMemberListener());
}
```
