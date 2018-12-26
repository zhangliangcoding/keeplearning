##Mybatis

> MyBatis 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。MyBatis 避免了几乎所有的 
JDBC 代码和手动设置参数以及获取结果集。MyBatis 可以使用简单的 XML 或注解来配置和映射原生信息，将接口
和 Java 的 POJOs(Plain Old Java Objects,普通的 Java对象)映射成数据库中的记录。 -- 官网

###整体架构
![avatar](Mybatis架构.png)
通过上图可以对Mybatis的架构有一个大概的了解，我们从程序启动加载xml相关配置，到执行操作返回数据的路线，来进行流程梳理及源码解析，大致分为以下几个流程
- 配置信息初始化
- 接受调用创建会话
- 处理请求
- 返回处理结果

####配置信息初始化
在Mybatis中，SqlSessionFactory 和 SqlSession 有着重要的作用，SqlSessionFactory为我们生成SqlSession，通过SqlSession我们可以进行增删改查等操作
在SqlSessionFactory中，声明了Configuration类，此类包含了大量的配置基础信息，我们在项目中配置的mybatis-config.xml就是配置的Configuration类的内容
例如Environment -> DataSource ,mapper.xml文件等，在不继承Spring相关的东西，单纯使用Mybatis进行数据操作,首先要配置的就是Configuration类的相关信息
如下：config.xml(名字随意)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="dev">
        <environment id="dev">
            <!-- 配置事务管理 ，采用JDBC管理事务-->
            <transactionManager type="JDBC"/>
            <!-- POOLED是mybatis的 数据源 -->
            <!-- JNDI是基于tomcat的数据源 -->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://xxxx:3306/test"/>
                <property name="username" value="xxx"/>
                <property name="password" value="xxxxxx"/>
            </dataSource>
        </environment>
    </environments>
    <!-- pojo的映射文件UserMapper引入到配入到配置文件中 -->
    <mappers>
        <mapper resource="com.learning.mapping/UserMapper.xml"/>
    </mappers>
</configuration>
```
也可以通过<settings>标签来设置Configuration的相关属性
```xml
    <settings>
        <setting name="logImpl" value="LOG4J"/>
    </settings>
```

config.xml文件的解析是通过org.ibatis.builder.xml.XMLConfigBuilder来进行解析的，通过使用sax的DocumentBuilder，解析成Document
调用parse()方法，通过解析/configuration node ，初始化Configuration类。Configuration创建后进而创建DefaultSqlSessionFactory
parse()方法，主要调用下面方法进行解析初始化Configuration
```java
public class XMLConfigBuilder extends BaseBuilder {
    //....
    private void parseConfiguration(XNode root) {
        try {
          //issue #117 read properties first
          propertiesElement(root.evalNode("properties"));
          Properties settings = settingsAsProperties(root.evalNode("settings"));
          loadCustomVfs(settings);
          typeAliasesElement(root.evalNode("typeAliases"));
          pluginElement(root.evalNode("plugins"));
          objectFactoryElement(root.evalNode("objectFactory"));
          objectWrapperFactoryElement(root.evalNode("objectWrapperFactory"));
          reflectorFactoryElement(root.evalNode("reflectorFactory"));
          settingsElement(settings);
          // read it after objectFactory and objectWrapperFactory issue #631
          environmentsElement(root.evalNode("environments"));
          databaseIdProviderElement(root.evalNode("databaseIdProvider"));
          typeHandlerElement(root.evalNode("typeHandlers"));
          mapperElement(root.evalNode("mappers"));
        } catch (Exception e) {
          throw new BuilderException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
      }
  //....
  }
```
environmentsElement方法会把配置的数据源加载到配置中，mapperElement方法是加载对应的mapper.xml文件的，通过XMLMapperBuilder解析mapper下面的文件，
解析mapper后转成statement主要是XMLStatementBuilder.parseStatementNode方法
```java
public class XMLStatementBuilder extends BaseBuilder {
    //....
    public void parseStatementNode() {
        String id = context.getStringAttribute("id");
        String databaseId = context.getStringAttribute("databaseId");
    
        if (!databaseIdMatchesCurrent(id, databaseId, this.requiredDatabaseId)) {
          return;
        }
    
        Integer fetchSize = context.getIntAttribute("fetchSize");
        Integer timeout = context.getIntAttribute("timeout");
        String parameterMap = context.getStringAttribute("parameterMap");
        String parameterType = context.getStringAttribute("parameterType");
        Class<?> parameterTypeClass = resolveClass(parameterType);
        String resultMap = context.getStringAttribute("resultMap");
        String resultType = context.getStringAttribute("resultType");
        String lang = context.getStringAttribute("lang");
        LanguageDriver langDriver = getLanguageDriver(lang);
    
        Class<?> resultTypeClass = resolveClass(resultType);
        String resultSetType = context.getStringAttribute("resultSetType");
        StatementType statementType = StatementType.valueOf(context.getStringAttribute("statementType", StatementType.PREPARED.toString()));
        ResultSetType resultSetTypeEnum = resolveResultSetType(resultSetType);
    
        String nodeName = context.getNode().getNodeName();
        SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
        boolean isSelect = sqlCommandType == SqlCommandType.SELECT;
        boolean flushCache = context.getBooleanAttribute("flushCache", !isSelect);
        boolean useCache = context.getBooleanAttribute("useCache", isSelect);
        boolean resultOrdered = context.getBooleanAttribute("resultOrdered", false);
    
        // Include Fragments before parsing
        XMLIncludeTransformer includeParser = new XMLIncludeTransformer(configuration, builderAssistant);
        includeParser.applyIncludes(context.getNode());
    
        // Parse selectKey after includes and remove them.
        processSelectKeyNodes(id, parameterTypeClass, langDriver);
        
        // Parse the SQL (pre: <selectKey> and <include> were parsed and removed)
        SqlSource sqlSource = langDriver.createSqlSource(configuration, context, parameterTypeClass);
        String resultSets = context.getStringAttribute("resultSets");
        String keyProperty = context.getStringAttribute("keyProperty");
        String keyColumn = context.getStringAttribute("keyColumn");
        KeyGenerator keyGenerator;
        String keyStatementId = id + SelectKeyGenerator.SELECT_KEY_SUFFIX;
        keyStatementId = builderAssistant.applyCurrentNamespace(keyStatementId, true);
        if (configuration.hasKeyGenerator(keyStatementId)) {
          keyGenerator = configuration.getKeyGenerator(keyStatementId);
        } else {
          keyGenerator = context.getBooleanAttribute("useGeneratedKeys",
              configuration.isUseGeneratedKeys() && SqlCommandType.INSERT.equals(sqlCommandType))
              ? Jdbc3KeyGenerator.INSTANCE : NoKeyGenerator.INSTANCE;
        }
    
        builderAssistant.addMappedStatement(id, sqlSource, statementType, sqlCommandType,
            fetchSize, timeout, parameterMap, parameterTypeClass, resultMap, resultTypeClass,
            resultSetTypeEnum, flushCache, useCache, resultOrdered, 
            keyGenerator, keyProperty, keyColumn, databaseId, langDriver, resultSets);
      }
      //....
  }
```
我们写的sql语句有些事最基本的查询，有些是动态sql，怎么来区分呢？当我们没有配置lang属性值，默认加载时XMLStatementDriver，
XMLStatementDriver.createSqlSource方法，根据sql是否包含动态语句，创建不同的SqlSource(DynamicSqlSource或者RawSqlSource),虽然有这个逻辑，但是在判断是否是动态语句的，
无论是否是动态语句，都会把它当成动态语句来处理，也就是说，都会加载DynamicSqlSource，下面看下判断动态语句的逻辑

TextSqlNode.java
```java
class TextSqlNode  implements SqlNode {
    //......
    public boolean isDynamic() {
        DynamicCheckerTokenParser checker = new DynamicCheckerTokenParser();
        GenericTokenParser parser = createParser(checker);
        parser.parse(text);//这个地方会吧SQL进行处理，返回一个String sql
        return checker.isDynamic();
    }
    //......
}
```
DynamicCheckerTokenParser.java
```java
private static class DynamicCheckerTokenParser implements TokenHandler {

