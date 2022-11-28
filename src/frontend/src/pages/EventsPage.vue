<template>
 <main-header>Список ивентов</main-header>
  <div class="events__list">
   <div class="event__item" v-for="event in EVENTS" :key="event.id">
    <router-link class="event__link" :to="`/admin/edit/${event.id}`">
      {{event.title}}
     </router-link>
   </div>
  <router-link class="event__item" :to="'/admin/make'">
    <svg width="101" height="101" viewBox="0 0 101 101" fill="none" xmlns="http://www.w3.org/2000/svg">
      <path d="M37.6409 0V37.6409H0V62.7348H37.6409V100.376H62.7348V62.7348H100.376V37.6409H62.7348V0H37.6409Z" fill="black"/>
    </svg>
  </router-link>
  </div>
</template>

<script>

import { mapGetters, mapActions } from 'vuex';
export default {
  components: {  },

  methods: {
    ...mapActions([
      'GET_EVENTS_FROM_API'
    ])
  },

  computed: {
   ...mapGetters([
     'EVENTS',
   ]),
 },

 mounted() {
  this.GET_EVENTS_FROM_API()
 }

}
</script>

<style lang="scss" scoped>
.events__list{
 justify-content: center;
 align-items: center;
 display: flex;
 gap: 60px;
 margin-top: 30px;
 flex-wrap: wrap;
}
.event__item {
  height: fit-content;
  min-width: 200px;
  max-width: 300px;
  background: rgba(0, 0, 0, 0.692);
  border-radius: 5px;
  text-align: center;
  cursor: pointer;
  padding: 20px;
  transition: all .2s ease-in-out;
  border: 1px solid rgba(0, 0, 0, 0.692);

  svg {
    width: 50px;
    height: 50px;

    path {
      transition: all .2s ease-in-out;
      fill: #fff;
    }
  }

  &:hover {
    background: #fff;
    .event__link {
      color: rgba(0, 0, 0, 0.692);
    }

    svg {
      path {
        fill: rgba(0, 0, 0, 0.692);
      }
    }
  }
}

.event__link {
  display: block;
  width: 100%;
  font-size: 18px;
  font-weight: 700;
  text-decoration: none;
  color: #fff;
  transition: all .2s ease-in-out;
}
</style>