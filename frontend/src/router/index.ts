import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '@/views/Home.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/index.html'
    // 此处如果不用重定向的话,路由的名字要不一样
    // name: 'Login1'
    // component: () => import('@/views/Login.vue')
  },
  {
    path: '/index.html',
    name: 'Login2',
    component: () => import('@/views/Login.vue')
  },
  {
    path: '/home/:accountId',
    name: 'Home',
    component: Home
  },
  {
    path: '/user/register',
    name: 'registerUser',
    component: () => import('@/views/Register.vue')
  },
  {
    path: '/query/users',
    name: 'queryUsers',
    component: () => import('@/views/Home.vue')
  },
  {
    path: '/insert/user',
    name: 'insertUser',
    component: () => import('@/components/User.vue')
  },
  {
    path: '/edit/user/:id',
    name: 'editUser',
    component: () => import('@/components/User.vue')
  }
]

const router = createRouter({
  // 如果开发环境要部署在根目录 process.env.NODE_ENV === 'production' ? '/webmanage/' : '/'
  // 应用托管在域名 https://example.com/webmanage/
  history: createWebHistory('/webmanage/'),
  routes
})

export default router
