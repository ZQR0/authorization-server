package com.testing.auth_server.dao.entity;


import com.testing.auth_server.dao.utils.Builder;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "user_entity")
@Table(name = "users_table", schema = "auth_server")
@Getter
@Setter
@NoArgsConstructor
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

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

//    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "user_creation_date", nullable = false)
    private LocalDateTime creationDate;

//    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "user_updating_date")
    private LocalDateTime updatingDate;


    @Column(name = "active", nullable = false)
    private Boolean active;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            schema = "auth_server",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();


    public UserEntity(String email,
               String username,
               String passwordHash,
               LocalDate birthday,
               boolean active,
               Set<RoleEntity> roles
               )
    {
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.birthday = birthday;
        this.active = active;
        this.roles = roles;
    }


    public static UserEntityBuilder builder() {
        return new UserEntityBuilder();
    }


    public static final class UserEntityBuilder implements Builder<UserEntity> {

        private String _email;
        private String _username;
        private String _passwordHash;
        private LocalDate _birthday;
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
                    this._active,
                    this._roles
            );
        }
    }

}
