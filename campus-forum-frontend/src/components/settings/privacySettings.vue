<script setup>
import {Message} from "@element-plus/icons-vue";
import {reactive} from "vue";

const privacyForm = reactive({
  email: false,
  phone: false,
  sex: false,
  qq: false,
  wechat: false,
  url: false
})
</script>

<template>
  <div>
    <el-form
        label-position="top"
        label-width="100px"
        :model="privacyForm"
        style="max-width: 460px;font-weight: bold">
      <el-form-item>
        <el-checkbox v-model="privacyForm.email" label="是否公开展示我的邮箱" size="large"/>
      </el-form-item>
      <el-form-item>
        <el-checkbox v-model="privacyForm.sex" label="是否公开展示我的性别" size="large"/>
      </el-form-item>
      <el-form-item>
        <el-checkbox v-model="privacyForm.phone" label="是否公开展示我的手机号" size="large"/>
      </el-form-item>
      <el-form-item>
        <el-checkbox v-model="privacyForm.qq" label="是否公开展示我的QQ" size="large"/>
      </el-form-item>
      <el-form-item>
        <el-checkbox v-model="privacyForm.wechat" label="是否公开展示我的微信" size="large"/>
      </el-form-item>
      <el-form-item>
        <el-checkbox v-model="privacyForm.url" label="是否公开展示我的网站地址" size="large"/>
      </el-form-item>
    </el-form>
    <el-button type="success">保存隐私设置</el-button>
  </div>

</template>

<style lang="less" scoped>
::v-deep(.el-form-item) {
  margin-bottom: 5px;
}
</style>