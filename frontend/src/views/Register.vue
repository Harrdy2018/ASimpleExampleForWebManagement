<template>
    <div class="outer">
        <div class="makeCenter">
            <div>
                <div class="registerTitle">
                    <p>注册中心</p>
                </div>
                <el-form label-width="100px" :model="formData" style="max-width: 460px">
                <el-form-item label="username">
                  <el-input v-model="formData.username" />
                </el-form-item>
                <el-form-item label="password">
                  <el-input v-model="formData.password" />
                </el-form-item>
                <el-form-item label="">
                    <el-radio v-model="formData.sex" label="1" size="large">女</el-radio>
                    <el-radio v-model="formData.sex" label="0" size="large">男</el-radio>
                </el-form-item>
                <el-form-item label="email">
                    <el-input v-model="formData.email" />
                </el-form-item>
                <el-form-item label="introduce">
                    <el-input v-model="formData.introduce" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">Submit</el-button>
                    <el-button type="primary" @click="onCancel">Cancel</el-button>
                    <el-button type="primary" @click="resetForm">Reset</el-button>
                  </el-form-item>
              </el-form>
            </div>
        </div>
    </div>
</template>

<script lang="ts" setup>
import { defineComponent, reactive, onMounted, ref } from 'vue'
import { useStore } from 'vuex'
import { useRouter, useRoute } from 'vue-router'
import api from '@/api'
const hostDomain = window.localStorage.getItem("hostDomain")
import { RESTCODE } from "@/common/resetcode"

const router = useRouter()

const formData = reactive({
    username:'',
    password:'',
    sex:'',
    email: '',
    introduce: ''
});

const onSubmit = ()=>{
    console.log(formData)
    const response = fetch(`${hostDomain}${api.register}`, {
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
        })
}

const onCancel = () => {
    window.history.back();
}
    
onMounted(()=>{
    console.log("加载 注册组件")
})
</script>
<style>
.makeCenter {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 60%;
}
.outer {
    height: 100%;
}
</style>