    private boolean isDynamic;

    public DynamicCheckerTokenParser() {
      // Prevent Synthetic Access
    }

    public boolean isDynamic() {
      return isDynamic;
    }

    @Override
    public String handleToken(String content) {
      this.isDynamic = true;//永远是动态语句
      return null;
    }
  }
```
在createParser(checker) 方法中会调用DynamicCheckerTokenParser.handleToken 方法，设置isDynamic
所有关于mapper的准备工作做好之后，最终通过builderAssistant.addMappedStatement方法，把mapper中声明的所有操作存入Configuration的mappedStatements map集合中
方便后面调用的时候从mappedStatements中通过id(mapp.xml文件的namespace+.+方法名 如com.xxx.xxx.UserMapper.selectByKey)做为Key取出对应的MappedStatement
数据源，sql操作都已经加载到了Configuration中了，就差调用执行了。

下面开始进入调用流程，在调用的时候肯定是要先创建一个SqlSession的啦
```java
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    //....
    private SqlSession openSessionFromDataSource(ExecutorType execType, TransactionIsolationLevel level, boolean autoCommit) {
        Transaction tx = null;
        try {
          final Environment environment = configuration.getEnvironment();
          final TransactionFactory transactionFactory = getTransactionFactoryFromEnvironment(environment);
          tx = transactionFactory.newTransaction(environment.getDataSource(), level, autoCommit);
          final Executor executor = configuration.newExecutor(tx, execType);
          return new DefaultSqlSession(configuration, executor, autoCommit);
        } catch (Exception e) {
          closeTransaction(tx); // may have fetched a connection so lets call close()
          throw ExceptionFactory.wrapException("Error opening session.  Cause: " + e, e);
        } finally {
          ErrorContext.instance().reset();
        }
    }
    //....
  }
```
在开启一个session的同时，创建了事务和执行器，最终的执行都是通过Executor执行器进行操作，重要的就是Executor

local cache 默认session缓存，事务问题
https://segmentfault.com/a/1190000008207977

mybitas 插件
1 implements Interceptor
2 @Intercepts(
      {
          @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
          @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
      }
  )
3  @Override public Object intercept(Invocation invocation){}
4 @Override public Object plugin(Object target) { return Plugin.wrap(target, this); }
5 sqlSessionFactory.getConfiguration().addInterceptor(interceptor);

ResultHandler
1 implements ResultHandler
2 @Override public void handleResult(ResultContext<? extends V> context) 