package com.example.model;

import javax.persistence.*;

/**
 * Created by Edvard Piri on 05.12.2017.
 */
@Entity
@Table(name = "loan")
public class Loan {
    private long id;
    private double amount;
    private String term;
    private Person person;

    public Loan() {
    }

    public Loan(long id) {
        this.id = id;
    }

    public Loan(double amount, String term, Person person) {
        this.amount = amount;
        this.term = term;
        this.person = person;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Column(name = "term")
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @ManyToOne
    @JoinColumn(name = "personal_id")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Loan loan = (Loan) o;

        if (id != loan.id) return false;
        if (Double.compare(loan.amount, amount) != 0) return false;
        return term != null ? term.equals(loan.term) : loan.term == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (term != null ? term.hashCode() : 0);
        return result;
    }
}
