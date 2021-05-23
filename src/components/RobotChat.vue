<template>
  <div class="box">
    <div class="header">
      <h3>{{ list[0].name }}</h3>
    </div>
    <div class="main">
      <div class="talkshow">
        <div 
          v-for="(item, index) in list"
          :key="index"
          :class="item.id == '2' ? 'atalk' : 'btalk'"
        >
          <span>{{ item.content }}</span>
        </div>
      </div>
    </div>
    <div class="sendbox">
      <el-input
        v-model="wordone"
        placeholder="请输入消息"
        @keyup.enter="sendmsg1()"
      ></el-input>
      <el-button type="success" round @click="sendmsg1()">发送</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "RobotChat",
  data() {
    return {
      list: [
        {
          id: 1,
          name: "chenyun",
          content: "你好"
        },
        {
          id: 2,
          name: "小吴同学",
          content: "你好啊"
        }
      ],
      replyList:"",
      wordone: "",
      wordtwo: "",
    };
  },
  methods: {
    sendmsg1() {
      var param = { 
          perception: {
            inputText: {
              text:this.wordone,
        },
      },
           userInfo: {
            apiKey:"32293c6faadb42499fa94fe37ac1ba7a",
            userId:"714515",
      }, 
    };
    this.list.push({
          id: 1,
          name: "chenyun",
          content: this.wordone,
    })
      this.$axios.post("/openapi/api/v2", param).then(res => {
        if (200 == res.status) {
          console.log(res);
          this.replyList = res.data.results;
          console.log(this.replyList);
          this.list.push({
            id:2,
            name: "小吴同学",
            content: this.replyList[0].values.text,
          });
          console.log(this.replyList[0].values.text);
          console.log(this.list);
        }
      });
    },
  }
};
</script>

<style scoped>
.el-input {
  width: 80%;
}
.box {
  width: 380px;
  height: 520px;
  margin: 50px 90px;
  border-radius: 15px;
  /* border: 1px solid grey; */
}
.header {
  text-align: center;
  border: 1px solid grey;
  background-color: #fff;
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
}

h3 {
  margin: 2px;
  padding: 2px;
  color: #000;
}
.main {
  height: 85%;
   border-left: 1px solid grey;
  border-right: 1px solid grey; 
  overflow: auto;
  background-color: #ddd;
}
/* div::-webkit-scrollbar {
  display: none;
} */
.atalk {
  margin: 10px;
}
.atalk span {
  display: inline-block;
  padding: 6px 10px;
  background-color: #fff;
  border-radius: 5px;
}
.btalk {
  text-align: right;
  margin: 10px;
}
.btalk span {
  display: inline-block;
  padding: 6px 10px;
  background-color: #A0EC70;
  border-radius: 5px;
}
.sendbox {
  height: 50px;
  border-bottom-left-radius: 15px;
  border-bottom-right-radius: 15px;
}
.el-button--success {
  width: 20%;
  float: right;
  border-radius: 5px;
}
</style>
