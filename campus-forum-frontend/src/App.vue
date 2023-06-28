<script setup>
import { get } from '@/request/request'
import { useStore } from '@/stores/store'
import router from '@/router'

const store = useStore()

if(store.auth.user == null){
  get('/api/user/me',(message)=>{
    store.auth.user = message
    router.push('/index')
  },()=>{
    store.auth.user = null
  })
}
</script>

<template>
  <router-view></router-view>
</template>

<style>
body{
  overflow: hidden;
  margin: 0;
  padding: 0;
}
</style>
