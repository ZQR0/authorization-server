package com.testing.auth_server.dao.entity;


import com.testing.auth_server.dao.utils.Builder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "user_entity")
@Table(name = "users_table")
@Getter
@Setter
public class UserEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "updating_date")
    private LocalDateTime updatingDate;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<RoleEntity> roles;


    UserEntity(String email,
               String username,
               String passwordHash,
               LocalDate birthday,
               LocalDateTime creationDate,
               LocalDateTime updatingDate,
               boolean active,
               Set<RoleEntity> roles
               )
    {
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.birthday = birthday;
        this.creationDate = creationDate;
        this.updatingDate = updatingDate;
        this.active = active;
        this.roles = roles;
    }

    public static Builder<UserEntity> builder() {
        return new UserEntityBuilder();
    }


    private static final class UserEntityBuilder implements Builder<UserEntity> {

        private String _email;
        private String _username;
        private String _passwordHash;
        private LocalDate _birthday;
        private LocalDateTime _creationDate;
        private LocalDateTime _updatingDate;
        private boolean _active;
        private Set<RoleEntity> _roles;


        public UserEntityBuilder email(String email) {
            this._email = email;
            return this;
        }

        public UserEntityBuilder username(String username) {
            this._username = username;
            return this;
        }

        public UserEntityBuilder passwordHash(String passwordHash) {
            this._passwordHash = passwordHash;
            return this;
        }

        public UserEntityBuilder birthday(LocalDate birthday) {
            this._birthday = birthday;
            return this;
        }

        public UserEntityBuilder creationDate(LocalDateTime creationDate) {
            this._creationDate = creationDate;
            return this;
        }

        public UserEntityBuilder updatingDate(LocalDateTime updatingDate) {
            this._updatingDate = updatingDate;
            return this;
        }

        public UserEntityBuilder active(boolean active) {
            this._active = active;
            return this;
        }

        public UserEntityBuilder roles(Set<RoleEntity> roles) {
            this._roles = roles;
            return this;
        }

        @Override
        public UserEntity build() {
            return new UserEntity(
                    this._email,
                    this._username,
                    this._passwordHash,
                    this._birthday,
                    this._creationDate,
                    this._updatingDate,
                    this._active,
                    this._roles
            );
        }
    }

}
