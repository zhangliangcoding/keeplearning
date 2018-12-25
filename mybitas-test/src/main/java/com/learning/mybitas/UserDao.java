package com.learning.mybitas;

/**
 * @Auther: zhangll
 * @Date: 2018/12/25 17:38
 * @Description:
 */
public interface UserDao {
//    void save(User user);

    User selectById(Integer id);
}
