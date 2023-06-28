<script setup>
import {useRouter} from "vue-router";
import {useStore} from "@/stores/store.js";
import {SwitchButton, User} from "@element-plus/icons-vue";
import {onMounted, reactive} from "vue";
import {ElMessage} from "element-plus";
import {get} from "@/request/request.js";

const router = useRouter()
const store = useStore()

const form = reactive({
  user:[]
})

const logout = () => {
  get('/api/auth/logout', (message) => {
    ElMessage.success(message)
    store.auth.user = null
    router.push('/')
  })
}

const getMe = () => {
  get('/api/user/me', (message) => {
    form.user = message
  })
}

const goMyspace = () => {
  router.push('/myspace')
}

onMounted(async () => {
  await getMe()
})
</script>

<template>
  <div class="user-container">
    <div class="user-wrapper">
      <div class="user-name">{{form.user.username}}</div>
      <div class="user-email">{{form.user.email}}</div>
    </div>
    <el-dropdown>
      <el-avatar :src="form.user.avatar" :size="50" alt=""/>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item @click="goMyspace()" :icon="User">
            个人中心
          </el-dropdown-item>
          <el-dropdown-item @click="logout()" :icon="SwitchButton">
            退出登录
          </el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<style lang="less" scoped>
.user-container{
  display: flex;
  align-items: center;
  height: 100%;
  transition: background 0.3s;
  .user-wrapper{
    display: flex;
    flex-direction: column;
    margin: 10px 10px 0 0;
    line-height: 20px;
    .user-name{
      text-align: center;
      font-size: 18px;
    }
    .user-email{
      color: gray;
      font-size: 14px;
    }
  }
  .el-avatar{
    cursor: pointer;
    outline: none;
  }
}
</style>