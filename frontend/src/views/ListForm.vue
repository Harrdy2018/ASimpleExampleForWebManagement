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
        <el-button type="primary" @click="onSubmit(ruleFormRef)">Create</el-button>
        <el-button type="primary" @click="onCancel(ruleFormRef)">Cancel</el-button>
        <el-button type="primary" @click="resetForm(ruleFormRef)">Reset</el-button>
      </el-form-item>
  </el-form>
</template>
  
<script lang="js">
import { defineComponent, reactive, onMounted, ref} from 'vue';
import { FormInstance } from 'element-plus'
import store from '@/store/index.ts'
export default defineComponent({
    name: 'ListForm',
    setup(){
        const formLabelAlign = reactive({
            name: '',
            country: '',
            email: '',
        })

        const labelPosition = ref('left')
        const ruleFormRef = ref<FormInstance>({})

        const onSubmit = (x) => {
                console.log('submit! ', formLabelAlign, x)
            
                const response = fetch("http://127.0.0.1:8080/management/new", {
                method: 'post',
                body: JSON.stringify(formLabelAlign),
                headers: {
                    'Content-Type': 'application/json'
                }
                }).then(rsp=>{
                    console.log("rsp ",rsp);
                    return rsp.json()
                })
        
            window.history.back(); 
        }

        const onCancel = (x) => {
            console.log('cancel!', formLabelAlign, x)
            window.history.back();
        }

        const resetForm = (x) => {
            console.log('reset!', formLabelAlign, x)
            if(x){
                x.resetFields()
                console.log("success")
                formLabelAlign.name="" 
            }
        }

        return {
            formLabelAlign,
            onSubmit,
            onCancel,
            resetForm,
            labelPosition,
            ruleFormRef
        }
    }
})
</script>
<style>
   
</style>