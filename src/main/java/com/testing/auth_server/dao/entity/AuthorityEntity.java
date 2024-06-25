package com.testing.auth_server.dao.entity;

import com.testing.auth_server.dao.utils.Builder;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "authority_entity")
@Table(name = "authorities_table", schema = "auth_server")
@Getter
@Setter
@NoArgsConstructor
public class AuthorityEntity extends AbstractEntity {

    @Id
    @Column(name = "authority_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true, nullable = false, length = 100)
    private String code;

    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Column(name = "system_code", nullable = false)
    private String systemCode;

    @Column(name = "active", nullable = false)
    private Boolean active;


    public AuthorityEntity(
            String code,
            String description,
            String systemCode,
            boolean active
    )
    {
        this.code = code;
        this.description = description;
        this.systemCode = systemCode;
        this.active = active;
    }

    public static AuthorityEntityBuilder builder() {
        return new AuthorityEntityBuilder();
    }

    public static final class AuthorityEntityBuilder implements Builder<AuthorityEntity> {

        private String _code;
        private String _description;
        private String _systemCode;
        private boolean _active;


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

        @Override
        public AuthorityEntity build() {
            return new AuthorityEntity(
                    this._code,
                    this._description,
                    this._systemCode,
                    this._active
            );
        }
    }

}
