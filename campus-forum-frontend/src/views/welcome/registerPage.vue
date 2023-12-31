<script setup>
import { User, Lock, Message, EditPen } from '@element-plus/icons-vue'
import router from '@/router/index.js'
import { reactive, ref } from 'vue'
import {ElMessage} from 'element-plus'
import {post} from "@/request/request.js";

const form = reactive({
  username: '',
  password: '',
  password_repeat: '',
  email: '',
  code: ''
})

const validateUsername = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入用户名'))
  } else if(!/^[a-zA-Z0-9\u4e00-\u9fa5\u3040-\u309F\u30A0-\u30FF]+$/.test(value)){
    callback(new Error('用户名不能包含特殊字符，只能是中文/英文/日文'))
  } else {
    callback()
  }
}

const validatePassword = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== form.password) {
    callback(new Error("两次输入的密码不一致"))
  } else {
    callback()
  }
}

const rules = {
  username: [
    { validator: validateUsername, trigger: ['blur', 'change'] },
    { min: 2, max: 8, message: '用户名的长度必须在2-8个字符之间', trigger: ['blur', 'change'] },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 16, message: '密码的长度必须在6-16个字符之间', trigger: ['blur', 'change'] }
  ],
  password_repeat: [
    { validator: validatePassword, trigger: ['blur', 'change'] },
  ],
  email: [
    { required: true, message: '请输入邮件地址', trigger: 'blur' },
    {type: 'email', message: '请输入合法的电子邮件地址', trigger: ['blur', 'change']}
  ],
  code: [
    { required: true, message: '请输入获取的验证码', trigger: 'blur' },
  ]
}

const formRef = ref()
const isEmailValid = ref(false)
const coldTime = ref(0)

const onValidate = (prop, isValid) => {
  if(prop === 'email')
    isEmailValid.value = isValid
}

const register = () => {
  formRef.value.validate((isValid) => {
    if(isValid) {
      post('/api/auth/register', {
        username: form.username,
        password: form.password,
        email: form.email,
        code: form.code
      }, (message) => {
        ElMessage.success(message)
        router.push("/")
      })
    } else {
      ElMessage.warning('请完整填写注册表单内容！')
    }
  })
}

const validateEmail = () => {
  coldTime.value = 60
  post('/api/auth/valid-register-email', {
    email: form.email
  }, (message) => {
    ElMessage.success(message)
    setInterval(() => coldTime.value--, 1000)
  },(message) => {
    ElMessage.warning(message)
    coldTime.value = 0
  })
}
</script>

<template>
  <div style="text-align: center; margin: 0 20px">
    <div style="margin-top: 150px">
      <div style="font-size: 25px;font-weight: bold">注册新用户</div>
      <div style="font-size: 14px;color: grey;margin-top: 10px">欢迎注册Campus论坛，请在下方填写相关信息</div>
    </div>
    <el-form style="margin-top: 50px" :model="form" :rules="rules" @validate="onValidate" ref="formRef">
      <el-form-item prop="username">
        <el-input type="text" :prefix-icon="User" :maxlength="8" v-model="form.username" placeholder="用户名"/>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" :prefix-icon="Lock" :maxlength="16" v-model="form.password" placeholder="密码" show-password/>
      </el-form-item>
      <el-form-item prop="password_repeat">
        <el-input type="password" :prefix-icon="Lock" :maxlength="16" v-model="form.password_repeat" placeholder="重复密码" show-password/>
      </el-form-item>
      <el-form-item prop="email">
        <el-input type="email" :prefix-icon="Message" v-model="form.email" placeholder="电子邮件地址"/>
      </el-form-item>
      <el-form-item prop="code">
        <el-row :gutter="10">
          <el-col :span="18">
            <el-input type="text" v-model="form.code" :maxlength="6" :prefix-icon="EditPen" placeholder="请输入验证码"/>
          </el-col>
          <el-col :span="6">
            <el-button style="width: 105px" type="success" @click="validateEmail"
                       :disabled="!isEmailValid || coldTime > 0">
              {{coldTime > 0 ? '请等待 ' + coldTime + '秒' : '获取验证码'}}</el-button>
          </el-col>
        </el-row>
      </el-form-item>
    </el-form>
    <div style="margin-top: 40px">
      <el-button type="warning" @click="register" style="width: 270px" plain>立即注册</el-button>
    </div>
    <div style="margin-top: 20px">
      <span style="font-size: 14px; color: grey">已有帐号？</span>
      <el-link type="primary" style="translate:0 -2px" @click="router.push('/')">立即登录</el-link>
    </div>
  </div>
</template>

<style scoped>

</style>