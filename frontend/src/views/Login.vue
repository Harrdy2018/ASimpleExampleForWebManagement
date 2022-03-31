<template>
    <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        status-icon
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm">

    <el-form-item label="用户名" prop="username">
        <el-input v-model.number="formData.username" />
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="formData.password" type="password"/>
    </el-form-item>
    
    <el-form-item>
      <el-button type="primary" @click="onSubmit">Submit</el-button>
      <el-button type="primary" @click="resetForm">Reset</el-button>
    </el-form-item>
  </el-form>
</template>
  
<script lang="js">
import { defineComponent, reactive, onMounted, inject} from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useStore } from 'vuex'
import api from "@/api"

const hostDomain = window.localStorage.getItem("hostDomain")
export default defineComponent({
    name: 'Login',
    setup () {
        const router = useRouter()

        const formData = reactive({
            username: '',
            password: ''
        })

        const resetForm = () => {
            formData.username = ''
            formData.password = ''
        }

        const onSubmit = () => {
            console.log(formData)
            const response = fetch(`${hostDomain}${api.login}`, {
                method: 'post',
                body: JSON.stringify(formData),
                headers: {
                    'Content-Type': 'application/json'
                }
                }).then(rsp=>rsp.json())
                  .then(res=>{
                    console.log(res)
                    if (res.message === "SUCCESS") {
                        router.push({
                            name: 'Home',
                            params: {
                                accountId: res.data.id
                            }
                        })
                    }
                })
        }

        onMounted(()=>{
            console.log("登录组件加载...")
        })

        return {
            formData,
            resetForm,
            onSubmit
        }
      }
    });
</script>
<style>
  
</style>