package com.zw;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by ZhangWang on 2018/6/15.
 */
public class NodeMapper implements RowMapper<Node> {
    public static final int QUERY_FOR_ALL = 1;
    public static final int QUERY_FOR_GROUP = 2;


    private int status;

    public NodeMapper(int status) {
        this.status = status;
    }

    @Override
    public Node mapRow(ResultSet resultSet, int i) throws SQLException {
        Node node = new Node();
        if (this.status == QUERY_FOR_GROUP) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            node.setTag(metaData.getColumnName(1));
            node.setName(resultSet.getString(node.getTag()));
        } else if (this.status == QUERY_FOR_ALL) {
            node.setName(resultSet.getString("gsajmc"));
            node.setGscfbm(resultSet.getString("gscfbm"));
            node.setGscfjg(resultSet.getString("gscfjg"));
            node.setGsjf(resultSet.getString("gsjf"));
            node.setGsjsrxm(resultSet.getString("gsjsrxm"));
            node.setGshphm(resultSet.getString("gshphm"));
            node.setGscflb(resultSet.getString("gscflb"));
        }
        return node;
    }
}
