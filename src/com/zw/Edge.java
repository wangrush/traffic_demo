package com.zw;

/**
 * Created by ZhangWang on 2018/6/15.
 */
public class Edge {
    private Node nodeA, nodeB;
    private String type;

    public Edge(Node nodeA, Node nodeB) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    public Node getNodeA() {
        return nodeA;
    }

    public void setNodeA(Node nodeA) {
        this.nodeA = nodeA;
    }

    public Node getNodeB() {
        return nodeB;
    }

    public void setNodeB(Node nodeB) {
        this.nodeB = nodeB;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
