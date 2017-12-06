package com.example.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Edvard Piri on 05.12.2017.
 */
@Entity
@Table(name = "blacklist")
public class BlackList {
    private long id;
    private Person person;

    public BlackList() {
    }

    public BlackList(long id) {
        this.id = id;
    }

    public BlackList(Person person) {
        this.person = person;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "personal_id")
    public Person getPerson() {
        return person;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlackList blacklist = (BlackList) o;

        if (id != blacklist.id) return false;
        return person != null ? person.equals(blacklist.person) : blacklist.person == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (person != null ? person.hashCode() : 0);
        return result;
    }
}
