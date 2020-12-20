项目名称：家庭电话本

华中科技大学 计算机学院 移动终端开发结课作业

项目网址：https://github.com/junncao/SimplePhoneBook

### 功能：

1.读取本地通话记录详细信息并聚合方式显示；单条显示最近一条记录；

2.通话记录中展示姓名、时间、类型、归属地；

3.管理电话本中带有目标关键字（爸爸、妈妈、老婆、儿子、女儿等）的联系人；

4.联系人详情中展示联系人的天气字段，并可以生成“关怀短信”；

5.管理电话本中带有目标关键字（爸爸、妈妈、老婆、儿子、女儿等）的联系人；
联系人详情中展示联系人的天气字段，并可以生成“关怀短信”；

6.联系人详情根据不同关键字信息，定制不同关怀短信内容；

### 结构：

分为电话本模块和天气模块，每个模块由一个activity和多个Fragment完成。

电话本采用两个Tab加ViewPager实现，交互友好。电话记录直接从手机读取，然后在recyclerview中填充进去，联系人会从数据库中读取和存储。

<img src="/Users/caojun/Library/Application Support/typora-user-images/image-20201213120609563.png" alt="image-20201213120609563" style="zoom:50%;" />

天气模块采用MVVM架构，结构清晰。获取天气信息采用的网上的免费api--彩云天气API，通过retrofit进行请求和json的解码工作。初次获取联系人天气会要求输入联系人的地点，然后保存在sharedpreference中，下次可以直接通过联系人名字获取。

<img src="/Users/caojun/Library/Application Support/typora-user-images/image-20201213120715041.png" alt="image-20201213120715041" style="zoom:50%;" />

### 用到的技术：

本项目采用kotlin开发，因为kotlin是近几年官方的推荐语言，而且相比java会有很多的优点，比如扩展函数，对函数式编程的大力支持，对空指针异常的处理等等，而且kotlin中的Android-extension插件可以减少很多类似findbyview之类的样板代码，抱着学习的态度完成了这个项目。

### 用到的开源库：

1.jetpack组件：

​	Room：数据库

​	Lifecycles：⽤于对组件⽣命周期的观察

​	LiveData：和Room⼀起使⽤，更好的使⽤观察者模式

2.Retrofit：与网上的某免费天气预报api通讯，获取天⽓

3.Gson：解析Json⽂件

4.permissionX：⽤于更简洁地处理权限问题

5.libphonenumber：⽤于获取电话归属地

### 任务分工：



### 工作日志：

![image-20201220152145436](/Users/caojun/Downloads/image-20201220152145436.png)