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
## ```nginx```反向代理解决跨域请求
* 需求
```sh
nginx暴露8085端口
tomcat部署后台代码暴露8080端口
将 http://47.96.251.225:8085/api/management/queryAllUser 代理到 http://47.96.251.225:8080/management/queryAllUser
```
* nginx配置
```sh
 server {
        listen       8085;
        listen       [::]:8085;
        server_name  _;
        root         /home/www/dist/;
        include /etc/nginx/default.d/*.conf;
        #
        # 如果配置 location /api/
        # 例如URI为 /api/xxx 匹配到路径之后代理到 http://127.0.0.1:8080/xxx
        #
        # 如果配置 location /api
        # 则URI为 /api/xxx 匹配到路径之后代理到 http://127.0.0.1:8080//xxx
        #
        location /api/{
             proxy_pass http://127.0.0.1:8080/;
        }
        error_page 404 /404.html;
        location = /404.html {
        }
        error_page 500 502 503 504 /50x.html;
        location = /50x.html {
        }
    }

# 注意 例如URI为 /api/xxx 匹配到路径之后代理到 http://127.0.0.1:8080
location  /api/{
    proxy_pass http://127.0.0.1:8080;
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
