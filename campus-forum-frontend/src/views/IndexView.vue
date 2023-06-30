<script setup>
import {get} from "@/request/request.js";
import {onMounted, reactive, ref} from "vue";
import {
  Search, Menu as IconMenu, Document, Files, Setting, Collection, Expand, Fold
} from '@element-plus/icons-vue';

import User from '@/components/User.vue'
import SwitchButton from '@/components/SwitchButton.vue'
import router from "@/router/index.js";

const isCollapse = ref(false)
const search = ref('')
const form = reactive({
  user: []
})

const switchMenu = (tmp) => {
  if(tmp === true)
    isCollapse.value=false
  else if(tmp === false)
    isCollapse.value=true
}

const getMe = () => {
  get('/api/user/me', (message) => {
    form.user = message
  })
}

onMounted(async () => {
  await getMe()
})
</script>

<template>
  <div class="layout">
    <el-container class="container">
      <el-aside class="aside" :class="isCollapse ? 'fold-aside':'aside'">
        <div class="logo">
          <el-image
              src="https://cdn.staticaly.com/gh/Gatsby0108/idle_bed/master/2023/06/2835bdcd0fca594c60b19dd6fd72aa7a4d.svg"
              style="height: 50px"
          />
        </div>
        <el-menu
            :default-active="router.currentRoute.value.path"
            router
            :collapse="isCollapse"
            style="border:none">
          <el-menu-item index="/index">
              <el-icon>
                <icon-menu/>
              </el-icon>
              <span>帖子列表</span>
          </el-menu-item>
          <el-menu-item index="2">
            <el-icon>
              <Document/>
            </el-icon>
            <span>表白墙</span>
          </el-menu-item>
          <el-menu-item index="3">
            <el-icon>
              <Collection />
            </el-icon>
            <span>我的收藏</span>
          </el-menu-item>
          <el-menu-item index="4">
            <el-icon>
              <Files />
            </el-icon>
            <span>帖子管理</span>
          </el-menu-item>
          <el-menu-item index="5">
            <el-icon>
              <Setting />
            </el-icon>
            <span>网站设置</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header class="header">
          <el-button text @click="switchMenu(isCollapse)"
                     :icon="isCollapse ? Expand:Fold"
                     style="font-size: 25px"/>
          <div class="placeholder">
            <el-input
                v-model="search"
                placeholder="搜索论坛内容..."
                style="width: 400px"
                :prefix-icon="Search"/>
          </div>
          <SwitchButton class="switchButton"/>
          <User/>
        </el-header>
        <el-main style="padding: 0">
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style lang="less" scoped>
.layout {
  height: 100vh;

  .container {
    height: 100%;

    .aside {
      border-right:1px solid #d3d3d3;
      width: 250px;
      transition: .5s;

      .logo {
        text-align: center;
      }
    }
    .fold-aside {
      border-right:1px solid #d3d3d3;
      width: 60px;
    }
  }
}

.header {
  display: flex;
  border-bottom: 1px solid #d3d3d3;
  align-items: center;
  padding: 0 20px 0 0;

  .placeholder {
    flex: 1;
    text-align: center;
  }
  .switchButton {
    margin: 10px 60px;
  }
}
</style>