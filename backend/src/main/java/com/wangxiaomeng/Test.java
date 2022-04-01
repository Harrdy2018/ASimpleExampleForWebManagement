package com.wangxiaomeng;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangxiaomeng.model.User;
import netscape.javascript.JSObject;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        User user = new User("123", "lk", "cn");
        ObjectMapper objectMapper = new ObjectMapper();

        String str = objectMapper.writeValueAsString(user);
        System.out.println(str);


        System.out.println(objectMapper.readValue(str, User.class));
    }
}
