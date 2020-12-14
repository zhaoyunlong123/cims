package com.javakc.cims.subsidy.entity;

import com.javakc.cims.person.entity.Person;

import java.math.BigDecimal;

public class Subsidy {
    private int id;
    private String month;
    private BigDecimal money;
    private int type;
    private Person person;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Subsidy{" +
                "id=" + id +
                ", month='" + month + '\'' +
                ", money=" + money +
                ", type=" + type +
                ", person=" + person +
                '}';
    }
}
