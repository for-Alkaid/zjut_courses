package com.zjut.qll.pojo;


public class Position {
    private int position_id;
    private String position_name;

    public Position() {
    }

    public Position(int position_id, String position_name) {
        this.position_id = position_id;
        this.position_name = position_name;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    @Override
    public String toString() {
        return "Position{" +
                "position_id=" + position_id +
                ", position_name='" + position_name + '\'' +
                '}';
    }
}
