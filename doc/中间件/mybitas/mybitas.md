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