<template>
    <el-form
    ref="ruleFormRef"
    :label-position="labelPosition"
    label-width="100px"
    :model="formLabelAlign"
    style="max-width: 460px">
    <el-form-item label="Name">
      <el-input v-model="formLabelAlign.name" />
    </el-form-item>
    <el-form-item label="Country">
      <el-input v-model="formLabelAlign.country" />
    </el-form-item>
    <el-form-item label="Email">
      <el-input v-model="formLabelAlign.email" />
    </el-form-item>
    <el-form-item>
        <el-button type="primary" @click="onSubmit">Submit</el-button>
        <el-button type="primary" @click="onCancel">Cancel</el-button>
        <el-button type="primary" @click="resetForm">Reset</el-button>
      </el-form-item>
  </el-form>
</template>
  
<script lang="js">
import { defineComponent, reactive, onMounted, ref} from 'vue';
import { useStore } from 'vuex'
import { useRouter, useRoute } from 'vue-router'
export default defineComponent({
    name: 'User',
    setup(){
        const route = useRoute()
        const formLabelAlign = reactive({
            name: '',
            country: '',
            email: '',
            id: ''
        })

        const labelPosition = ref('left')

        const insertUser = ()=>{
            const response = fetch("http://127.0.0.1:8080/management/insertUser", {
                method: 'post',
                body: JSON.stringify(formLabelAlign),
                headers: {
                    'Content-Type': 'application/json'
                }
                }).then(rsp=>{
                    return rsp.json()
                })
        }

        const updateUser = ()=>{
            const response = fetch("http://127.0.0.1:8080/management/updateUserById", {
                method: 'post',
                body: JSON.stringify(formLabelAlign),
                headers: {
                    'Content-Type': 'application/json'
                }
                }).then(rsp=>{
                    return rsp.json()
                })
        }

        const onSubmit = () => {
            if(route.query.isEditing == "true"){
                console.log("编辑状态提交---更新")
                updateUser()
            }else{
                console.log("新增状态提交---插入")
                insertUser()
            }
            window.history.back();
        }

        const onCancel = () => {
            window.history.back();
        }

        onMounted(()=>{
            if(route.query.isEditing == "true"){
                const response = fetch("http://127.0.0.1:8080/management/queryUserById", {
                method: 'post',
                body: JSON.stringify({
                    id: parseInt(route.params.id)
                }),
                headers: {
                    'Content-Type': 'application/json'
                }
                }).then(rsp=>{
                    return rsp.json()
                }).then(data=>{
                    formLabelAlign.id=data.data.id
                    formLabelAlign.name=data.data.name
                    formLabelAlign.country=data.data.country
                    formLabelAlign.email=data.data.email
                })
            }
        })

        const resetForm = () => {
            formLabelAlign.name=""
            formLabelAlign.country=""
            formLabelAlign.email=""
        }

        return {
            formLabelAlign,
            onSubmit,
            onCancel,
            resetForm,
            labelPosition
        }
    }
})
</script>
<style>
   
</style>