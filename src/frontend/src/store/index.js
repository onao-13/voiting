import { createStore } from 'vuex'
import axios from 'axios'

export default createStore({
  state: {
    events: [
    ]
  },
  getters: {
    EVENTS(state) {
      return state.events
    }
  },
  mutations: {
    SET_EVENTS_TO_STATE(state, events) {
      state.events = events;
    },
  },
  actions: {
    GET_EVENTS_FROM_API({commit}) {
      return axios
        .get("https://voiting-server.cfapps.us10-001.hana.ondemand.com/api/event/get-all")
        .then(r => r.data)
        .then(events => {
          commit('SET_EVENTS_TO_STATE', events)
        })

        .catch((error) => {
          console.log(error)
          return error;
        })
    },
  },
  modules: {

  }
})
