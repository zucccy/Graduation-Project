<template>
  <div class="dialog">
    <div class="content">
      <el-dialog
        :title="title"
        :visible="visible"
        :center="true"
        class="new-info"
        @close="$emit('close')"
      >
        <el-form ref="form" :model="formData" label-width="80px" size="mini" :rules="rules">
          <el-form-item label="姓名" prop="patientName">
            <el-input v-model="formData.patientName"></el-input>
          </el-form-item>
          <el-form-item label="手机号码" prop="phone">
            <el-input v-model="formData.phone"></el-input>
          </el-form-item>
          <el-form-item label="身份证号" prop="remark">
            <el-input v-model="formData.remark" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item class="confirm-button" size="large">
            <el-button
              type="success"
              @click="updatePatient()"
              :loading="loading"
              >确定</el-button
            >
            <el-button type="info" @click="cancel()" :loading="loading">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  props: ["visible", "title", "data", "id", "idCard", "phone", "patientName"],
  components: {},
  data() {
    return {
      loading: false,
      checked: false,
      formData: {
        patientName: this.patientName,
        phone: this.phone,
        remark: this.idCard,
      },
      rules: {
        patientName: [
          { required: true, message: "请输入姓名", trigger: "blur" },
        ],
        phone: [
          { required: true, message: "请输入手机号", trigger: "blur" },
          {
            min: 11,
            max: 11,
            message: "不符合手机号位数规范",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    cancel() {
      this.$emit('close');
    },
    updatePatient() {
      if (this.formData.patientName != "" && this.formData.phone != "") {
        var param = {
          patientId: this.id,
          patientName: this.formData.patientName,
          phone: this.formData.phone,
        };
        this.$axios
          .post("http://localhost:8088/patient/updateMyPatient/patientId/", param)
          .then((res) => {
            if (200 == res.data.code) {
              console.log(res);
              this.$message.success("修改成功！");
              this.$emit("rereload");
              this.$emit("close");
            } else if (404 == res.data.code) {
              this.$alert(res.data.msg);
            } else if (400 == res.data.code) {
              this.$alert(res.data.msg);
            }
          });
      } else {
        this.$message.warning("必填信息未填写完毕！");
      }
    },
  },
  mounted() {
    console.log(this.idCard);
    console.log(this.patientName);
    console.log(this.phone)
  },
};
</script>
<style lang="scss">
.new-info {
  .el-dialog__header {
    display: flex;
    justify-content: flex-start;
    height: 50px;
    padding: 0 20px 0 30px;
    border-bottom: #d1d3dc solid 1px;
    align-items: center;
    span {
      border-left: #18be9b solid 5px;
      display: flex;
      height: 22px;
      padding: 0 8px;
      font-size: 16px;
      color: #333333;
    }
  }
  .el-input {
    width: 350px;
    height: 32px;
  }
  .el-dialog {
    width: 558px;
  }
  .el-button--success {
    width: 90px;
    height: 36px;
    box-shadow: 2px 2px 9px 0px rgba(24, 190, 155, 0.5);
    border-radius: 2px;
    margin-left: 0;
  }

  .el-dialog__body {
    padding: 20px 64px;
    font-size: 14px;
    label {
      width: 100%;
      display: inline-block;
      display: table;
      margin-bottom: 10px;

      .front {
        display: table-cell;
        color: #666666;
        width: 75px;
        margin-right: 20px;
        text-align: justify;
      }

      input {
        display: table-cell;
        width: 350px;
        height: 35px;
        outline-color: #18be9b;
        background-color: #f9f9f9;
        border-radius: 2px;
        border: solid 1px #e2e2e2;
        color: #333333;
        text-indent: 15px;
      }
    }
    .selectedArea {
      display: flex;
      select {
        width: 110px;
        height: 35px;
      }
    }
  }
}

.confirm-button {
  div {
    margin-left: 0 !important;
    text-align: center !important;
  }
}
</style>

