<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 7/1/2022
-->
<template>
  <form id="form" class="form">
    <h2>Register With US</h2>
    <!--用户名文本框-->
    <div class="form-control">
      <!--label如果您在 label 元素内点击文本，就会触发此控件-->
      <label for="username">Username</label>
      <input id="username" type="text" placeholder="Enter username">
      <!--<small> 标签将旁注 (side comments) 呈现为小型文本。-->
      <small>Error message</small>
    </div>
    <!--邮箱文本框-->
    <div class="form-control">
      <label for="email">Email</label>
      <input id="email" type="text" placeholder="Enter mail">
      <small>Error message</small>
    </div>
    <!--密码-->
    <div class="form-control">
      <label for="password">Password</label>
      <input id="password" type="password" placeholder="Enter password">
      <small>Error message</small>
    </div>
    <!--确认密码-->
    <div class="form-control">
      <label for="password2">Confirm Password</label>
      <input id="password2" type="password" placeholder="Enter password again">
      <small>Error message</small>
    </div>
    <!--提交按钮-->
    <button type="submit">Submit</button>
  </form>
</template>

<script>
export default {
  name: "BorderTest"
}
</script>

<style scoped>

</style>
