package com.testing.auth_server.dao.entity;

import com.testing.auth_server.dao.utils.Builder;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "authority_entity")
@Table(name = "")
public class AuthorityEntity extends AbstractEntity {

    @Id
    @Column(name = "authority_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Column(name = "system_code", nullable = false)
    private String systemCode;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "updating_date", nullable = false)
    private LocalDateTime updatingDate;


    AuthorityEntity(
            String code,
            String description,
            String systemCode,
            boolean active,
            LocalDateTime creationDate,
            LocalDateTime updatingDate
    )
    {
        this.code = code;
        this.description = description;
        this.systemCode = systemCode;
        this.active = active;
        this.creationDate = creationDate;
        this.updatingDate = updatingDate;
    }

    public static Builder<AuthorityEntity> builder() {
        return new AuthorityEntityBuilder();
    }

    private static final class AuthorityEntityBuilder implements Builder<AuthorityEntity> {

        private String _code;
        private String _description;
        private String _systemCode;
        private boolean _active;
        private LocalDateTime _creationDate;
        private LocalDateTime _updatingDate;


        public AuthorityEntityBuilder code(String code) {
            this._code = code;
            return this;
        }

        public AuthorityEntityBuilder description(String description) {
            this._description = description;
            return this;
        }

        public AuthorityEntityBuilder systemCode(String systemCode) {
            this._systemCode = systemCode;
            return this;
        }

        public AuthorityEntityBuilder active(boolean active) {
            this._active = active;
            return this;
        }

        public AuthorityEntityBuilder creationDate(LocalDateTime creationDate) {
            this._creationDate = creationDate;
            return this;
        }

        public AuthorityEntityBuilder updatingDate(LocalDateTime updatingDate) {
            this._updatingDate = updatingDate;
            return this;
        }

        @Override
        public AuthorityEntity build() {
            return new AuthorityEntity(
                    this._code,
                    this._description,
                    this._systemCode,
                    this._active,
                    this._creationDate,
                    this._updatingDate
            );
        }
    }

}
