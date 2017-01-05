package com.weibo.ad.example;

import com.weibo.ad.example.controllers.BasicController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.ServletContext;

@RunWith(SpringRunner.class)
@WebMvcTest(BasicController.class)
public class MVCTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testUrlParam() throws Exception {
        RequestBuilder requestBuilder = get("/basic/url/?id=1")
                .accept(MediaType.TEXT_HTML_VALUE);

        mvc.perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(content().string("id :" + 1));

        int item_id = 100;
        requestBuilder = get("/basic/item/" + item_id)
                .accept(MediaType.TEXT_HTML_VALUE);

        mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("item id:" + item_id));
    }
}
