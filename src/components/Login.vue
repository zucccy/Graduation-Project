<template>
  <div id="login" class="bg bgbottom">
    <div class="wrap">
      <div class="title">面向城市大脑的智慧医疗系统</div>
      <div class="slide">
        <div class="kouhao">随时预约挂号</div>
        <div class="kouhao kouhao2">省去排队烦恼</div>
      </div>
      <div class="loginpannl moveup">
        <div class="loginnav"><span class="current">账号登录</span></div>
        <div class="loginform">
          <div class="logintab">
            <div class="maininput">
              <el-input
                v-model="openCode"
                placeholder="请输入手机号/邮箱"
              ></el-input>
            </div>
            <div class="maininput">
              <el-input
                v-model="password"
                placeholder="请输入密码"
                show-password
              ></el-input>
            </div>
            <div class="checkbox">
              <el-radio v-model="radio" label="1">用户</el-radio>
              <el-radio v-model="radio" label="2">医生</el-radio>
            </div>
            <div class="submit">
              <el-button
                type="success"
                round
                icon=""
                class="submitButton"
                @click="login()"
                >登录</el-button
              >
              <el-button
                type="success"
                round
                icon=""
                class="submitButton"
                @click="toIndex()"
                >回到首页</el-button
              >
            </div>
            <div class="register">
              <span class="toreg"
                >没有账号？<a href="" @click="toRegister()">立即注册 ></a></span
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      openCode: "",
      password: "",
      radio: "1",
      userInfo: "",
    };
  },
  methods: {
    toIndex() {
      this.$router.push("/");
    },
    toRegister() {
      this.$router.push("register");
    },
    login() {
      var param = {
        openCode: this.openCode,
        password: this.password,
        roleType: this.radio,
      };
      if (this.openCode.length == 0 || this.password.length == 0) {
        this.$alert("手机号/邮箱为空或者密码为空");
      } else {
        this.$axios.post("http://localhost:8088/user/login", param).then((res) => {
          if (200 == res.data.code) {
            // 如果直接进入登录页，无上一页面 默认跳转首页 存在上一页面，则返回上一页面
            var lastPath = '/';
            var curr = localStorage.getItem('preRoute');
            if (curr && curr != '/login' && curr != '/') {
              lastPath = curr;
            }
            console.log(res);
            this.userInfo = res.data.data;
            console.log(this.userInfo);
            console.log(res.headers.authorization);
            localStorage["token"] = res.headers.authorization;
            localStorage["userInfo"] = JSON.stringify(this.userInfo);
            if (this.radio == 1) {
              this.$router.push(lastPath);
            } else if (this.radio == 2) {
              this.$router.push("/doctorWork");
            }
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
.bg {
  background-size: 100% 100%;
  height: 100%;
  position: fixed;
  width: 100%;
  min-height: 760px;
  min-width: 1000px;
  background-image: url("../assets/bcg.jpg");
  background-repeat: no-repeat;
}
.wrap {
  width: 1000px;
  height: 100%;
  margin: 0 auto;
  position: relative;
}
.title {
  padding-top: 55px;
  display: block;
  height: 80px;
  font-size: 45px;
  color: blue;
  text-align: center;
}
.slide {
  float: left;
  width: 450px;
}
.loginpannl {
  width: 310px;
  border: #74bc31 1px solid;
  padding: 0 55px 30px;
  border-radius: 10px;
  background: #fff;
  position: absolute;
  right: 0;
  overflow: hidden;
  transition: height 0.25s;
}
.loginpannl .moveup {
  margin-top: 0;
  top: 7%;
}
.loginnav {
  font-size: 20px;
  line-height: 60px;
  margin-top: 10px;
  text-align: center;
  display: flex;
  justify-content: center;
}
.loginform {
  width: 310px;
  margin: 0 auto;
}
.loginform .maininput {
  padding-top: 30px;
}
.loginform .submit {
  display: flex;
  justify-content: center;
  padding-top: 20px;
  height: 46px;
  line-height: 46px;
}
.submitButton {
  font-size: 20px;
  width: 120px;
  border-radius: 10px;
}
.loginform .submit span {
  float: left;
}
.loginform .submit a {
  color: #74bc31;
}
.loginnav span {
  width: 155px;
  cursor: pointer;
}
.loginnav .current {
  color: #74bc31;
  border-bottom: #74bc31 2px solid;
  margin-bottom: -2px;
}
.checkbox {
  padding-top: 30px;
}
.register {
  padding-top: 10px;
}
.kouhao {
  font-size: 35px;
  text-align: center;
  color: #3ecada;
  font-weight: 900;
  letter-spacing: 5px;
  padding-top: 100px;
}
.kouhao2 {
  padding: 50px 0 0 180px;
  color: #74bc31;
}
</style>
