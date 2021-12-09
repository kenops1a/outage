#maven
##maven的三种工程

###pom工程
    #用在父级工程和聚合工程中，做jar包的版本控制
###war工程
    #打包成war，发布在服务器上的工程，如网站或服务
###jar工程
    #打包成jar用作jar包使用
    
##pom控制jar包版本
###dependency相关配置
    #在<properties>标签中定义各个依赖的版本
    #scope标签属性：test，在测试时使用，打包时不用。import，单继承，只能用于dependencyManagenment中，且仅用于type=pom的dependency
    #type：dependency中type默认为jar
###dependencyManagenment
    #间接依赖，主要作用是用来管理依赖。可以被继承，如果在这个元素中引入的依赖不会被加载进来，只有其他工程引入这个依赖之后才会被引入。一般用于父pom.xml中，而子工程dependency元素在依赖。
    #懒加载
###dependencies
    #标签中引入的依赖，每个子工程中也就自动继承了这里的依赖。所以只要在这个标签中引入的依赖，
    maven会将这个标签中的依赖加载到项目中，每个子模块不用再依赖就直接使用。
##插件
