package com.atm.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date createDate;

    @Column(name = "type")
    private String type;

    @Column(name = "active")
    private boolean active;

    @Column(name = "acct_balance")
    private long acctBalance;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    @JsonManagedReference
    private Set<User> users;
}
