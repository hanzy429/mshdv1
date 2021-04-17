package com.earthquake.managementPlatform.entities;

public class BackupTime {
    private int id;
    private int time;

    public BackupTime(int id, int time) {
        this.id = id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
