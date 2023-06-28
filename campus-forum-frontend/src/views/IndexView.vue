<script setup>
import {get} from "@/request/request.js";
import {ElMessage} from "element-plus";
import router from "@/router";
import {useStore} from "@/stores/store.js";
import {onMounted, reactive} from "vue";
import {
  Location, Menu as IconMenu, Document, Setting} from '@element-plus/icons-vue';

import User from '@/components/User.vue'

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
  <div class="layout">
    <el-container style="height: 100%">
      <el-aside style="border-right:solid #d3d3d3;width: 250px">
        <div class="logo">
          <el-image
              src="https://cdn.staticaly.com/gh/Gatsby0108/idle_bed/master/2023/06/2835bdcd0fca594c60b19dd6fd72aa7a4d.svg"
              style="height: 50px"
          />
        </div>
        <el-menu
            default-active="1-1"
            style="border:none"
            @open="handleOpen"
            @close="handleClose">
          <el-sub-menu index="1">
            <template #title>
              <el-icon><Location/></el-icon>
              <span>Navigator One</span>
            </template>
            <el-menu-item-group title="Group One">
              <el-menu-item index="1-1">item one</el-menu-item>
              <el-menu-item index="1-2">item two</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="Group Two">
              <el-menu-item index="1-3">item three</el-menu-item>
            </el-menu-item-group>
            <el-sub-menu index="1-4">
              <template #title>item four</template>
              <el-menu-item index="1-4-1">item one</el-menu-item>
            </el-sub-menu>
          </el-sub-menu>
          <el-menu-item index="2">
            <el-icon><icon-menu/></el-icon>
            <span>Navigator Two</span>
          </el-menu-item>
          <el-menu-item index="3">
            <el-icon><Document/></el-icon>
            <span>Navigator Three</span>
          </el-menu-item>
          <el-menu-item index="4">
            <el-icon><Setting/></el-icon>
            <span>Navigator Two</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header class="header">
          <div class="placeholder"></div>
          <User/>
        </el-header>
        <el-main>Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style lang="less" scoped>
.layout{
  height: 100vh;
}
.logo{
  text-align:center;
}
.header{
  display: flex;
  border-bottom: solid #d3d3d3;
  .placeholder{
    flex: 1;
  }
}
</style>