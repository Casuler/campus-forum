<script setup>
import {get} from "@/request/request.js";
import {onMounted, reactive, ref} from "vue";
import {
  Search, Menu as IconMenu, Document, Files, Setting, Collection
} from '@element-plus/icons-vue';

import User from '@/components/User.vue'
import SwitchButton from '@/components/SwitchButton.vue'

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
      <el-aside class="aside">
        <div class="logo">
          <el-image
              src="https://cdn.staticaly.com/gh/Gatsby0108/idle_bed/master/2023/06/2835bdcd0fca594c60b19dd6fd72aa7a4d.svg"
              style="height: 50px"
          />
        </div>
        <SwitchButton class="switchButton" @click="switchMenu(isCollapse)"/>
        <el-menu
            default-active="1-1"
            :collapse="isCollapse"
            style="border:none">
          <el-sub-menu index="1">
            <template #title>
              <el-icon>
                <icon-menu/>
              </el-icon>
              <span>帖子列表</span>
            </template>
            <el-menu-item-group title="Group One">
              <el-menu-item index="1-1">item one</el-menu-item>
              <el-menu-item index="1-2">item two</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="Group Two">
              <el-menu-item index="1-3">item three</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
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
          <div class="placeholder">
            <el-input
                v-model="search"
                placeholder="搜索论坛内容..."
                style="width: 400px"
                :prefix-icon="Search"/>
          </div>
          <User/>
        </el-header>
        <el-main>Main</el-main>
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

      .logo {
        text-align: center;
      }

      .switchButton {
        margin: 10px 60px;
      }
    }
  }
}

.header {
  display: flex;
  border-bottom: 1px solid #d3d3d3;
  align-items: center;

  .placeholder {
    flex: 1;
  }
}
</style>