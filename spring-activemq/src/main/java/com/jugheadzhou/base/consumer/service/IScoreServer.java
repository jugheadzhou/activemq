package com.jugheadzhou.base.consumer.service;

import com.jugheadzhou.base.producer.entity.UserScore;
import org.springframework.stereotype.Service;

/**
 * @description: 模拟积分系统的积分业务
 * @author: aishu
 * @create: 2019-10-24 12:31
 */
@Service
public interface IScoreServer {

    /**
     * 新增用户积分
     */
    void addUserScore(UserScore userScore);

    /**
     * 根据用户名删除积分
     * @param username
     */
    void deleteUserScore(String username);
}




