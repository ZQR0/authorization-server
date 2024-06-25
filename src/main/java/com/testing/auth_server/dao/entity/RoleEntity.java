package com.testing.auth_server.dao.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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

@Entity(name = "role_entity")
@Table(name = "roles_table", schema = "auth_server")
@Getter
@Setter
@NoArgsConstructor
public class RoleEntity extends AbstractEntity {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", unique = true, length = 50, nullable = false)
    private String roleName;

    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Column(name = "active", nullable = false)
    private Boolean active;

//    @CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "role_creation_date", nullable = false)
    private LocalDateTime creationDate;

//    @UpdateTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "role_updating_date", nullable = false)
    private LocalDateTime updatingDate;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_authorities",
            schema = "auth_server",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<AuthorityEntity> authorities = new HashSet<>();



    public RoleEntity(String roleName,
               String description,
               boolean active,
               Set<AuthorityEntity> authorities
    )
    {
        this.roleName = roleName;
        this.description = description;
        this.active = active;
        this.authorities = authorities;
    }


    public static RoleEntityBuilder builder() {
        return new RoleEntityBuilder();
    }

    public static final class RoleEntityBuilder implements Builder<RoleEntity> {

        private String _roleName;
        private String _description;
        private boolean _active;
        private Set<AuthorityEntity> _authorities;


        public RoleEntityBuilder roleName(String roleName) {
            this._roleName = roleName;
            return this;
        }

        public RoleEntityBuilder description(String description) {
            this._description = description;
            return this;
        }

        public RoleEntityBuilder active(boolean active) {
            this._active = active;
            return this;
        }


        public RoleEntityBuilder authorities(Set<AuthorityEntity> authorities) {
            this._authorities = authorities;
            return this;
        }

        @Override
        public RoleEntity build() {
            return new RoleEntity(
                    this._roleName,
                    this._description,
                    this._active,
                    this._authorities
            );
        }
    }

}
