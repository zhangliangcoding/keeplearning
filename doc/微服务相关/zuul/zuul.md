RequestContext 请求上下文 继承ConcurrentHashMap
FilterLoader
DynamicCodeCompiler 动态编译 文件 加载zuulFilter文件  FilterFileManager

zuul:
  routes:
    user:                                               
      path: /test/**                                    
      url: http://localhost:8080/test
      serviceId: xxxx  -- 设置微服务id，结合eureka 注册到eureka的服务id
直接配置url，走simpleHostRouatingFilter 进行直接请求
SimpleHostRoutingFilter -> shouldFilter() ->run()
当配置serviceId的时候走 RibbonRoutingFilter