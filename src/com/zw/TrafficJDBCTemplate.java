package com.zw;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ZhangWang on 2018/6/15.
 */
public class TrafficJDBCTemplate {
    private DataSource dataSource;
    private JdbcTemplate template;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new JdbcTemplate(dataSource);
    }

    public List<Node> getNodes() {
        String sql = "select * from nodes";
        List<Node> trafficList = template.query(sql, new NodeMapper
                (NodeMapper.QUERY_FOR_ALL));
        return trafficList;
    }

    public List<Node> getGroups(String groupName) {
        String sql = String.format("select %s, count(*) as num from " +
                        "data_detail group by %s having count(*)>1", groupName,
                groupName);
        List<Node> groups = template.query(sql, new NodeMapper(NodeMapper
                .QUERY_FOR_GROUP));
        return groups;
    }

    public List<Edge> getEachGroupEdge(Node group) {
        String sql = String.format("select d1.gsajmc as d1, d2.gsajmc as d2, " +
                "d1.%s from data_detail as d1, data_detail as d2 where " +
                "d1.%s='%s' and d1.gsajmc in (select gsajmc from nodes) and " +
                "d2.gsajmc in (select gsajmc from nodes) limit 5", group
                .getTag(), group.getTag(), group.getName());
        List<Edge> edges = template.query(sql, new EdgeMapper());
        return edges;
    }

    public List<Edge> getEdges(String groupName) {
        List<Edge> edgeList = new ArrayList<>();
        List<Node> groupList = getGroups(groupName);
        for (Node group : groupList) {
            List<Edge> tmpEdgeList = getEachGroupEdge(group);
            edgeList.addAll(tmpEdgeList);
        }
        return edgeList;
    }
}
