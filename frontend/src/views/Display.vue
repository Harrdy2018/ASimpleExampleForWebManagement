<template>
    <el-row>
        <el-button color="#626aef" style="color: white" @click="handleAdd">Add</el-button>
        <el-button color="#626aef" plain>Add</el-button>
    </el-row>
    <el-table :data="tableData" border stripe style="width: 100%" :row-class-name="tableRowClassName">
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
    <hr/>
    <h3>{{$store.state.count}}</h3>
    <div>
        <el-button @click="$store.commit('add')">+</el-button>
        <el-button @click="$store.commit('reduce')">-</el-button>
        <el-button @click="forTest">forTest</el-button>
    </div>
</template>
  
<script lang="js">
import { defineComponent, reactive, onMounted} from 'vue';
import store from '@/store/index.ts'
export default defineComponent({
    name: 'Display',
    setup () {
        const tableData = reactive([])
        
        const handleEdit = (index, row) => {
            console.log(index, row)
        }
        const handleDelete = (index, row) => {
            console.log(index, row)
        }

        const handleAdd = () => {
            console.log("handleAdd")
            window.location.href="/add/list"
            history.pushState(null, null, "/query/list");
        }

        const tableRowClassName = ({
            row,
            rowIndex,
        }) => {
            console.log("tableRowClassName ", row, rowIndex)
            if (rowIndex === 1) {
                return 'warning-row'
            } else if (rowIndex === 3) {
                return 'success-row'
            }
            return ''
        }

        function forTest(){
            console.log("forTest ",this)
            console.log("forTest ",store)
        }

        const state = reactive({
            hits:[]
        })

        onMounted(async ()=>{
            // production http://47.96.251.225:8080/management/list
            // dev        http://127.0.0.1:8080/management/list
            const response = await fetch("http://127.0.0.1:8080/management/list").then(rsp=>{
                console.log("rsp ",rsp);
                return rsp.json()
            })
            console.log("start quest ",response)
            tableData.push(...response.data)
        })
    
        return {
            tableData,
            forTest,
            handleEdit,
            handleDelete,
            tableRowClassName,
            handleAdd
        }
      }
    });
</script>
<style>
    .el-table .warning-row {
      color: red;
    }
    .el-table .success-row {
      color: burlywood;
    }
</style>