package com.jugheadzhou.base.producer.service.impl;

import com.jugheadzhou.base.producer.entity.UserScore;
import com.jugheadzhou.base.producer.service.IUserServer;
import com.jugheadzhou.jms.producer.service.IJmsQueueService;
import com.jugheadzhou.jms.producer.service.IJmsTopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @description:
 * @author: aishu
 * @create: 2019-10-24 15:16
 */
@Slf4j
@Service
public class UserServerImpl implements IUserServer {
    @Resource
    private IJmsQueueService jmsQueueService;
    @Resource
    private IJmsTopicService jmsTopicService;

    /**
     * 通过队列通知
     * 模拟登陆成功后送积分的功能
     * 通过mq通知积分系统给用户新增积分
     * 使用队列模式
     * @param username
     * @param password
     */
    public String login(String username,String password){
        if (Objects.equals(username,"aishu") && Objects.equals(password,"aishu")){
            UserScore userScore = UserScore.builder()
                    .username(username)
                    .score(10)
                    .createTime(new Date())
                    .remark("用户登录送10积分")
                    .build();
            this.jmsQueueService.send("add_score_queue_destination",userScore);
            Date date = new Date();
            log.info("登录完成：{}",date);
            return "欢迎你：" + username + "现在是北京时间：" + date;
        }else {
            throw new RuntimeException("用户名或密码错误！");
        }
    }

    /**
     * 删除用户积分
     * 通过主题通知
     * @param username
     * @return
     */
    @Override
    public String deleteScore(String username) {
        if (StringUtils.isEmpty(username)){
            throw new RuntimeException("用户名为空！");
        }
        Map<String,String> map = new HashMap<>(2);
        map.put("username",username);
        this.jmsTopicService.send("delete_score_topic_destination",username);
        return "成功删除"+username+"的积分！";
    }

}