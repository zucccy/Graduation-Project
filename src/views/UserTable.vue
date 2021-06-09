<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i>用户管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-input
          v-model="query.userName"
          placeholder="用户名"
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
          label="用户编号"
          width="55"
          align="center"
        ></el-table-column>
        <el-table-column prop="userName" label="用户名"></el-table-column>
        <el-table-column prop="phone" label="手机号"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
        <el-table-column prop="password" label="密码"></el-table-column>
        <el-table-column
          prop="roleType"
          label="角色类型"
          width="55"
          align="center"
        >
        <template #default="scope">
                        {{ scope.row.roleType === 1 ? "用户" : scope.row.roleType === 2 ? "医生" : "" }}
                    </template>
        </el-table-column>
        <el-table-column label="状态" align="center">
                    <template #default="scope">
                        <el-tag
                            :type="
                                scope.row.status === 1
                                    ? 'success'
                                    : scope.row.status === 2
                                    ? 'danger'
                                    : ''
                            "
                        >{{ scope.row.status === 1 ? "有效" : "无效" }}</el-tag>
                    </template>
                </el-table-column>
        <el-table-column prop="createTime" label="创建时间"></el-table-column>
        <el-table-column prop="updateTime" label="修改时间"></el-table-column>
        <el-table-column label="操作" width="230" align="center">
          <template #default="scope">
            <el-button
              type="text"
              icon="el-icon-edit"
              @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-button
              type="text"
              :class="scope.row.status === 1? 'red' : 'green' "
              icon="el-icon-setting"
              @click="handleupdateStatus(scope.$index, scope.row.status)"
              >{{scope.row.status === 1?"禁用":"启用"}}</el-button
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
        <el-form-item label="用户名">
          <el-input v-model="form.userName" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="form.phone" placeholder="请输入手机号"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="角色类型">
          <el-select v-model="form.roleType" placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
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
        <el-form-item label="用户名">
          <el-input v-model="form.userName" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="form.phone" placeholder="请输入手机号"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" placeholder="请输入密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="角色类型">
          <el-select v-model="form.roleType" placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
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
import { fetchUserData } from "../api/index";
import { deleteUserData } from "../api/index";
import { updateUserData } from "../api/index";
import { insertUserData } from "../api/index";
import { updateStatus } from "../api/index";

export default {
  name: "usertable",
  data() {
    return {
      options: [{
          value: 1,
          label: '用户'
        }, {
          value: 2,
          label: '医生'
        },
        ],
      query: {
        userName: null,
        pageNum: 1,
        pageSize: 10,
      },
      query1: {
        id: "",
      },
      query2: {
        id: "",
        userName: "",
        email: "",
        phone:"",
        password:"",
        profile:"",
        roleType:"",
      },
      query3: {
        id: "",
        userName: "",
        email: "",
        phone:"",
        password:"",
        profile:"",
        roleType:"",
      },
      query4: {
        id:"",
        status:"",
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
        fetchUserData(this.query).then((res) => {
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
          this.$message.success("删除成功");
          this.tableData.splice(index, 1);
          deleteUserData(this.query1).then((res) => {
            console.log(res);
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
    //禁用/启用操作
    handleupdateStatus(index, status) {
      if (status === 1) {
        // 二次确认
      this.$confirm("确定要禁用吗？", "提示", {
        type: "warning",
      })
        .then(() => {
          this.query4.id = this.tableData[index].id;
          console.log(this.query4.id);
          this.query4.status = 2;
          // console.log(status);
          updateStatus(this.query4).then((res) => {
            console.log(res);
            if (res.code != 200) {
              this.$alert(res.msg);
            }else {
              this.$message.success("禁用成功");
              this.getData();
            }
          });
        })
        .catch(() => {});
      } else if (status === 2) {
        // 二次确认
      this.$confirm("确定要启用吗？", "提示", {
        type: "warning",
      })
        .then(() => {
          this.query4.id = this.tableData[index].id;
          console.log(this.query4.id);
          this.query4.status = 1;
          // console.log(status);
          updateStatus(this.query4).then((res) => {
            console.log(res);
            if (res.code != 200) {
              this.$alert(res.msg);
            }else {
              this.$message.success("启用成功");
              this.getData();
            }
          });
        })
        .catch(() => {});
      }
    },
    // 保存编辑
    saveEdit() {
      this.editVisible = false;
      console.log(this.form);
      this.query2.id = this.tableData[this.idx].id;
      this.query2.userName = this.form.userName;
      this.query2.phone = this.form.phone;
      this.query2.email = this.form.email;
      this.query2.password = this.form.password;
      this.query2.roleType = this.form.roleType;
      console.log(this.query2.id);
      updateUserData(this.query2).then((res) => {
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
        this.query3.userName = this.form.userName;
        this.query3.phone = this.form.phone;
        this.query3.email = this.form.email;
        this.query3.password = this.form.password;
        this.query3.roleType = this.form.roleType;
        console.log(this.query3);
        insertUserData(this.query3).then((res) => {
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
.green {
  color: green;
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
