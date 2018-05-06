package com.kangyonggan.demo;

import com.kangyonggan.app.redis.RedisService;
import com.kangyonggan.demo.model.User;
import com.kangyonggan.demo.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author kangyonggan
 * @date 4/11/17
 */
@Log4j2
public class RedisServiceTest extends AbstractServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void testGetKeys() {
        log.info(redisService.getKeys("*"));
    }

}
