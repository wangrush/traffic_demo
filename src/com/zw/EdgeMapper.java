package com.zw;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ZhangWang on 2018/6/15.
 */
public class EdgeMapper implements RowMapper<Edge> {
    @Override
    public Edge mapRow(ResultSet resultSet, int i) throws SQLException {
        Node nodeA = new Node();
        Node nodeB = new Node();
        nodeA.setName(resultSet.getString("d1"));
        nodeB.setName(resultSet.getString("d2"));
        Edge edge = new Edge(nodeA, nodeB);
        edge.setType(resultSet.getMetaData().getColumnName(3));
        return edge;
    }
}
