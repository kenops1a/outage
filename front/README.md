# front

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).

### env配置文件
.env 全局默认配置文件,不论什么环境都会加载合并
.env.development 开发环境下的配置文件
.env.production 生产环境配置文件

##不合理之处
### 暂时未修复的bug
1.验证码输入：以中文模式输入会出现按一次按键输入四个字母的情况
2.validate校验：点击登录或注册按钮校验规则提示不会全部亮起
3.导航栏点击退出后，按钮位置会发生变化
### 需要优化的地方
1.vuex中的数据应处理后再存放，而不是需要用的时候再处理
2.后端获取主持人列表接口逻辑需要修改
3.用户年龄的显示，应修改为，如果未设置出生日期，则年龄显示为 未知，已设置出生日期，则由出生日期计算年龄
4.性别应修改为选择框
5.预定主持人表单日期应做成选择框，而不是输入框
6.用户下单应将订单信息提交给对应主持人，由主持人确认后再进行处理
7.订单信息应由系统推送给对应用户，用户下单提醒主持人，主持人确认提醒用户
9.订单表结构可能需要修改，添加操作标识，交易双方有一方未处理，都将该订单设置为冻结状态，无法修改
10.管理端
11.未登录router跳转至登录，登录后会直接跳转到首页 (redirect参数有问题)
12.vuex中添加主持人列表储存已交流过的主持人对象
### 暂未处理的问题
1.修改用户信息的参数修改问题（已完成）
2.修改用户信息的参数格式校验（已完成）
3.vuex存放对象数组（已完成）{
    问题：vuex中向对象数组push元素时会将数组中的所有元素修改为新元素
    解决：将元素转换为json字符串再push到数组中
}
### 

