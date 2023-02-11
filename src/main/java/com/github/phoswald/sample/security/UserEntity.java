package com.github.phoswald.sample.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

@Entity
@Table(name = "user")
@UserDefinition
public class UserEntity {

	@Id
    @Column(name = "username")
    @Username
    public String username;

    @Column(name = "bcrypt_password")
    @Password
    public String password;

    @Column(name = "roles")
    @Roles
    public String roles;

//    /**
//     * Adds a new user in the database
//     * @param username the user name
//     * @param password the unencrypted password (it will be encrypted with bcrypt)
//     * @param role the comma-separated roles
//     */
//    public static void add(String username, String password, String role) {
//        User user = new User();
//        user.username = username;
//        user.password = BcryptUtil.bcryptHash(password);
//        user.role = role;
//        user.persist();
//    }
}