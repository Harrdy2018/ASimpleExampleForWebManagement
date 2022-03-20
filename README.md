# ASimpleExampleForWebManagement
* 一个简单的管理页面
## 部署到服务器
```sh
# 前端打包
scp -r /a/ASimpleExampleForWebManagement/frontend/dist/ root@47.96.251.225:/home/www/
# 后台打包
scp /a/ASimpleExampleForWebManagement/backend/target/management.war root@47.96.251.225:/usr/local/apache-tomcat-9.0.37/webapps/
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
