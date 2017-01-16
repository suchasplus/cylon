package com.weibo.ad.example.controllers;

import com.weibo.ad.example.entity.GuardApiEntity;
import com.weibo.ad.example.entity.JsonReturn;
import com.weibo.ad.example.entity.RespBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/db")
public class MySQL {

    @Autowired
    @Qualifier("ds1jt")
    private JdbcTemplate jt1;

    @Autowired
    @Qualifier("ds2jt")
    private JdbcTemplate jt2;

    @RequestMapping(value="/getListCount")
    public JsonReturn<List<String>> getListCount() {
        Integer userCount = jt1.queryForObject("SELECT count(DISTINCT id) FROM uc_user", Integer.class);
        List<String> ret = new ArrayList<>(14);
        ret.add("13 user count");
        ret.add(": "+userCount);

        List<GuardApiEntity> gal = new ArrayList<>();
        //gal = jt2.query("SELECT * FROM api", new BeanPropertyRowMapper<>(GuardApiEntity.class));

        gal = jt2.query("SELECT * FROM api where name like \"%?%\"",  new BeanPropertyRowMapper<>(GuardApiEntity.class));
        gal.forEach((ga) -> {
            ret.add(ga.toString());
        });
        return RespBuilder.build(ret, 0, "" );
    }
}
//    GuardApiEntity gae = new GuardApiEntity();
//            gae.setName(rs.getString("name"));
//                    gae.setId(rs.getInt("id"));
//                    gae.setDesc(rs.getString("desc"));
//                    return gae;