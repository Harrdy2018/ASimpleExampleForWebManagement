# frontend

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).

### 安装```ElementPlus```
```sh
# --save 将插件安装到package.json里面的dependencies
npm install element-plus --save
```
### ```fetch API```使用
* ```get```请求
```js
fetch('http://localhost:3000/fdata').then(function(data){
    // text() 方法属于fetch API一部分 返回 promise实例对象
    return data.text();
}).then(function(data){
    console.log(data)
})
```
* ```post```请求
```js
fetch('http://localhost:3000/fdata', {
    method: 'post',
    body: 'uname=lisi&pwd=123',
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
    }
}).then(function(data){
    return data.text();
}).then(function(data){
    console.log(data)
})

fetch('http://localhost:3000/fdata', {
    method: 'post',
    body: JSON.stringify({
        uname: 'lisi',
        age: 12
    }),
    headers: {
        'Content-Type': 'application/json'
    }
}).then(function(data){
    return data.text();
}).then(function(data){
    console.log(data)
})
```
* ```put``` 修改数据
```js
fetch('http://localhost:3000/fdata', {
    method: 'put',
    body: JSON.stringify({
        uname: 'lisi',
        age: 12
    }),
    headers: {
        'Content-Type': 'application/json'
    }
}).then(function(data){
    return data.text();
}).then(function(data){
    console.log(data)
})
```