package com.weibo.ad.example.controllers;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/http")
public class HTTPFetch {
    private static final String UA_KEY = "User-Agent";
    private static final String UA_CURL = "curl/7.43.0";

    private OkHttpClient ok;

    public HTTPFetch() {
        super();
        ok = new OkHttpClient();
    }

    /**
     * http://omgitsmgp.com/2015/12/02/effective-okhttp/
     * https://github.com/square/okhttp/wiki/Calls
     */
    @GetMapping("/ip")
    @ResponseBody
    public String okget() throws IOException {
        String url = "http://ip.cn";
        Request request = new Request.Builder()
                .url(url)
                .removeHeader(UA_KEY)
                .addHeader(UA_KEY, UA_CURL)
                .build();
        Response response = ok.newCall(request).execute();
        return response.body().string();
    }

}
