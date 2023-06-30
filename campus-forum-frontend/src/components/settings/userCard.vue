<script setup>
import {useStore} from "@/stores/store.js";

const store = useStore()
</script>

<template>
  <div class="user-card">
    <div class="user-info">
      <div class="user-container">
        <el-avatar  :size="70"
                    :src="store.auth.user.avatar"/>
        <div style="font-weight: bold">{{store.auth.user.username}}</div>
      </div>
    </div>
    <div class="user-intro">
      Hello there~
      Hello there~
      Hello there~
      Hello there~
      Hello there~
      Hello there~
      Hello there~
      Hello there~
    </div>
  </div>
</template>

<style lang="less" scoped>
.user-card {
  height: 600px;
  width: 250px;
  margin: 15px 0 0 40px;
  border-radius: 12px;
  border: 1px solid lightgrey;

  .user-info {
    text-align: right;
    padding: 10px 15px 0 15px;

    .user-container {
      display: inline-block;
      text-align: center;
    }
  }
}

.user-card > div {
  border-bottom: 1px solid lightgrey;
}
</style>