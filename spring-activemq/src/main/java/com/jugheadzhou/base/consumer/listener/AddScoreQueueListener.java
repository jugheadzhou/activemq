package com.jugheadzhou.base.consumer.listener;

import com.jugheadzhou.base.producer.entity.UserScore;
import com.jugheadzhou.base.consumer.service.IScoreServer;
import com.jugheadzhou.jms.consumer.listener.AbstractMessageListener;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @description: 新增积分队列监听器
 * @author: aishu
 * @create: 2019-10-24 14:05
 */
@Slf4j
public class AddScoreQueueListener extends AbstractMessageListener<UserScore> {

    @Resource
    private IScoreServer scoreServer;


    /**
     * 执行对象的相关逻辑
     *
     * @param userScore
     * @author zhangxueshu
     */
    @Override
    public void execute(UserScore userScore) {
        log.info("积分系统的监听器开始调用新增积分业务: {}",userScore.toString());

        this.scoreServer.addUserScore(userScore);
    }
}