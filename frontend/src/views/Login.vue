<template>
    <div class="outer">
        <div class="makeCenter">
            <div>
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
                <el-button type="primary" @click="onSubmit">登录</el-button>
                <el-button type="primary" @click="resetForm">重置</el-button>
                <el-button type="primary" @click="onRegister">注册</el-button>
                </el-form-item>
                </el-form>
            </div>
        </div>
        <div class="bottom"></div>
    </div>
</template>
  
<script lang="js">
import { defineComponent, reactive, onMounted, inject} from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useStore } from 'vuex'
import { ElMessage } from 'element-plus'
import api from "@/api"
import { RESTCODE } from "@/common/resetcode"

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

        const onRegister = ()=> {
            console.log("注册流程....")
            router.push({
                path: "/user/register"
            })
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
                    if (res.code === RESTCODE.SUCCESS) {
                        router.push({
                            name: 'Home',
                            params: {
                                accountId: res.data.id
                            }
                        })
                    }

                    if (res.code === RESTCODE.USER_NOT_EXIST) {
                        console.log("用户不存在")
                        ElMessage({
                            message: '用户不存在，请您先登录！',
                            type: 'warning'
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
            onSubmit,
            onRegister
        }
      }
    });
</script>
<style>
.makeCenter {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 60%;
}
.bottom {
    height: 40%;
}
.outer {
    height: 100%;
}
</style>
