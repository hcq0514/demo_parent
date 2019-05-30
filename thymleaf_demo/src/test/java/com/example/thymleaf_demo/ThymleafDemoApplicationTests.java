package com.example.thymleaf_demo;

import com.example.thymleaf_demo.util.HTMLTemplateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThymleafDemoApplicationTests {

    @Test
    public void contextLoads() {
        String template = "<p th:text='${title}'></p>";
        HashMap<String, Object> map = new HashMap<>();
        map.put("title","hello world");
        String render = HTMLTemplateUtils.render(template, map);
        System.out.println("渲染之后的字符串是:"+render);
    }

}
