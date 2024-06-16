package com.testing.auth_server.dao.entity;


import com.testing.auth_server.dao.utils.Builder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "role_entity")
@Table(name = "roles_table")
@Getter
@Setter
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

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "updating_date", nullable = false)
    private LocalDateTime updatingDate;

    @ManyToMany
    @JoinTable(
            name = "roles_authorities",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<AuthorityEntity> authorities;


    RoleEntity(String roleName,
               String description,
               boolean active,
               LocalDateTime creationDate,
               LocalDateTime updatingDate,
               Set<AuthorityEntity> authorities
    )
    {
        this.roleName = roleName;
        this.description = description;
        this.active = active;
        this.creationDate = creationDate;
        this.updatingDate = updatingDate;
        this.authorities = authorities;
    }


    public static Builder<RoleEntity> builder() {
        return new RoleEntityBuilder();
    }

    private static final class RoleEntityBuilder implements Builder<RoleEntity> {

        private String _roleName;
        private String _description;
        private boolean _active;
        private LocalDateTime _creationDate;
        private LocalDateTime _updatingDate;
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

        public RoleEntityBuilder creationDate(LocalDateTime creationDate) {
            this._creationDate = creationDate;
            return this;
        }

        public RoleEntityBuilder updatingDate(LocalDateTime updatingDate) {
            this._updatingDate = updatingDate;
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
                    this._creationDate,
                    this._updatingDate,
                    this._authorities
            );
        }
    }

}
