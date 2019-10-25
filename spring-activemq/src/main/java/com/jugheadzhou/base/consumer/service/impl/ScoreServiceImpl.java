package com.jugheadzhou.base.consumer.service.impl;

import com.jugheadzhou.base.consumer.service.IScoreServer;
import com.jugheadzhou.base.producer.entity.UserScore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @description: 模拟积分系统的业务层
 * @author: aishu
 * @create: 2019-10-24 15:06
 */
@Slf4j
@Service
public class ScoreServiceImpl implements IScoreServer {
    /**
     * 新增用户积分
     */
    public void addUserScore(UserScore userScore){
        log.info("积分系统新增积分业务开始...");
        //模拟业务执行时长
        try{
            log.info("开始等待10s..." + new Date());
            Thread.sleep(10000);
            log.info("等待结束..." + new Date());
        }catch(Exception e){
            System.exit(0);//退出程序
        }
        userScore.setId(UUID.randomUUID().toString().replaceAll("-", ""));

        log.info("新增积分信息：{}",userScore.toString());
        log.info("新增积分入库完成，时间是：{}",new Date());
    }

    /**
     * 根据用户名删除积分
     *
     * @param username
     */
    @Override
    public void deleteUserScore(String username) {
        log.info("开始删除用户{}的积分信息...",username);
        //模拟业务执行时长
        try{
            log.info("开始等待5s..." + new Date());
            Thread.sleep(5000);
            log.info("等待结束..." + new Date());
        }catch(Exception e){
            System.exit(0);//退出程序
        }
        log.info("成功删除用户{}的积分信息。",username);
    }
}