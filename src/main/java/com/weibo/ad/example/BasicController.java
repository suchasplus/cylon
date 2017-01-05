package com.weibo.ad.example;


import com.weibo.ad.example.entity.BasicJsonEntity;
import com.weibo.ad.example.entity.RespBuilder;
import com.weibo.ad.example.entity.JsonReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@RequestMapping("/basic")
@Controller
public class BasicController {
    private static Logger logger = LoggerFactory.getLogger(BasicController.class);

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        logger.info("helloworld invoked " + new Date().toInstant());
        return "hello world!";
    }

    @GetMapping("/egpcs")
    public String foregpcs() {
        return "basic/foregpcs";
    }

    /**
     * Basic Demo of PHP E G P C S via Servlet
     * @return Response Body String
     */
    @RequestMapping(value = "/egpcs", method = RequestMethod.POST)
    @ResponseBody
    public String egpcs() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();
        logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        StringBuilder sb = new StringBuilder(10240);

        //ENV
        Map<String, String> env = System.getenv();
        String lang = env.get("LANG");
        String home = env.get("HOME");
        String loginName = env.get("LOGNAME");
        sb.append(String.format(
                "ENV Lang : %s, home dir: %s, loginName: %s<br />",
                lang,
                home,
                loginName
        ));

        //GET
        // Example: http://localhost:8080/people?lastname=Fox&age=30

        String uri = request.getScheme() + "://" +   // "http" + "://
                request.getServerName() +       // "localhost"
                ":" +                           // ":"
                request.getServerPort() +       // "8080"
                request.getRequestURI() +       // "/people"
                "?" +                           // "?"
                request.getQueryString();       // "lastname=Fox&age=30"

        String lastName = request.getParameter("lastname");
        sb.append(String.format(
                "GET lastName: %s <br />",
                lastName
        ));


        //POST  getParameter
        String userName = request.getParameter("username");
        String szUid = request.getParameter("uid");
        Integer uid = Integer.valueOf(szUid);

        sb.append("POST username: ").append(userName).append("<br />");
        sb.append("uid: ").append(uid).append("<br />");

        //COOKIE
        Cookie[] cookiez = request.getCookies();
        Arrays.asList(cookiez).forEach((ck) -> {
            sb.append(String.format("Cookie name: %s, domain: %s, path: %s, secure: %s, value: %s, httponly: %s <br/>",
                    ck.getName(),
                    ck.getDomain(),
                    ck.getPath(),
                    ck.getSecure(),
                    ck.getValue(),
                    ck.isHttpOnly()
                    )
            );
        });

        //SERVER
        String remoteAddr = request.getRemoteAddr();
        String localAddr = request.getLocalAddr();
        String authType = request.getAuthType();
        String method = request.getMethod();

        sb.append(String.format(
                "remoteAddr: %s , localAddr: %s, authType: %s, method: %s <br/>",
                remoteAddr,
                localAddr,
                authType,
                method
        ));
        return sb.toString();
    }

    /**
     * 直接返回一个对象的json数据
     */
    @GetMapping(value = "/json",
            //consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    // http://localhost:8080/basic/json/
    public BasicJsonEntity json(HttpServletRequest request) {
        logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        BasicJsonEntity bje = new BasicJsonEntity();

        bje.setId(((Double)(Math.random() * 1000 + 1)).intValue());
        bje.setDate(new Date());
        bje.setName(BasicJsonEntity.class.toString());

        return bje;
    }

    /**
     * 标准json返回
     */
    @GetMapping(value = "/json/advanced", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    // http://localhost:8080/basic/json/advanced
    public JsonReturn<List<BasicJsonEntity>> advancedJson(HttpServletRequest request) {
        logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        BasicJsonEntity bje = new BasicJsonEntity();

        bje.setId(((Double)(Math.random() * 1000 + 1)).intValue());
        bje.setDate(new Date());
        bje.setName(BasicJsonEntity.class.toString());

        BasicJsonEntity bje2 = new BasicJsonEntity();

        bje2.setId(((Double)(Math.random() * 1000 + 1)).intValue());
        bje2.setDate(new Date());
        bje2.setName(BasicController.class.toString());

        List<BasicJsonEntity> list = new ArrayList<>(2);
        list.add(bje);
        list.add(bje2);

        return RespBuilder.build(list, 0, "");
    }

    /**
     *
     *
     * @param item_id item_id in url path
     * @return String
     */
    @RequestMapping(value = "/item/{item_id}", method = RequestMethod.GET)
    @ResponseBody
    public String pathVar(@PathVariable("item_id") Integer item_id) {

        // GET /basic/item/123
        return "item id:" + item_id;
    }


    @GetMapping(value = "/url")
    @ResponseBody
    public String urlParam(@RequestParam("id") Integer id) {
        // GET /basic/url/?id=123
        return "id: " + id;
    }

    @RequestMapping(value = "/param", params = "test=true")
    @ResponseBody
    public String withParamTrue() {

        return "with Param test true";
    }

    @RequestMapping(value = "/param", params = "test!=true")
    @ResponseBody
    public String withParamNotTrue() {

        return "with Param test NOT true";
    }

    @RequestMapping(value = "/paramin", params = "testParam")
    @ResponseBody
    public String withParam() {

        return "with Param test";
    }

    @RequestMapping(value = "/paramin", params = "!testParam")
    @ResponseBody
    public String withoutParam() {

        return "withOUT Param test";
    }

    /*
     * examples:
     *
        @RequestMapping(value = "/find", params = "target")
        public String find1(){
            return "example_find1_page";
        }

        @RequestMapping(value = "/find", params = "!target")
        public String find2(){
            return "example_find2_page";
        }

        @RequestMapping(value = "/search", params = "target=product")
        public String search1(){
            return "example_search1_page";
        }

        @RequestMapping(value = "/search", params = "target!=product")
        public String search2(){
            return "example_search2_page";
        }

         @RequestMapping(value = "/specify", headers = "accept=text/*")
         public String specify(){
         return "example_specify_page";
         }
        //请求的 Request Headers 中 Accept 的值必须匹配 text/* ( 如 text/html )，方法才会被调用
    */

    /*
     * 上传接口, 具体看下面的forupload
     * http://www.cnblogs.com/-crazysnail/p/3923135.html
     * https://spring.io/guides/gs/uploading-files/
     */
    @PostMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) throws IOException{
        String saveFileName = "/tmp/file_"+ System.currentTimeMillis() + "_" + ((Double)(Math.random()*1024 + 1)).intValue() ;
        Files.copy(file.getInputStream(), Paths.get(saveFileName));

        return String.format("upload ok: %s %s %s %s",
                file.getOriginalFilename(),
                file.getName(),
                file.getSize(),
                saveFileName
        );
    }

    /*
     上传页面
     */
    @GetMapping("/upload")
    public String forupload(Map<String,Object> map) {
        map.put("name", "FTL");
        return "basic/forupload";
    }



}
