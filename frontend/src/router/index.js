import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

import Register from '../views/registration/Register.vue'
import RegisterReader from '../views/registration/RegisterReader.vue'
import RegisterWriter from '../views/registration/RegisterWriter.vue'

import Login from '../views/login/Login.vue'

import Writers from '../views/writer/Writers.vue'
import Readers from '../views/reader/Readers.vue'

import Books from '../views/book/Books.vue'
import BooksByWriter from '../views/book/BooksByWriter.vue'
import AddBook from '../views/book/AddBook.vue'
import UpdateBook from '../views/book/UpdateBook.vue'

import Genres from '../views/genre/Genres.vue'
import AddGenre from '../views/genre/AddGenre.vue'

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
    path: '/writers',
    name: 'Writers',
    component: Writers
  },
  {
    path: '/readers',
    name: 'Readers',
    component: Readers
  },
  {
    path: '/addBook',
    name: 'AddBook',
    component: AddBook
  },
  {
    path: '/updateBook/:id',
    name: 'UpdateBook',
    component: UpdateBook
  },
  {
    path: '/books',
    name: 'Books',
    component: Books
  },
  {
    path: '/booksByWriter',
    name: 'BooksByWriter',
    component: BooksByWriter
  },
  {
    path: '/genres',
    name: 'Genres',
    component: Genres
  },
  {
    path: '/addGenre',
    name: 'AddGenre',
    component: AddGenre
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
