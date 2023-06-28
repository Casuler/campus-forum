<script setup>
import {get} from "@/request/request.js";
import {ElMessage} from "element-plus";
import router from "@/router";
import {useStore} from "@/stores/store.js";
import {onMounted, reactive} from "vue";

const store = useStore()

const form = reactive({
  user:[]
})

const getMe = () => {
  get('/api/user/me',(message)=> {
    form.user = message
  })
}

const logout = () => {
  get('/api/auth/logout', (message) => {
    ElMessage.success(message)
    store.auth.user = null
    router.push('/')
  })
}

onMounted(async ()=>{
  await getMe()
})
</script>

<template>
  <div>
    欢迎{{form.user.username}}进入到学习平台
  </div>
  <div>
    <el-button @click="logout()" type="danger" plain>退出登录</el-button>
  </div>
</template>

<style scoped>

</style>