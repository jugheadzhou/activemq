# activemq-demo
##### 说明：
activemq学习demo,以及与spring、springboot整合。

##### 索引：
com.jugheadzhou.jms包下面是对应使用Activemq的简单封装，方便业务开发

##### 与spring整合的demo业务：
1.模拟用户系统登录成功后送用户积分

实现：用户登录成功后，通过activemq 队列模式发送消息给积分系统，积分系统监听到信息后新增对应用户的积分记录

2.模拟用户系统删除用户积分记录

实现：用户删除记录后，通过activemq主题模式发送消息给积分系统，积分系统监听到信息后删除对应用户的积分记录
