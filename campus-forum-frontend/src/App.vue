<script setup>
import { useStore } from '@/stores/store'

const store = useStore()
const user = localStorage.getItem("user")

if(user != null){
  store.auth.user = JSON.parse(user)
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
