module.exports = {
    devServer: {
      // 项目运行时候的端口号
      port: 8085
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