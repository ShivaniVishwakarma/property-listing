package com.engelandvolkers.property_listing.entities;

import jakarta.persistence.*;
import lombok.Builder;

@Builder
@Entity
public class PropertyView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "property")
    private Property property;

    private String pid;

    private String username;

    public PropertyView() {
    }

    public PropertyView(Long id, Property property, String username) {
        this.id = id;
        this.property = property;
        this.username = username;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
