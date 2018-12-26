package com.learning.mybitas;

import org.apache.ibatis.annotations.Param;

/**
 * @Auther: zhangll
 * @Date: 2018/12/25 17:38
 * @Description:
 */
public interface UserDao {
//    void save(User user);

    User selectById(@Param("id") Integer id);
}
