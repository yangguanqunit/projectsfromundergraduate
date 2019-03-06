# projectsfromundergraduate


一、InstantMessaging
  采用c/s结构实现简易即时通讯功能
  1.实现客户端身份信息匹配验证，包括用户名、密码等用户信息的验证(TCP、30000端口)。
  2.客户端部分消息发送、接收以及切换消息接收对象(UDP、29771端口)。
  3.服务器对客户端身份验证信息接受以及与数据库信息匹配。
  4.服务器接收转发客户消息，并接收存储离线消息并延时转发。
  
二、DHCPserver
  使用Tomcat服务器搭建DHCP手动配置服务器
  以Ubuntu16.04为平台，前端用POST方法向服务器发送待配置数据，后端根据接收信息实时生成shell脚本修改dhcpd.conf配置文件，实现修改dhcp配置信息的功能。
  1.前端页面包括分配地址池、设置网关以及设置首选DNS服务器和备选DNS服务器。
  2.后端服务器解析前端数据，以该数据为参数实时生成、执行shell脚本文件。
  
  
 三、基于开源爬虫项目webmagic定制爬取内容
  原项目地址：https://github.com/code4craft/webmagic
  目标网站：https://www.technologyreview.com/
  爬取内容：论文作者、主标题、副标题。
  本地化格式：50kb为一组的文本文档
  1.修改xpath以选择合适的抓取内容
  2.修改pipeline方法，定制本地化格式
    
四、
