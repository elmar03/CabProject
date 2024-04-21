//package com.example.cabproject.service;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.SecondaryRow;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//@Getter
//@Setter
//
//public class UserDetailsImpl implements UserDetails {
//    private Long id;
//
//    private String name;
//    private String surName;
//    private int age;
//    private String emailAddress;
//    private String gender;
//    private String phoneNumber;
//    private String homeAddress;
//    private String cardDetails;
//    private String language;
//
//    public Long getId() {
//        return id;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}
