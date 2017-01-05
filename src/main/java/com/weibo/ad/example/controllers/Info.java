package com.weibo.ad.example.controllers;

import com.weibo.ad.example.entity.JsonReturn;
import com.weibo.ad.example.entity.RespBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Info {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    /**
     * dump all url to class method mapping
     */
    @GetMapping(value={"/admin/url2method", "/admin"})
    public JsonReturn<Map<String, String>> getUrlMapping() {
        Map<String, String> ret = new HashMap<>(100);
        requestMappingHandlerMapping.getHandlerMethods().entrySet().forEach(
                (/* Map.Entry<RequestMappingInfo, HandlerMethod> */m) -> {
                    RequestMappingInfo info = m.getKey();
                    HandlerMethod method = m.getValue();
                    ret.put(info.getPatternsCondition().toString(), method.getMethod().getDeclaringClass() + "--" +method.getMethod().getName());
        });

        return RespBuilder.build(ret, 0, "ok");
    }
}
