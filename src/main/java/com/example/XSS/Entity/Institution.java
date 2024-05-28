package com.example.XSS.Entity;

import java.util.Objects;

public class Institution {
    private String name;
    private String description;
    private String state;

    public Institution(String name, String description, String state) {
        this.name = name;
        this.description = description;
        this.state = state;
    }

    public Institution() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Institution{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Institution that = (Institution) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}

