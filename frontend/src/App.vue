<template>
  <div id="nav">
    <router-link to="/">WebManageSystem</router-link>
  </div>
  <router-view/>
</template>

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
