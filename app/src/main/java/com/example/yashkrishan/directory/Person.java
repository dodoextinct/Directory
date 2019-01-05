package com.example.yashkrishan.directory;

/**
 * Created by yashkrishan on 4/1/19.
 */

public class Person {
    private String bg;
    private String dob;
    private String id;
    private String loc;
    private String mob;
    public String name;

    public Person(String bg, String dob, String id, String loc, String mob, String name) {
        this.bg = bg;
        this.dob = dob;
        this.id = id;
        this.loc = loc;
        this.mob = mob;
        this.name = name;
    }

    public Person(){}

    public void setName(String name) {
        this.name = name;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public String getLoc() {
        return loc;
    }

    public String getMob() {
        return mob;
    }

    public String getDob() {
        return dob;
    }

    public String getBg() {
        return bg;
    }

    public String getId() {
        return id;
    }
}
