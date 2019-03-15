https://liuluo129.iteye.com/blog/1965268
Filter的作用就是处理request和response，在request到达资源前进行处理，请求资源后处理response
Filter有如下几个用处
 - 在HttpServletRequest到达Servlet之前，拦截客户的HttpServletRequest。
 - 根据需要检查HttpServletRequest，也可以修改HttpServletRequest头和数据。
 - 在HttpServletResponse到达客户端之前，拦截HttpServletResponse。
 - 根据需要检查HttpServletResponse，也可以修改HttpServletResponse头和数据。
在springmvc中的大部分filter都继承OncePerRequestFilter，我们在自定义自己的filter时，也都是用OncePerFitler
下面一个简单的例子使用OncePerRequestFilter定义自己的filter，针对每个请求进行过滤
```java
@WebFilter(filterName = "authFilter", urlPatterns = "/*")//
@Component
public class AuthFilter extends OncePerRequestFilter {
     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {
                try{
                    //实现自己的过滤逻辑
                    filterChain.doFilter(request, response);
                }catch(Exception e){
                    //如出现异常，返回前端json格式数据，避免500 异常页面
                    // 自定义异常的类，用户返回给客户端相应的JSON格式的信息
                    JsonResult errorResponse = new JsonResult(0, e.getMessage());
                    response.setContentType("application/json; charset=utf-8");
                    response.setCharacterEncoding("UTF-8");
        
                    String userJson = convertObjectToJson(errorResponse);
                    OutputStream out = response.getOutputStream();
                    out.write(userJson.getBytes("UTF-8"));
                    out.flush();
                }
     }
}
```



