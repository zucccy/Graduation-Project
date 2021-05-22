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
        <el-form ref="form" :model="formData" label-width="80px" size="mini" >
          <el-form-item label="姓名" prop="unitName">
            <el-input v-model="formData.unitName" ></el-input>
          </el-form-item>
          <el-form-item label="手机号码" prop="phone">
            <el-input v-model="formData.phone" ></el-input>
          </el-form-item>
          <el-form-item label="身份证号" prop="remark">
            <el-input v-model="formData.remark"></el-input>
          </el-form-item>
          <el-form-item >
            <el-checkbox v-model="checked" style="padding-left: 60px">设置为默认就诊人</el-checkbox>
          </el-form-item>
          <el-form-item class="confirm-button" size="large">
            <el-button type="primary" @click="addCustomerInformation" :loading="loading">确定</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
  </div>
</template>

<script>

  export default {
    props: ['visible', 'title', 'data'],
    components: {
    },
    data () {
      return {
        loading: false,
        checked:false,
        formData: {
          unitName: '',
          phone: '',
          remark: ''
        },


      }
    },
    methods: {
      // 修改选择的地区后进行的操作
      // selectChanged(val) {
      //   let provinceId = val[0]
      //   this.$provinceChanged(provinceId)
      //   if (val[1]) {
      //     let cityId = val[1]
      //     this.$cityChanged(cityId)
      //   }
      // },
      // 添加客户信息
      onSelected(data) {
        this.placeholders.province = data.province.value
        this.placeholders.city = data.city.value
        this.placeholders.area = data.area.value

      },
      addCustomerInformation() {
        if(this.formData.unitName!="" && this.formData.contact!="" && this.formData.phone!="") {
          let params={
            placeholders:this.placeholders,
            formData:this.formData
          }
          this.$emit("addedCustomerInfo",params)
        }else {
          this.$message.warning("必填信息未填写完毕！")
        }

      },
    },
  }
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
    .el-button--primary {
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
          width:110px;
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

