package com.learning.mybitas;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: zhangll
 * @Date: 2018/12/25 17:42
 * @Description:
 */
public class MybitasTest {

    public static void main(String[] args) throws IOException {
        String config = "com/learning/mapping/config.xml";
        InputStream inputStream = Resources.getResourceAsStream(config);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        User user = session.selectOne("test.selectById", 1);
        System.out.println(user.getAge());
    }

}
