package com.selab.auction.redis;

import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;
    private final static String KEY = "TestKey";

    @Test
    public void testString()  {
        String value = "testResult1";

        ValueOperations<String, String> stringValueOperations = redisTemplate.opsForValue();

        stringValueOperations.set(KEY, value);
        String result = stringValueOperations.get(KEY);
        Assertions.assertEquals(value, result);
    }

    @Test
    public void testList() {
        List<String> values = new ArrayList<>();

        values.add("H");
        values.add("E");
        values.add("L");
        values.add("L");
        values.add("O");

        ListOperations<String, String> listValueOperations = redisTemplate.opsForList();

        for(String str : values) {
            listValueOperations.rightPush(KEY, str);
        }

        List<String> result = listValueOperations.range(KEY, 0, 4);

        assertThat(result).usingRecursiveComparison().isEqualTo(values);
    }

}
