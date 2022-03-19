let hostDomain: string | undefined =  process.env.VUE_APP_URL
if (hostDomain === undefined) {
  hostDomain = ""
}

window.localStorage.setItem('hostDomain', hostDomain)
const prefix = 'management'
const api = {
  login: `${prefix}/user/login`,  // 登录
  logout: `${prefix}/user/logout`, // 登出
  enums: `${prefix}/common/enums`, // 系统变量
  upload: `${prefix}/common/upload`, // 上传文件
  insertUser: `${prefix}/insertUser`, // 新增用户信息
  deleteUserById: `${prefix}/deleteUserById`, // 通过用户ID删除用户
  updateUserById: `${prefix}/updateUserById`, // 通过用户ID更新用户
  queryAllUser: `${prefix}/queryAllUser`, // 查询所有用户
  queryUserById: `${prefix}/queryUserById` // 通过用户ID单个查询用户
}

export default api
