package com.jugheadzhou.base.producer.service;

import com.jugheadzhou.base.producer.entity.UserScore;

/**
 * @description: 用户服务
 * @author: aishu
 * @create: 2019-10-24 11:44
 */

public interface IUserServer {
    /**
     * 模拟登陆成功后送积分的功能
     * 通过mq通知积分系统给用户新增积分
     * 使用队列模式
     * @param username
     * @param password
     */
    String login(String username,String password);

    /**
     * 删除用户积分
     * @param username
     * @return
     */
    String deleteScore(String username);
}