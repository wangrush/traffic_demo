package com.zw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Controller
public class AppointmentsController {
    @RequestMapping(path = "/test")
    public String printHello(ModelMap model) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("database" +
                ".xml");
        TrafficJDBCTemplate template;
        template = (TrafficJDBCTemplate) ctx.getBean("trafficJDBCTemplate");
        List<Node> trafficList = template.getNodes();

        model.addAttribute("nodesList", trafficList);
        model.addAttribute("gscfbm", template.getEdges("gscfbm"));
        model.addAttribute("gscflb", template.getEdges("gscflb"));
        model.addAttribute("gscfjg", template.getEdges("gscfjg"));
        model.addAttribute("gsjf", template.getEdges("gsjf"));
        return "hello";
    }
}