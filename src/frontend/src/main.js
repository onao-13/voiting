import { createApp } from 'vue'
import store from './store'
import router from './router'
import App from './App.vue'
import components from '@/components/UI'

const app = createApp(App)

components.forEach(component => {
 app.component(component.name, component)
})

app.use(store).use(router).mount('#app')


