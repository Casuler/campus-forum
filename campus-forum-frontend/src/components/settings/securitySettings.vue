<script setup>
import {reactive} from "vue";
import {Message, Lock} from "@element-plus/icons-vue";

const securityForm = reactive({
  email: '',
  password_origin: '',
  password_new: '',
  password_new_repeat: ''
})
</script>

<template>
  <div>
    <div>
      <h1><el-icon><Message/></el-icon> 邮箱设置</h1>
      <el-form
          label-position="top"
          label-width="100px"
          :model="securityForm"
          style="max-width: 460px;font-weight: bold">
        <el-form-item label="邮箱地址">
          <el-input :prefix-icon="Message" v-model="securityForm.email"/>
        </el-form-item>
      </el-form>
      <el-button type="success">保存邮件设置</el-button>
    </div>
    <el-divider/>
    <div>
      <h1><el-icon><Lock/></el-icon> 密码设置</h1>
      <el-form
          label-position="top"
          label-width="100px"
          :model="securityForm"
          style="max-width: 460px;font-weight: bold">
        <el-form-item label="当前密码">
          <el-input :prefix-icon="Lock" show-password v-model="securityForm.password_origin"/>
        </el-form-item>
        <el-form-item label="新密码">
          <el-input :prefix-icon="Lock" show-password v-model="securityForm.password_new"/>
        </el-form-item>
        <el-form-item label="重复新密码">
          <el-input :prefix-icon="Lock" show-password v-model="securityForm.password_new_repeat"/>
        </el-form-item>
      </el-form>
      <el-button type="success">修改密码</el-button>
    </div>
  </div>

</template>

<style scoped>

</style>