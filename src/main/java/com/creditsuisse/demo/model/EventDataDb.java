package com.creditsuisse.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "EventDataDb")
public class EventDataDb {

    @Id
    private String id;
    @Column
    private long duration;
    @Column
    private String type;
    @Column
    private String host;
    @Column
    private Boolean alert;

    public EventDataDb(String id, long duration, String type, String host, Boolean alert) {
        this.id = id;
        this.duration = duration;
        this.type = type;
        this.host = host;
        this.alert = alert;
    }

    public EventDataDb() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDataDb that = (EventDataDb) o;
        return duration == that.duration && Objects.equals(id, that.id) && Objects.equals(type, that.type) && Objects.equals(host, that.host) && Objects.equals(alert, that.alert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, duration, type, host, alert);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Boolean getAlert() {
        return alert;
    }

    public void setAlert(Boolean alert) {
        this.alert = alert;
    }
}