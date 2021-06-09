<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i>科室管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-input
          v-model="query.officeName"
          placeholder="科室名"
          class="handle-input mr10"
        ></el-input>
        <el-button type="primary" icon="el-icon-search" @click="handleSearch"
          >搜索</el-button
        >
        <el-button type="primary" @click="handleAdd" style="float:right"
          >新增</el-button
        >
      </div>
      <el-table
        :data="tableData"
        border
        class="table"
        ref="multipleTable"
        header-cell-class-name="table-header"
      >
        <el-table-column
          type="selection"
          width="55"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="id"
          label="科室编号"
          width="55"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="parentId"
          label="父级科室编号"
          width="80"
          align="center"
        ></el-table-column>
        <el-table-column prop="officeName" label="科室名"></el-table-column>
        <el-table-column prop="description" label="描述"></el-table-column>
        <el-table-column prop="createTime" label="创建时间"></el-table-column>
        <el-table-column prop="updateTime" label="修改时间"></el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <template #default="scope">
            <el-button
              type="text"
              icon="el-icon-edit"
              @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-button
              type="text"
              icon="el-icon-delete"
              class="red"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
          background
          layout="total, prev, pager, next"
          :current-page="query.pageIndex"
          :page-size="query.pageSize"
          :total="pageTotal"
          @current-change="handlePageChange"
        ></el-pagination>
      </div>
    </div>

    <!-- 编辑弹出框 -->
    <el-dialog title="编辑" v-model="editVisible" width="30%">
      <el-form ref="form" :model="form" label-width="70px">
        <el-form-item label="科室名">
          <el-input v-model="form.officeName"></el-input>
        </el-form-item>
        <el-form-item label="父级科室编号">
          <el-input v-model="form.parentId"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="form.description"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="editVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveEdit">确 定</el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 新增弹出框 -->
    <el-dialog title="新增" v-model="addVisible" width="30%">
      <el-form ref="form" :model="form" label-width="70px">
        <el-form-item label="科室名" >
          <el-input v-model="form.officeName" placeholder="请输入科室名"></el-input>
        </el-form-item>
        <el-form-item label="父级科室编号" >
          <el-input v-model="form.parentId" placeholder="请输入父级科室编号"></el-input>
        </el-form-item>
        <el-form-item label="描述" >
          <el-input v-model="form.description" placeholder="请输入描述"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="addVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveAdd">确 定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import { fetchOfficeData } from "../api/index";
import { deleteOfficeData } from "../api/index";
import { updateOfficeData } from "../api/index";
import { insertOfficeData } from "../api/index";

export default {
  name: "officetable",
  data() {
    return {
      query: {
        officeName: null,
        pageNum: 1,
        pageSize: 10,
      },
      query1: {
        id: "",
      },
      query2: {
        id: "",
        officeName: "",
        description: "",
        parentId:"",
      },
      query3: {
        id: "",
        officeName: "",
        description: "",
        parentId:"",
      },
      tableData: [],
      multipleSelection: [],
      delList: [],
      editVisible: false,
      addVisible: false,
      doctorList: [],
      pageTotal: 0,
      form: {},
      idx: -1,
      id: -1,
    };
  },
  created() {
    this.getData();
  },
  //   mounted() {
  //     this.getDoctorList();
  //   },
  methods: {
    // 获取 easy-mock 的模拟数据
    getData() {
        fetchOfficeData(this.query).then((res) => {
        console.log(res);
        this.tableData = res.data;
        this.pageTotal = res.pageTotal || 50;
      });
    },
    // 触发新增按钮
    handleAdd() {
      this.addVisible = true;
    },
    // 触发搜索按钮
    handleSearch() {
      this.query.pageNum = 1;
      this.getData();
    },
    // 删除操作
    handleDelete(index) {
      // 二次确认删除
      this.$confirm("确定要删除吗？", "提示", {
        type: "warning",
      })
        .then(() => {
          this.query1.id = this.tableData[index].id;
          console.log(this.query1.id);
          console.log(index);
          this.tableData.splice(index, 1);
          deleteOfficeData(this.query1).then((res) => {
            console.log(res);
            if (res.code != 200) {
              this.$alert(res.msg);
            }else {
              this.$message.success("删除成功");
              this.getData();
            }
          });
        })
        .catch(() => {});
    },
    // 编辑操作
    handleEdit(index, row) {
      this.idx = index;
      this.form = row;
      console.log(this.idx);
      console.log(this.form);
      this.editVisible = true;
    },
    // 保存编辑
    saveEdit() {
      this.editVisible = false;
      console.log(this.form);
      this.query2.id = this.tableData[this.idx].id;
      this.query2.officeName = this.form.officeName;
      this.query2.parentId = this.form.parentId;
      this.query2.description = this.form.description;
      console.log(this.query2.id);
      updateOfficeData(this.query2).then((res) => {
        console.log(res);
        if (res.code != 200) {
              this.$alert(res.msg);
            }else {
              this.$message.success(`修改第 ${this.idx + 1} 行成功`);
              this.getData();
            }
      });
    },
    // 保存新增
      saveAdd() {
        this.addVisible = false;
        console.log(this.form);
        this.query3.officeName = this.form.officeName;
        this.query3.parentId = this.form.parentId;
        this.query3.description = this.form.description;
        console.log(this.query3);
        insertOfficeData(this.query3).then((res) => {
        console.log(res);
        if (res.code != 200) {
              this.$alert(res.msg);
            }else {
              this.$message.success(`新增成功`);
              this.getData();
            }
      });
    },
    // 分页导航
    handlePageChange(val) {
      this.query.pageNum = val;
      this.getData();
    },
  },
};
</script>

<style scoped>
.handle-box {
  margin-bottom: 20px;
}

.handle-select {
  width: 120px;
}

.handle-input {
  width: 300px;
  display: inline-block;
}
.table {
  width: 100%;
  font-size: 14px;
}
.red {
  color: #ff0000;
}
.mr10 {
  margin-right: 10px;
}
.table-td-thumb {
  display: block;
  margin: auto;
  width: 40px;
  height: 40px;
}
</style>
