<template>
  <div id="nav">
    <router-link to="/">WebManageSystem</router-link>
  </div>
  <router-view v-if="state.showRouter"></router-view>
</template>
<script>
import { defineComponent, nextTick, provide, reactive, ref } from 'vue'
export default defineComponent({
  setup() {
    const state = reactive({
      showRouter: true
    })

    function reload() {
      state.showRouter = false
      nextTick(()=>{
        state.showRouter = true
      })
    }

    provide('reload', reload)

    return {
      state
    }
  }
})
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

#nav {
  padding: 30px;
  font-size: 25px;
  font-family: initial;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration-line: none;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
