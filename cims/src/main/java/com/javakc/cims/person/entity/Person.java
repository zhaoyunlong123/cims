package com.javakc.cims.person.entity;

import java.util.Date;

/**
 * [干部收入管理系统]
 * [人员管理-人员对象]
 */
public class Person {
    private int id;
    private String name;
    private String personCard;
    private int state;
    private int grade;
    private Date startedDate;
    private int hotting;
    private int property;
    private String reasons;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonCard() {
        return personCard;
    }

    public void setPersonCard(String personCard) {
        this.personCard = personCard;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startDate) {
        this.startedDate = startDate;
    }

    public int getHotting() {
        return hotting;
    }

    public void setHotting(int hotting) {
        this.hotting = hotting;
    }

    public int getProperty() {
        return property;
    }

    public void setProperty(int property) {
        this.property = property;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", personCard='" + personCard + '\'' +
                ", state=" + state +
                ", grade=" + grade +
                ", startedDate=" + startedDate +
                ", hotting=" + hotting +
                ", property=" + property +
                ", reasons='" + reasons + '\'' +
                '}';
    }
}
