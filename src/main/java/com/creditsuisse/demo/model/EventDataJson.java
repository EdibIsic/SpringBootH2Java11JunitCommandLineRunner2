package com.creditsuisse.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class EventDataJson {
    @Id
    private String id;
    private String state;
    private String type;
    private String host;
    private long timestamp;


    public EventDataJson(String id, String state, String type, String host, long timestamp) {
        this.id = id;
        this.state = state;
        this.type = type;
        this.host = host;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDataJson eventData = (EventDataJson) o;
        return Objects.equals(id, eventData.id) && Objects.equals(state, eventData.state) && Objects.equals(type, eventData.type) && Objects.equals(host, eventData.host) && Objects.equals(timestamp, eventData.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state, type, host, timestamp);
    }
}
