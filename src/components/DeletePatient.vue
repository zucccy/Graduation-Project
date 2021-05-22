<template>
  <div class="dialog">
    <div class="content">
      <el-dialog
        :visible="visible"
        :title="title"
        :center="true"
        class="new-info"
        @close="$emit('close')"
      >
        <el-form ref="form"  label-width="80px" size="mini" >

          <el-form-item >
            <div class="confirm">
              是否确认删除就诊人 <p>{{name}}</p>？
            </div>
          </el-form-item>
          <el-form-item class="confirm-button" size="large">
            <el-button type="primary" @click="checked" :loading="loading">确定</el-button>
            <el-button type="info" @click="cancel" :loading="loading">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
  </div>
</template>

<script>

  export default {
    props: ['visible', 'title'],
    components: {
    },
    data () {
      return {
        loading: false,
        name:"师姐冰",

      }
    },
    methods: {
      checked() {

      },
      cancel() {

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
    .el-button--info {
      width: 90px;
      height: 36px;
      box-shadow: 2px 2px 9px 0px rgba(24, 190, 155, 0.5);
      border-radius: 2px;
      margin-left: 30px;
    }
    .confirm {
      font-size: 20px;
      padding: 30px 0 30px 10px;
      color: #333333;
    }
    .confirm p {
      display: inline-block;
      color: #3ECADA;
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

