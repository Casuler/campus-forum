<script setup>
import {reactive, ref} from "vue";
import InfoSettings from "@/components/settings/infoSettings.vue";
import UserCard from "@/components/settings/userCard.vue";
import SecuritySettings from "@/components/settings/securitySettings.vue";
import PrivacySettings from "@/components/settings/privacySettings.vue";

const activeName = ref('info')
</script>

<template>
  <div style="display: flex;padding: 20px 30px">
    <div style="flex: 1">
      <el-tabs v-model="activeName">
        <el-tab-pane label="个人信息" name="info">
          <info-settings/>
        </el-tab-pane>
        <el-tab-pane label="账号安全" name="security">
          <security-settings/>
        </el-tab-pane>
        <el-tab-pane label="账号隐私" name="privacy">
          <privacy-settings/>
        </el-tab-pane>
      </el-tabs>
    </div>
    <div style="width: 300px">
      <user-card/>
    </div>
  </div>
</template>

<style lang="less" scoped>

</style>