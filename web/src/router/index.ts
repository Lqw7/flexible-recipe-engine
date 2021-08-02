import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/home.vue'
import Search from '../views/search.vue'
import Admin from '../views/admin.vue'
import Show from '../views/show.vue'
import Substitution from '../views/substitution.vue'
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/substitution',
    name: 'Substitution',
    component: Substitution
  },
  {
    path: '/search',
    name: 'Search',
    component: Search
  },
  {
    path: '/admin',
    name: 'Admin',
    component: Admin
  },
  {
    path: '/show',
    name: 'Show',
    component: Show
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
