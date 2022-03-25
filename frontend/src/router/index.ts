import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '@/views/Home.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home
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
