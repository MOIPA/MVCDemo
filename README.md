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

#### 3.1 会员注册功能

1. 会员注册时需要根据时长，会员类型计算金额

2. 会员到今年9月1号的年龄需要自己计算,会员资格结束时间需要自己计算

3. 家庭会员可以添加配偶或者子女

4. 被添加会员不可以编辑时长和会员类型

5. 访客会员不可以编辑费用类型下拉框 且结束时间默认为使用次数

6. 需要校验会员输入信息

#### 框架使用

1. 以后写代码只需要在AppFrameImpl里定义按钮（按钮信息，按钮监听名），或者弹出框（弹出框的标题，弹出框名字）
在controller.listener 层定义监听即可 （如 定义一个和某按钮监听名一样的监听，参考ShowmemberListener）

#### 技术说明

1. 自动绑定实现原理：Java 内省反射机制
2. 单例（懒汉模式）
3. 泛化，继承，多态，封装
4. I18 国际化 简单工厂模式