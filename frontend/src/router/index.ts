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
  history: createWebHistory(),
  routes
})

export default router
