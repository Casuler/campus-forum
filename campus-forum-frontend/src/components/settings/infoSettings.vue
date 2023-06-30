<script setup>
import {reactive, ref} from "vue";
import {Connection, Iphone, Link, User} from "@element-plus/icons-vue";
import {post} from "@/request/request.js";
import {ElMessage} from "element-plus";
import {useStore} from "@/stores/store.js";
const form = ref()
const store = useStore()
const infoForm = reactive({
  nickname: '',
  username: store.auth.user.username,
  sex: 'male',
  phone: '',
  qq: '',
  wechat: '',
  url: '',
  description: '',
})

const validateNickname = (rule, value, callback) => {
  if (value === '')
    callback(new Error('请输入用户名'))
  else if(!/^[a-zA-Z0-9\u4e00-\u9fa5\u3040-\u309F\u30A0-\u30FF]+$/.test(value))
    callback(new Error('用户名不能包含特殊字符，只能是中文/英文/日文'))
  else
    callback()

}

const validatePhoneNumber = (rule,value,callback) => {
  if(value !== '' && !/^1[3456789]\d{9}$/.test(value))
    callback(new Error('手机号格式错误'))
  else callback()
}

const validateUrl = (rule,value,callback) => {
  if(value !=='' && !/^[a-zA-Z]+:\/\/[^\s]*$/.test(value))
    callback(new Error('url地址格式错误'))
  else callback()
}

const rules = {
  nickname: [
    { validator: validateNickname, trigger: ['blur', 'change'] },
    { min: 2, max: 8, message: '用户名的长度必须在2-8个字符之间', trigger: ['blur', 'change'] },
  ],
  phone: [
    { validator: validatePhoneNumber, trigger: ['blur', 'change'] },
    { min: 11, max: 11, message: '请输入正确格式的11位手机号', trigger: ['blur', 'change']}
  ],
  qq: [
    { min: 6, max: 12, message: 'QQ号只能为6-12位', trigger: ['blur', 'change'] }
  ],
  wechat: [
    { min: 5, max: 30, message: '微信号只能为5-30位', trigger: ['blur', 'change'] }
  ],
  url: [
    { validator: validateUrl, trigger: ['blur', 'change']},
    { max: 50, message: 'url长度不能超过50位', trigger: ['blur', 'change'] }
  ],
  description: [
    { max: 500, message: '个人介绍长度不能超过500字', trigger: ['blur', 'change'] }
  ],
}
const save = () => {
  form.value.validate((isValid) => {
    if(isValid){
      post('/api/user/save-info', infoForm,()=>{
        ElMessage.success("保存成功")
      },'json')
    } else {
      ElMessage.warning('表单内容有误，请重新检查内容是否正确填写')
    }

  })

}
</script>

<template>
  <div>
    <el-form
        ref="form"
        :rules="rules"
        label-position="top"
        label-width="100px"
        :model="infoForm"
        style="max-width: 460px;font-weight: bold">
      <el-form-item prop="nickname" label="昵称">
        <el-input :maxlength="8" :prefix-icon="User" v-model="infoForm.nickname"/>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="infoForm.sex">
          <el-radio label="male" size="large">男</el-radio>
          <el-radio label="female" size="large">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="phone" label="手机号">
        <el-input :maxlength="11" :prefix-icon="Iphone" v-model="infoForm.phone"/>
      </el-form-item>
      <el-form-item label="个人介绍">
        <el-input :maxlength="500" type="textarea" v-model="infoForm.description" :rows="6"/>
      </el-form-item>
      <el-form-item prop="qq" label="QQ">
        <el-input :maxlength="12" :prefix-icon="Connection" v-model="infoForm.qq"/>
      </el-form-item>
      <el-form-item prop="wechat" label="微信">
        <el-input :maxlength="30" :prefix-icon="Connection" v-model="infoForm.wechat"/>
      </el-form-item>
      <el-form-item prop="url" label="网站">
        <el-input :maxlength="50" :prefix-icon="Link" v-model="infoForm.url"/>
      </el-form-item>
    </el-form>
    <el-button type="success" @click="save()">保存个人信息设置</el-button>
  </div>
</template>

<style scoped>

</style>