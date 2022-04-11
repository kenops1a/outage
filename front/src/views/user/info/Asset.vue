<!--
* @Descripttion:
* @Project: front
* @Tip: write_bug
* @Author: yaominc
* @Date: 10/4/2022 下午3:29
-->
<template>
  <v-main style="height: 100vh; width: 100vw">
    <v-card color="pa-8 pb-16 pt-10" style="height: 100%; width: 100%;">
      <!-- 认证提交 -->
      <div v-if="assetRecord == null">
        <div>
          <v-row class="ml-4 mb-n3">
            <h2>主持人认证申请</h2>
          </v-row>
        </div>
        <div>
          <v-form class="ml-4 mb-n3 mt-8">
            <v-row>
              <v-col cols="4" class="pl-3 pt-0 pr-3">
                <!-- 自动获取用户昵称 -->
                用户昵称：<v-text-field placeholder="请输入昵称" disabled :value="assetForm.nick"></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="4" class="pl-3 pt-0 pr-3">
                主持类型：
                <v-select :items="typeSelect" v-model="assetForm.type" label="类型"></v-select>
              </v-col>
            </v-row>
            <!-- 上传文件 -->
            <v-row>
              <v-col cols="4" class="pl-3 pt-0 pr-3">
                上传主持人证书（支持.doc,.docx,.pdf,最大2mb）：
                <v-file-input
                    accept=".doc,.docx,.pdf"
                    :rules="rules"
                    label="上传文件"
                    @change="getFile"
                ></v-file-input>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="4" class="pl-3 pt-0 pr-3">
                <v-btn @click="handleSubmit">提交申请</v-btn>
              </v-col>
            </v-row>
          </v-form>
        </div>
      </div>
      <!-- 查看已提交的认证 -->
      <div v-if="assetRecord !== null">
        <div>
          <v-row class="ml-4 mb-n3 mt-8">
            <h2>已提交的认证申请</h2>
          </v-row>
        </div>
        <div>
          <v-simple-table>
            <template v-slot:default>
              <thead>
              <tr>
                <th v-for="(item, index) in tableHeader" :key="index">
                  {{ item.text }}
                </th>
                <th>
                  认证文件
                </th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <td v-for="(item, index) in tableHeader" :key="index" class="mb-2">
                  {{ assetRecord[item.value] }}
                </td>
                <td>
                  <a :href="'http://localhost:8089' + assetRecord.fileModel.httpPath">{{ assetRecord.fileModel.fileName }}</a>
                </td>
                <v-btn v-if="assetRecord.status !== '认证通过'" class="mt-2 mr-1" color="red" dark small @click="handleAssetDel">撤销申请</v-btn>
              </tr>
              </tbody>
            </template>
          </v-simple-table>
        </div>
      </div>
    </v-card>
  </v-main>
</template>

<script>
import { hostAsset, getAssetFile, getAssetRecord, delAsset } from "@/api/user/user";
import { success } from "@/utils/snackBar";

export default {
  name: "Asset",
  data () {
    return {
      assetForm: {
        id: this.$store.state.userNow.id,
        nick: this.$store.state.userNow.nick,
        type: null,
        file: null
      },
      assetRecord: null,
      typeSelect: [
        '婚礼', '生日', '测试', '中文'
      ],
      tableHeader: [
        { text: '昵称', value: 'nick' },
        { text: '主持类型', value: 'type' },
        { text: '状态', value: 'status' },
        { text: '创建时间', value: 'createTime' }
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
    this.handleAssetSubmit()
  },
  updated() {
    console.log(this.assetForm)
  },
  methods: {
    getFile (val) {
      console.log(val)
      this.assetForm.file = val
      console.log(this.assetForm)
    },
    handleSubmit () {
      console.log(`handleSubmit`)
      let formData = new FormData()
      formData.append('file', this.assetForm.file)
      formData.append('userId', this.assetForm.id)
      formData.append('type', this.assetForm.type)

      let formConfig = this.headers
      hostAsset(formData, formConfig).then(() => {
        success('提交申请成功')
        this.handleAssetSubmit()
      }).catch(e => {

      })
    },
    handleAssetSubmit () {
      getAssetRecord(this.$store.state.userNow.id).then(res => {
        console.log(res)
        if (res.record.status === '0') {
          res.record.status = '申请中'
        } else if (res.record.status === '1') {
          res.record.status = '认证通过'
        } else {
          res.record.status = '认证失败'
        }
        this.assetRecord = res.record
        this.assetRecord.nick = this.$store.state.userNow.nick
      })
      // getAssetFile(this.$store.state.userNow.id).then(res => {
      //   console.log(res)
      // })
    },
    handleAssetDel () {
      delAsset(this.$store.state.userNow.id).then(res => {
        if (res.errorCode === 200) {
          success('撤销申请成功')
          this.assetRecord = null
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
