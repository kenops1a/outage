<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 21/1/2022
-->
<template>
  <v-main>
    <v-card class="mx-auto my-12" max-height="400px" max-width="400px" color="black" dark>
      <v-btn @click="stringToDate"> 点击将日期字符串转换为日期对象 </v-btn>
      <v-text-field :value="str"></v-text-field>
    </v-card>
  </v-main>
</template>

<script>

Date.prototype.format = function(fmt) {
  var o = {
    "M+" : this.getMonth()+1,                 //月份
    "d+" : this.getDate(),                    //日
    "h+" : this.getHours(),                   //小时
    "m+" : this.getMinutes(),                 //分
    "s+" : this.getSeconds(),                 //秒
    "q+" : Math.floor((this.getMonth()+3)/3), //季度
    "S"  : this.getMilliseconds()             //毫秒
  };
  if(/(y+)/.test(fmt)) {
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
  }
  for(var k in o) {
    if(new RegExp("("+ k +")").test(fmt)){
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    }
  }
  return fmt;
}
export default {
  name: "StringToDate",
  data () {
    return {
      str: '2020-05-12 21:12:15',
      timeList: [
        { label: '年', value: '2020' },
        { label: '月', value: '05' },
        { label: '日', value: '12' },
        { label: '时', value: '21' },
        { label: '分', value: '12' },
      ],
    }
  },
  methods: {
    stringToDate () {
      // this.str = this.str.replace(/-/g, '/')
      // this.str = new Date(this.str)
      // console.log(this.str)
      let timeStr = this.timeList[0].value + '-' + this.timeList[1].value + '-' + this.timeList[2].value + ' ' + this.timeList[3].value + ':' + this.timeList[4].value
      // timeStr = timeStr.replace(/-/g, '/')
      // this.str = new Date(timeStr)
      this.str = new Date().format('yyyy-MM-dd hh:mm:ss')
    }
  }
}
</script>

<style scoped>

</style>
