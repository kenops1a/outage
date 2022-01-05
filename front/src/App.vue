<template>
  <div id="app">
    <TestComponent></TestComponent>
  </div>
</template>

<script>

export default {
  name: 'App',
  components: {
    TestComponent: ()=> import('@/components/test/TestComponent')
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
