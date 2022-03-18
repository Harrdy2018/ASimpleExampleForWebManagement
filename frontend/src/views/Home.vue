<template>
    <el-row>
        <el-button color="#626aef" style="color: white" @click="handleAdd">Add</el-button>
    </el-row>
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="Id" width="200" />
      <el-table-column prop="name" label="Name" width="300" />
      <el-table-column prop="country" label="Country" width="200" />
      <el-table-column prop="email" label="Email" width="300"/>
      <el-table-column label="Operations">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
</template>
  
<script lang="js">
import { defineComponent, reactive, onMounted} from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useStore } from 'vuex'
export default defineComponent({
    name: 'Home',
    setup () {
        const tableData = reactive([])
        const router = useRouter()

        const handleEdit = (index, row) => {
            console.log(index, row)
            router.push({
                name: 'editUser',
                params: {
                    id: row.id
                },
                query: {
                    isEditing: true
                }
            })
        }

        const handleDelete = (index, row) => {
            console.log(index, row)
            const response = fetch("http://127.0.0.1:8080/management/deleteUserById", {
                method: 'post',
                body: JSON.stringify({
                    id: row.id
                }),
                headers: {
                    'Content-Type': 'application/json'
                }
                }).then(rsp=>{
                    return rsp.json()
                })
            window.history.go()
        }

        
        const handleAdd = () => {
            console.log("handleAdd")
            router.push("/insert/user")
        }

        onMounted(()=>{
            // production http://47.96.251.225:8080/management/queryAllUser
            // dev        http://127.0.0.1:8080/management/queryAllUser
            fetch("http://127.0.0.1:8080/management/queryAllUser")
            .then(rsp=>{
                return rsp.json()
            }).then(temp=>{
                tableData.push(...temp.data)
            })
        })
    
        return {
            tableData,
            handleEdit,
            handleDelete,
            handleAdd
        }
      }
    });
</script>
<style>
  
</style>