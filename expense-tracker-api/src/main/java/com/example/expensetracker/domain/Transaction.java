package com.example.expensetracker.domain;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transaction_id")
    private Integer Id;
    private double amount;
    private String note;


    @Column(name="transaction_date")
    private Date transactionDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="category_id")
    private Category category;


    public Transaction() {
    }

    public Transaction(Integer id, double amount, String note, User user, Category category) {
        Id = id;
        this.amount = amount;
        this.note = note;
        this.user = user;
        this.category=category;
    }

    public Transaction(Integer id, double  amount, String note, Date transactionDate, User user, Category category) {
        Id = id;
        this.amount = amount;
        this.note = note;
        this.transactionDate = transactionDate;
        this.user = user;
        this.category = category;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
