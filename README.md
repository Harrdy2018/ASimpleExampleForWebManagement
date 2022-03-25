# ASimpleExampleForWebManagement
* 一个简单的管理页面
## 部署到服务器
```sh
# 前端打包
scp -r /a/ASimpleExampleForWebManagement/frontend/webmanage/ root@47.96.251.225:/usr/local/production/frontend/apache-tomcat-9.0.37/webapps/
# 后台打包
scp /a/ASimpleExampleForWebManagement/backend/target/management.war root@47.96.251.225:/usr/local/production/backend/apache-tomcat-9.0.37/webapps/
# 重启nginx
nginx -s reload
```
## ```servlet```如何处理跨域请求
* 使用过滤器
```sh
```
* 使用拦截器
```sh
```
## ```nginx```配置
```sh
# 当用户请求 http://localhost/example 时，这里的 $uri 就是 /example
# try_files 会到/root也就是项目代码安装目录查找该文件，如果能找到就直接把这个文件的内容发送给用户；如果找不到继续看 root/example/ 目录
# 还是找不到发起请求 http://localhost/index.php?$uname=oppo
location /{
    try_files $uri $uri/ /index.php?$uname=oppo;
}

# 解决 vue 部署之后404错误
# vue 路由必须先加载 index.html 或者 xx.js 才能识别到路由 ???
# vue 在访问页面的时候只能访问默认页面和通过项目内跳转其他页面 ???
location /{
    try_files $uri $uri/ /index.html;
}
````
## 数据库修改最大连接数
```sh
# 显示最大连接数
mysql> show variables like "max_connections";
+-----------------+-------+
| Variable_name   | Value |
+-----------------+-------+
| max_connections | 1500  |
+-----------------+-------+
# 显示当前连接数
mysql> show processlist;
+----+------+-----------+------+---------+------+----------+------------------+
| Id | User | Host      | db   | Command | Time | State    | Info             |
+----+------+-----------+------+---------+------+----------+------------------+
|  2 | root | localhost | NULL | Sleep   |  666 |          | NULL             |
|  3 | root | localhost | NULL | Query   |    0 | starting | show processlist |
+----+------+-----------+------+---------+------+----------+------------------+
# 设置最大连接数1000(命令行设置重启之后可能失效)
mysql> set global max_connections=1000;
mysql> show variables like "max_connections";
+-----------------+-------+
| Variable_name   | Value |
+-----------------+-------+
| max_connections | 1000  |
+-----------------+-------+
# 也可以配置文件修改 /etc/my.cnf
[mysqld]
max_connections = 1500
# 设置服务器关闭非交互连接之前等待活动的秒数
mysql> set global wait_timeout=20;
mysql> show variables like "wait_timeout";
+---------------+-------+
| Variable_name | Value |
+---------------+-------+
| wait_timeout  | 20 |
+---------------+-------+
# 设置服务器关闭交互式连接前等待活动的秒数
mysql> set global interactive_timeout=20;
mysql> show variables like "interactive_timeout";
# 同时设置interactive_timeout和wait_timeout才会生效
# 下面配置解决druid报“Too many connections”错误
[mysqld]
max_connections = 1500
wait_timeout = 20
interactive_timeout = 20
```
## ```win10```杀死进程
```sh
# 根据端口号找到进程
C:\Users\Harrdy>netstat -ano | findstr 3306
  TCP    0.0.0.0:3306           0.0.0.0:0              LISTENING       3356
  TCP    [::]:3306              [::]:0                 LISTENING       3356
# 根据进程查看任务名
C:\Users\Harrdy>tasklist | findstr 3356
mysqld.exe                    3356 Services                   0     42,228 K
# 杀死进程(用管理员权限)
taskkill -F /PID 3356
# 启动服务
net start mysql
```
* centos
```sh
# l 表示监听 n 表示不解析域名 t 表示tcp p 表示进程ID
[root@ECS ~]# netstat -lntp
Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name
tcp6       0      0 :::8080                 :::*                    LISTEN      20745/java
```
## ```sql```相关
```sql
2、查看整个表的结构
mysql> show create table tb_user;
3、修改现有表的注释
mysql> alter table tb_user comment="这是用户列表";
4、查看表的注释
mysql> select table_name,table_comment from information_schema.tables where table_name='tb_user';
5、修改现有列，加上注释
mysql> alter table tb_user modify column gender varchar(30) comment '用户性别~';
6、 查看所有列的注释
mysql> show full columns from tb_user;
7、查看指定列的注释
mysql> select column_name, column_comment from information_schema.columns where table_name='tb_user';
```
* 表名和字段名用单引号和双引号都不对，要么都不用引号，要么就用反引号
```sql
create table `tb_user`(
    `id` int auto_increment comment '用户ID',
    `username` varchar(20) comment '用户名',
    `password` varchar(20) comment '用户密码',
    `gender` varchar(20) comment '用户性别',
    `addr` varchar(20) comment '用户地址',
    primary key(`id`)) comment='这是用户表';
```
## ```tar```命令
```sh
# 将tar.gz文件解压到指定目录
# -x extract files from an archive 从压缩包里提取文件
# -z 是否需要用gzip压缩
# -v 压缩的过程中显示档案
# -f 置顶文档名，在f后面立即接文件名，不能再加参数
tar -zxvf apache-tomcat-9.0.37.tar.gz --directory=/usr/local/production/backend
```
