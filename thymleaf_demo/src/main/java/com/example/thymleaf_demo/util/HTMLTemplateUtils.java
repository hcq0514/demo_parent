package com.example.thymleaf_demo.util;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

/**
 * @author : hcq
 * @date : 2019/5/30
 */
public class HTMLTemplateUtils {
    private final static TemplateEngine engine = new TemplateEngine();

    /**
     * 使用 Thymeleaf 渲染 HTML
     *
     * @param template HTML模板
     * @param params   参数
     * @return 渲染后的HTML
     */
    public static String render(String template, Map<String, Object> params) {
        Context context = new Context();
        context.setVariables(params);
        return engine.process(template, context);
    }
}
