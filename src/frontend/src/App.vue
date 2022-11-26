<template>
  <router-view></router-view>
</template>

<script>
export default {

}
</script>

<style lang="scss">
* {
 margin: 0;
 padding: 0;
 box-sizing: border-box;
}

h1 {
 text-align: center;
}

#app {
 font-family: Avenir, Helvetica, Arial, sans-serif;
 -webkit-font-smoothing: antialiased;
 -moz-osx-font-smoothing: grayscale;
 color: #2c3e50;
}

.container {
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 10px;
}

.btn {
  background: rgba(0, 0, 0, 0.692);
  border: 1px solid rgba(0, 0, 0, 0.692);
  cursor: pointer;
  padding: 8px 12px;
  color: #fff;
  font-weight: 500;
  font-size: 16px;
  transition: all .2s ease-in-out;

  &:hover {
    color: rgba(0, 0, 0, 0.692);
    background: #fff;
  }
}
</style>