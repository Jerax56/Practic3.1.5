package ru.kata.spring.boot_security.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private int age;
    @Column
    private String email;
    @Column
    private String password;
    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
    }

    public User(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(String firstName, String lastName, int age, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int ID) {
        this.id = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String surname) {
        this.lastName = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }
    @JsonIgnore
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + firstName + '\'' +
                ", surname='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}