package com.fantasy.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "system_metadata")
public class SystemMetadata {
    
    @Column (name = "key")
    private String key;

    @Column (name = "value")
    private String value;

    public SystemMetadata() {}
    public SystemMetadata(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() { return key; }
    public String getValue() { return value; }

    public void setKey(String key) { this.key = key; }
    public void setValue(String value) { this.value = value; }

    @Override
    public String toString() {
        return String.format("SystemMetadata{key=%s, value=%s}", key, value);
    }
}
