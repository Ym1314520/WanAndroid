# Wanandroid—中期考核
## 1.app实现功能
### 1.1本地永久化登录
- 可以实现注册登录功能。

- 账号密码永久保存

- 自动记住账号密码

### 1.2浏览文章
- 阅览文章列表

- 搜索位于不同页数的文章

- 点击标题跳转文章内容
### 1.3使用导航
- 导航界面分为两个部分：常用网站和公众号作者内容

- 常用网站：搜索常用网站、点击跳转常用网站

- 作者内容：实现上拉加载
### 1.4浏览项目
- 点击标题跳转项目具体内容
 
- 下拉刷新界面
### 1.5知识体系
- 简单的可以阅览
### 1.6公众号
- 暂未完成
## 2.Wanandroid分包
- adapter：不同适配器包

- datas：存储不同数据的包

- fragment：存储一个界面分了几个界面切换的包

- json：不同接口的解析工具

- mainactivity：主界面

- login：登录
## 3.Wandroid布局
- article_list、articles：置顶文章

- passage_list、passages：文章

- program_list、program：项目

- public_passage、publicpassages：公众号

- system_list、systemlists:知识体系

- urlgo_fragment、urlgo:常用网站

- content_fragment、contents：公众号大佬们

- menu：主菜单和底部菜单

- loginit、register：登录和注册界面

- go：导航布局


##4.主要代码逻辑
###4.1网络请求
在主函数中调用InternetUtil类中的sendRequestWithHttpURLConnection（）方法开始网络请求。

###4.2BackMethod回调
通过方法回调到主函数中，将得到的字符串response传递给主函数中重写接口的方法Backmethod中去，调用Json类的jsonRelease（）的方法解析拿到的数据。

###4.3打包数据
将每次得到的一组数据作为一个对象存到List<Datas>中去，返回List给主函数。


###4.4把数据显示在UI界面上

##5.中期考核总结
###5.1存在问题
一开始存储数据是采用的数组存储，特别麻烦，而且存储逻辑很混乱，拿数据不方便。之后和室友探讨之后明白了数据是一个对象一个对象拿的，代码逻辑就清晰了很多。
###5.2收获
对回调认识更清楚了，很多点击事件
