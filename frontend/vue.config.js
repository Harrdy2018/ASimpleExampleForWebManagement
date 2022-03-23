module.exports = {
    lintOnSave: false, // 关闭eslint检测
    devServer: {
      host: '127.0.0.1',
      port: 8085, // 项目运行时候的端口号
      open: true, // 项目启动时是否自动打开浏览器
      proxy: {
        // 接口以/api开头的请求，会被代理到http://127.0.0.1:8080/api/management/queryAllUser
        '/api': {
          target: 'http://127.0.0.1:8080/',
          changeOrigin: true, // 是否容许跨域
          pathRewrite: {
            '^/api': '' // 重写路径代理到http://127.0.0.1:8080/management/queryAllUser
          }
        }
      }
    },
    configureWebpack: config => {
      console.log(`区分环境信息${process.env.NODE_ENV}`)
      if (process.env.NODE_ENV === 'production') {
        console.log("enter production...")
      }

      if (process.env.NODE_ENV === 'development') {
        console.log("enter devlopment...")
      }

      if (process.env.NODE_ENV === 'test') {
        console.log("enter test...")
      }
    }
}