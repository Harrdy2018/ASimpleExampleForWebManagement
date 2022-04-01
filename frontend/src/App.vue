<template>
  <div id="diva">
    <router-view v-if="state.showRouter"></router-view>
  </div>
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
#diva {
  height: 100%;
}
</style>
