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
        <v-col cols="8">
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

      <div>
        <v-row class="ml-4 mb-3">
          <h2>图片信息</h2>
        </v-row>
        <v-row class="ml-4 mb-n3">
          <v-col cols="4">
            <v-img :src="imgSrc" max-height="250" max-width="250"></v-img>
          </v-col>
          <v-col cols="4">
            <v-img :src="hostImgSrc" max-height="250" max-width="250"></v-img>
          </v-col>
        </v-row>
        <v-row class="ml-4 mb-n3">
          <v-col cols="4">
            上传主持人照片（支持.jpg,.jpeg,.png）：
            <v-file-input
                accept=".jpg,.jpeg,.png"
                :rules="rules"
                label="上传图片"
                @change="getFile"
            ></v-file-input>
          </v-col>
          <v-col cols="4" class="mt-6">
            <v-btn @click="uploadImage">上传</v-btn>
          </v-col>
        </v-row>
      </div>

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
import { uploadImg } from "@/api/user/user";

export default {
  name: "HostInfo",
  data () {
    return {
      dialog: false,
      imgSrc: require('@/assets/jpg/default_image.jpg'),
      hostImgSrc: null,
      httpPrefix: 'http://localhost:8089',
      imgModel: {
        userId: this.$store.state.userNow.id,
        img: null,
      },
      updateLabel: {
        label: null,
        value: null,
        data: null
      },
      hostModel: null,
      showList: [
        { label: '费用', value: 'money' },
        { label: '主持人简介', value: 'text' },
      ],
      rules: [
        value => !value || value.size < 2000000 || 'Avatar size should be less than 2 MB!',
      ],
      headers: {
        'Content-Type': 'multipart/form-data',
        'token': this.$store.state.token
      }
    }
  },
  created() {
    this.getHost()
  },
  methods: {
    getFile (val) {
      console.log(val)
      this.imgModel.img = val
    },
    updateHostInfo (val) {
      this.dialog = true
      this.updateLabel = val
    },
    uploadImage () {
      let formData = new FormData()
      formData.append('userId', this.imgModel.userId)
      formData.append('img', this.imgModel.img)
      let formConfig = this.headers
      uploadImg(formData, formConfig).then(res => {
        console.log(res)
        let imgStr = this.httpPrefix + res.record
        this.hostImgSrc = imgStr
      })
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
