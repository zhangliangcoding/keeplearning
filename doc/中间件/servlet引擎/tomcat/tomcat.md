https://www.ibm.com/developerworks/cn/java/j-lo-tomcat1/index.html?mhq=tomcat&mhsrc=ibmsearch_d

StandardServer

                  - setContainer
StandardService -| 
                  - addConnector


Container
    |
    StandardEngine
    |
    StandardHost
    |
    StandardContext - TomcatEmbeddedContext
    |
    StandardWrapper

StandardEngineValve
StandardHostValve
StandardContextValve
StandardWrapperValve

Lifecycle

https://www.cnblogs.com/xuwc/p/8523681.html
