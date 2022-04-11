<!--
* @Descripttion:
* @Project: front
* @Tip: write_bug
* @Author: yaominc
* @Date: 11/4/2022 上午11:15
-->
<template>
  <v-main style="height: 100vh; width: 100vw">
    <v-card color="pa-8 pb-16 pt-10" style="height: 100%; width: 100%;">
      <!-- 查看已提交的认证 -->
      <div>
        <div>
          <v-row class="ml-4 mb-n3 mt-8">
            <h2>主持人认证申请审批</h2>
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
              </tr>
              </thead>
              <tbody>
              <tr v-for="(assetRecord, index) in assetRecordList" :key="index">
                <td v-for="(item, index) in tableHeader" :key="index" class="mb-2">
                  {{ assetRecord[item.value] }}
                </td>
                <td>
                  <v-btn v-if="assetRecord.status === '申请中'" color="primary" class="mt-2 mr-1" @click="updateRecord(assetRecord, '1')">通过</v-btn>
                  <v-btn v-if="assetRecord.status === '申请中'" color="error" class="mt-2 mr-1" @click="updateRecord(assetRecord, '2')">驳回</v-btn>
                </td>
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
import { getAssetRecordList, updateAssetRecord } from "@/api/user/user";

export default {
  name: "AssetList",
  data () {
    return{
      tableHeader: [
        { text: '用户id', value: 'createBy' },
        { text: '主持类型', value: 'type' },
        { text: '状态', value: 'status' },
        { text: '创建时间', value: 'createTime' },
        { text: '认证文件' }
      ],
      assetRecordList: []
    }
  },
  created() {
    getAssetRecordList().then(res => {
      res.record.forEach(item => {
        if (item.status === '0') {
          item.status = '申请中'
        } else if (item.status === '2') {
          item.status = '驳回'
        } else {
          item.status = '认证通过'
        }
      })
      this.assetRecordList = res.record
    })
  },
  methods: {
    updateRecord (val, status) {
      let hostModel = {
        createBy: null,
        status: null
      }
      hostModel.createBy = val.createBy
      hostModel.status = status
      updateAssetRecord(hostModel).then(res => {
        if (res.success) {
          getAssetRecordList().then(res => {
            res.record.forEach(item => {
              if (item.status === '0') {
                item.status = '申请中'
              } else if (item.status === '2') {
                item.status = '驳回'
              } else {
                item.status = '认证通过'
              }
            })
            this.assetRecordList = res.record
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
