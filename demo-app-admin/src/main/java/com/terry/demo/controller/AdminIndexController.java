package com.terry.demo.controller;


import com.terry.demo.core.util.PfPropertyUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminIndexController {

    /**
     * ADMIN INDEX
     * @param res
     * @throws Exception
     */

    @GetMapping(value = "/")
    public void adminIndex(HttpServletResponse res) throws Exception {
        res.setContentType("text/html");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print("ADMIN-" + PfPropertyUtil.getProperty("spring.profiles.active"));
    }

}
