<template>
    <p>登陆名:{{ accountInfo.username }} | {{ accountInfo.sex }}</p>
    <p>Email: {{ accountInfo.email }}</p>
    <p>自我介绍：{{ accountInfo.introduce }}</p>
</template>

<script lang="js">
import { defineComponent, reactive, onMounted, ref} from 'vue';
import { useStore } from 'vuex'
import { useRouter, useRoute } from 'vue-router'
import api from '@/api'
const hostDomain = window.localStorage.getItem("hostDomain")
export default defineComponent({
     name: 'Account',
     setup(){
        const route = useRoute()
        let accountInfo = reactive({
            id: 0,
            username: '',
            password: '',
            sex: 0,
            email: '',
            introduce: ''
         })

         onMounted(()=>{
            console.log("加载登录账号组件...")
            const response = fetch(`${hostDomain}${api.queryLoginAccount}?id=${route.params.accountId}`, {method: 'get'})
                .then(rsp=>rsp.json())
                .then(data=>{
                    accountInfo.username = data.data.username
                    accountInfo.sex = data.data.sex == 0 ? '男' : '女'
                    accountInfo.email = data.data.email
                    accountInfo.introduce = data.data.introduce
                })    
            }
        )

        return {
            accountInfo
        }
    }       
})
</script>
<style>
       
</style>