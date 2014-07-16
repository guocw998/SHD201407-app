SHD201407-app
=============

2014-07 
<pre>
  1 基于maven的项目，整合内容spring4.0.3+hibernate4.3.5+jpa2.1+druid1.0.6+JDK8编写
  2 JavaConfig编程：Spring的no xml编程。
  3 使用Druid连接池管理数据库连接，druid1.0.6提供的DruidConnectionProvider不适用Hibernate4.3,抛出下面的异常：
  Caused by: java.lang.ClassNotFoundException: org.hibernate.service.jdbc.connections.spi.ConnectionProvider
  	at java.net.URLClassLoader$1.run(URLClassLoader.java:372)
  	at java.net.URLClassLoader$1.run(URLClassLoader.java:361)
  	at java.security.AccessController.doPrivileged(Native Method)
  	at java.net.URLClassLoader.findClass(URLClassLoader.java:360)
  	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
  	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:308)
  	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
  	... 65 more
  在项目中，参考原有实现com.urely.shd2014.DruidConnectionProvider，就可以继续使用。
</pre>
