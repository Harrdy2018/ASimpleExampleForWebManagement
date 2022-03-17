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
        <el-button type="primary" @click="onSubmit">Create</el-button>
        <el-button type="primary" @click="onCancel">Cancel</el-button>
        <el-button type="primary" @click="resetForm">Reset</el-button>
      </el-form-item>
  </el-form>
</template>
  
<script lang="js">
import { defineComponent, reactive, onMounted, ref} from 'vue';
import { FormInstance } from 'element-plus'
import store from '@/store/index.ts'
import { useRouter, useRoute } from 'vue-router'
export default defineComponent({
    name: 'ListForm',
    setup(){
        const route = useRoute()
        const formLabelAlign = reactive({
            name: '',
            country: '',
            email: '',
            id: ''
        })

        const labelPosition = ref('left')
        const createSinglePersonInformation = ()=>{
            const response = fetch("http://127.0.0.1:8080/management/addSinglePersonInformation", {
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
            if(formLabelAlign.id){
                console.log("edit")
            }else{
                createSinglePersonInformation()
            }
                
            setTimeout(() => {
                window.history.back(); 
            }, 500);
        }

        const onCancel = () => {
            window.history.back();
        }

        onMounted(()=>{
            console.log(formLabelAlign)
            if(route.query.id){
                formLabelAlign.name=route.query.name
                formLabelAlign.country=route.query.country
                formLabelAlign.email=route.query.email
                formLabelAlign.id=route.query.id
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