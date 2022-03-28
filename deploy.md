### 项目部署方式一
#### 容器
* nginx:8085(前端)+tomcat:8080(后台)
```sh
前端代码打包生成webmanage目录(默认是dist目录),丢到/home/www目录 nginx暴露8085端口
后台代码打包,tomcat部署后台代码暴露8080端口
```
#### nginx配置
```sh
server {
        listen       8085;
        listen       [::]:8085;
        server_name  _;
        root         /home/www;
        include /etc/nginx/default.d/*.conf;
        location /{
             try_files $uri $uri/ /index.html;
        }
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
```
#### 请求过程
```sh
前端发起请求 http://47.96.251.225:8085/webmanage/
    找nginx配置root /home/www/webmanage/目录下的html文件，形成页面
        点击页面发起查询请求 http://47.96.251.225:8085/api/management/queryAllUser
            匹配location规则(location加斜杠、proxy_pass加斜杠)
                    location /api/{
                        proxy_pass http://127.0.0.1:8080/;
                    }
            代理到 URL=http://127.0.0.1:8080/management/queryAllUser 发起请求
                    tomcat收到请求 URL=http://127.0.0.1:8080/management/queryAllUser


http://47.96.251.225:8085/api/management/queryAllUser
# location(任意)、proxy_pass不加斜杠
location /api{
    proxy_pass http://127.0.0.1:8080;
}
实际访问代理地址--->http://127.0.0.1:8080/api/management/queryAllUser

# location不加斜杠，proxy_pass加斜杠
location /api{
    proxy_pass http://127.0.0.1:8080/;
}
实际访问代理地址--->http://127.0.0.1:8080//management/queryAllUser

# location、proxy_pass都加斜杠
location /api/{
    proxy_pass http://127.0.0.1:8080/;
}
实际访问代理地址--->http://127.0.0.1:8080/management/queryAllUser

# location不加斜杠，proxy_pass加"v1"
location /api{
    proxy_pass http://127.0.0.1:8080/v1;
}
实际访问代理地址--->http://127.0.0.1:8080/v1/management/queryAllUser

# location加斜杠，proxy_pass加"v1"
location /api/{
    proxy_pass http://127.0.0.1:8080/v1;
}
实际访问代理地址--->http://127.0.0.1:8080/v1management/queryAllUser

# location不加斜杠，proxy_pass加"v1/"
location /api{
    proxy_pass http://127.0.0.1:8080/v1/;
}
实际访问代理地址--->http://127.0.0.1:8080/v1//management/queryAllUser

# location加斜杠，proxy_pass加"v1/"
location /api/{
    proxy_pass http://127.0.0.1:8080/v1/;
}
实际访问代理地址--->http://127.0.0.1:8080/v1/management/queryAllUser

## 总结
proxy_pass代理地址端口后无任何字符，转发后地址=代理地址+访问URL目录部分
proxy_pass代理地址端口后有目录(包括 / )，转发后地址=代理地址+访问URL目录部分去除location匹配目录
```
#### ```location```匹配规则
* =
```sh
location = /abcd{
    proxy_pass http://127.0.0.1:8080/yes;
}

匹配情况:
    http://47.96.251.225:8085/abcd        # 正好完全匹配         tomcat收到 http://127.0.0.1:8080/yes
    http://47.96.251.225:8085/ABCD        # 如果运行 Nginx server 的系统本身对大小写不敏感，比如 Windows ，那么也匹配
    http://47.96.251.225:8085/abcd?uname=oppo    # 匹配          tomcat收到 http://127.0.0.1:8080/yes?uname=oppo 
    http://47.96.251.225:8085/abcd/    # 不匹配，因为末尾存在反斜杠（trailing slash），Nginx 不认为这种情况是完全匹配
    http://47.96.251.225:8085/abcde    # 不匹配，因为不是完全匹配
```
* none 匹配那些以指定的 patern 开头的 URI
```sh
location /abcd{
    proxy_pass http://127.0.0.1:8080/yes;
}

匹配情况：
    http://47.96.251.225:8085/abcd        # 正好完全匹配       tomcat收到 http://127.0.0.1:8080/yes 
    http://47.96.251.225:8085/ABCD        # 如果运行 Nginx server 的系统本身对大小写不敏感，比如 Windows ，那么也匹配
    http://47.96.251.225:8085/abcd?uname=oppo     # 匹配       tomcat收到 http://127.0.0.1:8080/yes?uname=oppo 
    http://47.96.251.225:8085/abcd/       # 匹配               tomcat收到 http://127.0.0.1:8080/yes/
    http://47.96.251.225:8085/abcde       # 匹配               tomcat收到 http://127.0.0.1:8080/yese
```
* ~ 这个```location modifier```对大小写敏感，且```pattern```须是正则表达式
```sh
# ^表示匹配的字符串以什么开头
# $表示匹配的字符串以什么结尾
location ~ ^/abcd$ {
    proxy_pass http://127.0.0.1:8080;
}

匹配情况:
    http://47.96.251.225:8085/abcd        # 完全匹配       tomcat收到 http://127.0.0.1:8080/abcd
    http://47.96.251.225:8085/ABCD        # 不匹配，~ 对大小写是敏感的
    http://47.96.251.225:8085/abcd?uname=oppo    # 忽略查询串参数（query string arguments）tomcat收到 http://127.0.0.1:8080/abcd?uname=oppo
    http://47.96.251.225:8085/abcd/    # 不匹配，因为末尾存在反斜杠（trailing slash），并不匹配正则表达式 ^/abcd$
    http://47.96.251.225:8085/abcde    # 不匹配正则表达式 ^/abcd$
```
* ```~*``` 类似 ```~``` 这个```location modifier```对大小写敏感，且```pattern```须是正则表达式
```sh
location ~* ^/abcd$ {
    proxy_pass http://127.0.0.1:8080;
}

匹配情况:
    http://47.96.251.225:8085/abcd        # 完全匹配                tomcat收到 http://127.0.0.1:8080/abcd
    http://47.96.251.225:8085/ABCD        # 匹配，~* 不区分大小写   tomcat收到 http://127.0.0.1:8080/ABCD
    http://47.96.251.225:8085/abcd?uname=oppo    # 忽略查询串参数（query string arguments）tomcat收到 http://127.0.0.1:8080/abcd?uname=oppo
    http://47.96.251.225:8085/abcd/    # 不匹配，因为末尾存在反斜杠（trailing slash），并不匹配正则表达式 ^/abcd$
    http://47.96.251.225:8085/abcde    # 不匹配正则表达式 ^/abcd$
```
* ```^~ ``` 类似 ```none``` 
```sh
# 以指定匹配模式开头的 URI 被匹配，不同的是，一旦匹配成功，那么 Nginx 就停止去寻找其他的 Location 块进行匹配了（与 Location 匹配顺序有关）
location ^~ /abc {
    proxy_pass http://127.0.0.1:8080;
}
```
* ```@```
```sh
# 用于定义一个 Location 块，且该块不能被外部 Client 所访问，只能被 Nginx 内部配置指令所访问，比如 try_files or error_page
```
### exampleA
* 准备文件
```sh
[root@ECS www]# cat /home/www/dev/index.html
this is /home/www/dev/index.html
[root@ECS www]# cat /home/www/test/index.html
this is /home/www/test/index.html
```
* nginx配置
```sh
location / {
    root /home/www/dev;
    index index.php index.html;
}

# \. 转意符号只匹配点号，因为单独使用.号是匹配除换行符以外的任意字符
location ~ \.html$ {
    root /home/www/test;
}

发起请求 http://47.96.251.225:8085/
   那么首先会访问到“/”的location，结合root与index指令，会先判断/home/www/dev/index.php是否存在
   如果不，则接着查看/home/www/dev/index.html
   如果存在，则使用http://47.96.251.225:8085/index.html发起内部重定向
   就像从客户端再一次发起请求一样
       Nginx会再一次搜索location，毫无疑问匹配到第二个~ \.html$，从而访问到/home/www/test/index.html
```
#### ```rewrite```
```sh
# 发起请求 http://47.96.251.225:8085/api/management/queryAllUser
     # 回应客户端 Location: http://www.baidu.com
           # 客户端发起请求 http://www.baidu.com
# permanent--->301 Moved Permanently
location /api/ {
    proxy_pass http://127.0.0.1:8080/;
    rewrite ^/(.*) http://www.baidu.com permanent;
}

# redirect--->302 Moved Temporarily
location /api/ {
    proxy_pass http://127.0.0.1:8080/;
    rewrite ^/(.*) http://www.baidu.com redirect;
}
```
### 项目部署方式二
#### 容器
```sh
                    tomcat:8090(前台)
nginx:8085(中转)
                    tomcat:8080(后台)

# 前端代码打包
[root@ECS webapps]# tree /usr/local/production/frontend/apache-tomcat-9.0.37/webapps
/usr/local/production/frontend/apache-tomcat-9.0.37/webapps
├── WEB-INF
│   └── web.xml
└── webmanage # 前台代码打包的目录(可以自己设定)
    ├── # 前台代码打包的文件

[root@ECS webapps]# cat ./WEB-INF/web.xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
           http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
  version="3.1" metadata-complete="true">
  <display-name>Router for Tomcat</display-name>
  <error-page>
    <error-code>404</error-code>
    <location>/index.html</location>
  </error-page>
</web-app>

# 后台代码打包
/usr/local/production/backend/apache-tomcat-9.0.37/webapps/
```
#### nginx配置
```sh
upstream frontend {
    server 127.0.0.1:8090;
}

upstream backend {
    server 127.0.0.1:8080;
}

server {
    listen       8085;
    listen       [::]:8085;
    server_name  _;
    include /etc/nginx/default.d/*.conf;
    location /webmanage {
        proxy_pass http://frontend;
    }

    location ~* ^/webmanage/.*\.(gif|jpg|jpeg|png|css|js|html|ico)$ {
        proxy_pass http://frontend;
    }

    location /api/ {
        proxy_pass http://backend/;
    }

    error_page 404 /404.html;
        location = /404.html {
    }

    error_page 500 502 503 504 /50x.html;
        location = /50x.html {
    }
}
```
#### 请求过程
```sh
前端发起请求 http://47.96.251.225:8085/webmanage/
    匹配到location规则
        location /webmanage {
            proxy_pass http://frontend;
        }
    upstream到前端tomcat(8090)
        前端tomcat(8090)收到请求  URL=http://127.0.0.1:8090/management/
        请求页面的同时script标签发起请求，请求静态文件，形成页面
        页面初次刷新在前端tomcat(8090)发起查询请求到nginx(8050) http://47.96.251.225:8085/api/management/queryAllUser
            匹配location规则
                location /api/ {
                    proxy_pass http://backend/;
                }
                upstream到 URL=http://127.0.0.1:8080/management/queryAllUser 发起请求
                        tomcat收到请求 URL=http://127.0.0.1:8080/management/queryAllUser
    ```

