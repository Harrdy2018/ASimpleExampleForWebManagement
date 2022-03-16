import { createStore } from 'vuex'

export default createStore({
  state: {
    count: 1
  },
  mutations: {
    add(state){
      console.log(this)
      state.count++;
    },
    reduce(state){
      state.count--;
    }
  },
  actions: {
  },
  modules: {
  }
})
