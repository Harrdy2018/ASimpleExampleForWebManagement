<template>
    <div class="makeCenter">
        <div>
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
        </div>
    </div>
</template>
  
<script lang="js">
import { defineComponent, reactive, onMounted, ref} from 'vue';
import { useStore } from 'vuex'
import { useRouter, useRoute } from 'vue-router'
import api from '@/api'
const hostDomain = window.localStorage.getItem("hostDomain")
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
            const response = fetch(`${hostDomain}${api.insertUser}`, {
                method: 'post',
                body: JSON.stringify(formLabelAlign),
                headers: {
                    'Content-Type': 'application/json'
                }
                }).then(rsp=>{
                    // 不能放在外面，会造成调到详情页面时，数据未刷新
                    window.history.back();
                    return rsp.json()
                })
        }

        const updateUser = ()=>{
            const response = fetch(`${hostDomain}${api.updateUserById}`, {
                method: 'post',
                body: JSON.stringify(formLabelAlign),
                headers: {
                    'Content-Type': 'application/json'
                }
                }).then(rsp=>{
                    window.history.back();
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
        }

        const onCancel = () => {
            window.history.back();
        }

        onMounted(()=>{
            console.log(route)
            if(route.query.isEditing == "true"){
                const response = fetch(`${hostDomain}${api.queryUserById}?id=${route.params.id}`, {
                method: 'get'
                })
                .then(rsp=>rsp.json())
                .then(data=>{
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
.makeCenter {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 60%;
}
</style>