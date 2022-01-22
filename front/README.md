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

### 暂时未修复的bug
1.验证码输入：以中文模式输入会出现按一次按键输入四个字母的情况
2.validate校验：点击登录或注册按钮校验规则提示不会全部亮起
3.导航栏点击退出后，按钮位置会发生变化
### 需要优化的地方
1.vuex中的数据应处理后再存放，而不是需要用的时候再处理
2.后端获取主持人列表接口逻辑需要修改

