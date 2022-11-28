import { createRouter, createWebHashHistory } from 'vue-router'
import HomePage from '../pages/HomePage'
import EventsPage from '../pages/EventsPage'
import MakeEvent from '../pages/MakeEvent'
import EditEventPage from '../pages/EditEventPage'
import EventsPageItem from '@/components/EventsPageItem'

const routes = [
  {
    path: '/admin',
    name: 'Home',
    component: HomePage
  },
  {
    path: '/admin/events',
    name: 'EventsPage',
    component: EventsPage
  },
  {
    path: '/admin/make',
    name: 'MakeEvent',
    component: MakeEvent
  },
  {
    path: '/admin/edit/:id',
    name: 'EditEventPage',
    component: EditEventPage
  },
  {
    path: '/admin/event/:id',
    name: 'EventsPageItem',
    component: EventsPageItem,
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
  mode: 'history'
})

export default router