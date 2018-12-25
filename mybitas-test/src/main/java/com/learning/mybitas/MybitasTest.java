package com.learning.mybitas;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: zhangll
 * @Date: 2018/12/25 17:42
 * @Description:
 */
public class MybitasTest {

    private SqlSessionFactory sqlSessionFactory;

   /* public MybitasTest() {
        Configuration configuration = new Configuration();
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://gz-cdb-i5rfdmub.sql.tencentcdb.com:63939/test");
        dataSource.setUsername("dev");
        dataSource.setPassword("((A3ze$!aw2");
        Environment environment = new Environment("JDBC",new JdbcTransactionFactory(),dataSource);
        configuration.setEnvironment(environment);
        this.configuration = configuration;
    }
*/

    public static void main(String[] args) throws IOException {
        MybitasTest mybitasTest = new MybitasTest();
//        mybitasTest.getConfiguration(); C:\see\workspace\keeplearning\mybitas-test\src\main\resources\com.learning.mapping\config.xml
        String config = "com.learning.mapping/config.xml";
        InputStream inputStream = Resources.getResourceAsStream(config);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        User user = session.selectOne("test.selectById", 1);
        System.out.println(user.getAge());
    }

}
