<!--
* @Descripttion:
* @Project: front
* @Tip: write_bug
* @Author: yaominc
* @Date: 10/4/2022 下午10:25
-->
<template>
  <v-main style="height: 100vh; width: 100vw">
    <v-card color="pa-8 pb-16 pt-10" min-width="100%" min-height="100%">
      <!-- 页面标题 -->
      <v-row class="ml-4 mb-n3">
        <h2>主持人信息</h2>
      </v-row>

      <!-- 信息列表 -->
      <v-row>
        <v-col cols="6">
          <v-simple-table>
            <template v-slot:default>
              <tbody>
              <tr>
                <td>昵称:</td>
                <td>
                  {{ $store.state.userNow.nick }}
                </td>
              </tr>
              <tr v-for="(item, index) in showList" :key="index">
                <td>{{ item.label }}:</td>
                <td>
                  {{ hostModel[item.value] }}
                  <v-icon size="16" color="blue-grey" class="ml-2" @click="updateHostInfo(item)">{{ "mdi mdi-pencil" }}</v-icon>
                </td>
              </tr>
              <tr>
                <td>认证状态:</td>
                <td>
                  {{ hostModel.status }}
                </td>
              </tr>
              <tr>
                <td>认证时间:</td>
                <td>
                  {{ hostModel.createTime }}
                </td>
              </tr>
              </tbody>
            </template>
          </v-simple-table>
        </v-col>
      </v-row>

      <!-- dialog对话框 -->
      <v-row justify="center">
        <v-dialog v-model="dialog" max-width="290">
          <v-card class="text-center pa-4">
            <span>修改{{ updateLabel.label }}</span>
            <v-form ref="updateValid">
              <v-text-field v-model="updateLabel.data" :placeholder="updateLabel.value"></v-text-field>
            </v-form>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="handleClose">
                取消
              </v-btn>
              <v-btn color="green darken-1" text @click="handleEnter(updateLabel.value, updateLabel.data)">
                确认
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>

    </v-card>
  </v-main>
</template>

<script>
import { getHostById, updateHostInfo } from "@/api/host/host";

export default {
  name: "HostInfo",
  data () {
    return {
      dialog: false,
      updateLabel: {
        label: null,
        value: null,
        data: null
      },
      hostModel: null,
      showList: [
        { label: '费用', value: 'money' },
      ],
    }
  },
  created() {
    this.getHost()
  },
  methods: {
    updateHostInfo (val) {
      this.dialog = true
      this.updateLabel = val
    },
    handleClose () {
      this.dialog = false
      this.updateLabel = {
        label: null,
        value: null,
        data: null
      }
    },
    handleEnter (label, value) {
      let updateModel = {}
      updateModel[label] = value
      updateModel.email = this.$store.state.userNow.email
      updateModel.createBy = this.$store.state.userNow.id
      updateHostInfo(updateModel).then(res => {
        console.log(res)
        this.getHost()
      })
      this.dialog = false
    },
    getHost () {
      getHostById(this.$store.state.userNow.id).then(res => {
        this.hostModel = res.record
        let status = res.record.status
        if (res.record.status === '1') {
          res.record.status = '认证完成'
        } else {
          res.record.status = '认证已失效'
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
