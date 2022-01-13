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
