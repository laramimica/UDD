import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

import Register from '../views/registration/Register.vue'
import RegisterReader from '../views/registration/RegisterReader.vue'
import RegisterWriter from '../views/registration/RegisterWriter.vue'

import Login from '../views/login/Login.vue'

import Search from '../views/search/Search.vue'
import Index from '../views/search/Index.vue'
import Result from '../views/search/Result.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
    children: [
      {
        path: 'reader',
        name: 'Register Reader',
        component: RegisterReader,
      },
      {
        path: 'writer',
        name: 'Register Writer',
        component: RegisterWriter,
      },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/search',
    name: 'Search',
    component: Search
  },
  {
    path: '/index',
    name: 'Index',
    component: Index
  },
  {
    path: '/result',
    name: 'Result',
    component: Result
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
