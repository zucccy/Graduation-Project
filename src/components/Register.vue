<template>
  <div class="wrap">
    <div class="topbar">
      <div class="layout clearfix">
        <div class="logo fl">
          <a href="/"  class="name"
            >面向城市大脑的智慧医疗系统</a
          >
        </div>
        <div class="mission fr"></div>
      </div>
    </div>
    <div class="main">
      <div class="content">
        <div class="regBox">
          <div class="regtab">
            <div
              class="userReg"
              :class="{ active: identity == 'user' }"
              @click="identity = 'user'"
            >
              <span>用户注册</span>
            </div>
          </div>
          <div class="divForm">
            <div class="singleInput">
              <el-input v-model="userName" placeholder="请输入用户名"></el-input>
            </div>
            <div class="singleInput">
              <el-input
                v-model="openCode"
                placeholder="请输入手机号码/邮箱"
              ></el-input>
            </div>
            <div class="singleInput">
              <el-input v-model="password" placeholder="请输入密码" show-password></el-input>
            </div>
            <div class="divButton">
              <button class="regButton" @click="toLogin()">注册</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      userName: "",
      openCode:"",
      password: "",
      checked: false,
      identity: "user",
    };
  },
  methods: {
    toIndex() {
      this.$router.push("/");
    },
    toLogin() {
      var param = {
        openCode: this.openCode,
        password: this.password,
        userName: this.userName,
      }
      if (this.userName.length == 0 || this.password.length == 0 || this.openCode.length == 0) {
        this.$alert("用户名为空或者手机号码/邮箱为空或者密码为空");
      } else {
          this.$axios.post("http://localhost:8088/user/register", param).then((res) => {
          if (200 == res.data.code) {
            console.log(res);
            this.$message({
              message: '注册成功!',
              type: 'success'
      });
            this.$router.push("/Login");
          }
          else if (404 == res.data.code) {
            this.$alert(res.data.msg);
          }
          else if (400 == res.data.code) {
            this.$alert(res.data.msg);
          }
        });
      }
    },
  },
};
</script>

<style scoped>
.wrap {
  width: 100%;
  height: 100%;
  margin: 0 auto;
  position: relative;
}

.topbar {
  background-color: white;
  border-top: 5px solid #0096a5;
  border-bottom: 2px solid #f2f2f2;
  height: 85px;
}
.layout {
  width: 800px;
  margin: 0 auto;
  overflow: hidden;
  zoom: 1;
}
.logo {
  position: relative;
  width: 400px;
  margin-left: 20px;
  background: url("../assets/hospital.png") left top no-repeat;
  height: 60px;
  margin-top: 10px;
  overflow: hidden;
}
.name {
  float: left;
  padding-left: 20px;
  margin-left: 55px;
  font-weight: 700;
  font-size: 25px;
  color: #000;
  text-decoration: none;
  cursor: pointer;
  width: 360px;
  line-height: 60px;
}
.fl {
  float: left;
  display: inline;
}
.mission {
  width: 265px;
  margin-right: 28px;
  background: url("../assets/app.png") left top
    no-repeat;
  height: 60px;
  overflow: hidden;
  text-indent: -999px;
}
.fr {
  float: right;
  display: inline;
}
.main {
  display: block;
  width: 800px;
  height: 588px;
  margin: 0 auto;
  background: #fcfcfc;
}
.content {
  padding: 45px 0 95px 0;
}
.regBox {
  width: 400px;
  height: 407px;
  margin: 0 200px;
}
.regtab {
  height: 51px;
  margin-bottom: 25px;
}
.userReg {
  float: left;
  text-align: center;
  width: 125px;
  font-size: 25px;
  color: #999;
  line-height: 44px;
}
.docReg {
  float: right;
  text-align: center;
  width: 200px;
  font-size: 25px;
  color: #999;
  line-height: 44px;
}
.divForm {
  width: 400px;
  height: 331px;
}
.singleInput {
  width: 374px;
  height: 42px;
  padding: 0 12px;
  margin: 1px 1px 23px 1px;
}
.divLabel {
  height: 18px;
  line-height: 18px;
  font-size: 12px;
  padding-left: 13px;
  margin-bottom: 23px;
}
.divButton {
  text-align: center;
  height: 46px;
  margin-bottom: 23px;
}
.regButton {
  border-radius: 5px;
  border: #74bc31 1px solid;
  font-size: 20px;
  width: 374px;
  color: white;
  height: 46px;
  margin: 0 auto;
  background-color: #74bc31;
}
.active {
  color: #333;
}
</style>
