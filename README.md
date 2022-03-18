# ASimpleExampleForWebManagement
一个简单的管理页面
## ```servlet```如何处理跨域请求
* 使用过滤器
```sh
```
* 使用拦截器
```sh
```
## 部署到服务器
```sh
# 前端打包
scp -r /a/ASimpleExampleForWebManagement/frontend/dist/ root@47.96.251.225:/home/www/
# 后台打包
scp /a/ASimpleExampleForWebManagement/backend/target/management.war root@47.96.251.225:/usr/local/apache-tomcat-9.0.37/webapps/
# 重启nginx
nginx -s reload
```