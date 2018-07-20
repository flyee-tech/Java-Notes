package com.peierlong.lambda;

/**
 * @author elong
 * @version V1.0
 * @date 2018/7/20
 */
public class Artist {

    private String name;
    private String from;

    public Artist() {
    }

    public Artist(String name, String from) {
        this.name = name;
        this.from = from;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public boolean isFrom(String from) {
        if (from == null) {
            return false;
        }
        return from.equals(this.from);
    }

}
