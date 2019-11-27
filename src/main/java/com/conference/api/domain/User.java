package com.conference.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

/**
 * Created by Alex Sharman 22 Nov 2019
 */


@Getter
@Setter
@Entity
@Table(name = "tbl_users", uniqueConstraints = {@UniqueConstraint(columnNames = {"login"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Please provide a name")
    @Length(max = 50)
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Please provide a surname")
    @Size(max = 100)
    @Column(name = "surname")
    private String surname;

    @NotEmpty(message = "Please provide a login")
    @Size(max = 100)
    @Column(name = "login",
            unique = true)
    private String login;

    @NotNull(message = "Please provide a password, minimum length is 6")
    @Size(min = 6, max = 100, message = "Password but be between 6 and 100 characters in length")
    @Column(name = "password")
//    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns
            = @JoinColumn(name = "user_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))
    private List<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, login, password, roles);
    }
}

