package com.example.thymleaf_demo;

import com.example.thymleaf_demo.util.HTMLTemplateUtils;

import java.util.HashMap;

//@SpringBootApplication
public class ThymleafDemoApplication {

    public static void main(String[] args) {

//        SpringApplication.run(ThymleafDemoApplication.class, args);
        String template = "<p th:text='${title}'></p>";
        HashMap<String, Object> map = new HashMap<>();
        map.put("title","hello world");
        String render = HTMLTemplateUtils.render(template, map);
        System.out.println("渲染之后的字符串是:"+render);

    }

}
