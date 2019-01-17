package com.learning;

import com.learning.handler.MyHandler;
import com.learning.handler.MyHandler2;
import com.learning.handler.servlet.MyServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ScheduledExecutorScheduler;
import org.eclipse.jetty.util.thread.Scheduler;

/**
 * @Auther: zhangll
 * @Date: 2019/1/16 11:32
 * @Description:
 */
//@SpringBootApplication
public class JettyApp {

    public static void main(String[] args) throws Exception {

        QueuedThreadPool threadPool = new QueuedThreadPool();
        Scheduler scheduler = new ScheduledExecutorScheduler(1 + "-scheduler", false);
        /*JettyApp jettyApp = new JettyApp();

        Server server = new Server(8080);
//        jettyApp.withSimpleHandler(server);
//        jettyApp.withServletHandler(server);
        jettyApp.withMultiContext(server);
        server.start();
        server.join();*/
    }

    void withSimpleHandler(Server server){
        server.setHandler(new MyHandler("SimpleHandler"));
    }

    void withServletHandler(Server server){
        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);
        servletHandler.addServletWithMapping(MyServlet.class,"/hello");
    }

    void withContextHandler(Server server) {
        ContextHandler contextHandler = new ContextHandler();
        contextHandler.setContextPath("/context");
        contextHandler.setHandler(new MyHandler("ContextHandler"));
    }

    void withMultiContext(Server server) {
        ContextHandler context = new ContextHandler("/");
        context.setContextPath("/");
        context.setHandler(new MyHandler("Root"));

        ContextHandler contextC1 = new ContextHandler("/c1");
        contextC1.setHandler(new MyHandler("c1"));//http://localhost:8080/c1

        ContextHandler contextC2 = new ContextHandler("/c2");
        contextC2.setHandler(new MyHandler2("c2"));//http://localhost:8080/c2

        ContextHandler contextVirtual= new ContextHandler("/cv");
        contextVirtual.setVirtualHosts(new String[] { "127.0.0.2" });
        contextVirtual.setHandler(new MyHandler2("Virtual Hello"));//http://127.0.0.2:8080/cv

        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[] { context, contextC1, contextC2,
                contextVirtual });
        server.setHandler(contexts);
    }

}
