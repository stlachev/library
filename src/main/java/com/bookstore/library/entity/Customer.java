package com.bookstore.library.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.library.auth.Role;
import com.bookstore.library.entity.auth.Token;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
@Table(name = "customers")
@Entity
public class Customer implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private String telephone;

    private String address;

    @BatchSize(size=10)
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)  //FetchType.EAGER
    private List<Orders> orders = new ArrayList<Orders>();

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @BatchSize(size=10)
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "customer")
    private List<Token> tokens;

    private LocalDateTime created;

    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addOrder(Orders order) {
        this.orders.add(order);
        order.setCustomer(this);
    }

    public void removeOrder(Orders order) {
        order.setOrders(null);
        this.orders.remove(order);
    }

    public void removeOrder() {
        Iterator<Orders> iterator = this.orders.iterator();
        while (iterator.hasNext()) {
            Orders orders = iterator.next();
            orders.setOrders(null);
            iterator.remove();
        }
    }

    @Transactional(readOnly= true)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transactional(readOnly= true)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transactional(readOnly= true)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Transactional(readOnly= true)
    public List<Orders> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
/*
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", customer_name='" + getCustomer_name() + "'" +
            ", customer_address='" + getCustomer_address() + "'" +
            ", orders='" + getOrders() + "'" +
            "}";
    }
*/
}