package com.cuckoofi.commonclientlibs.entities;


import com.cuckoofi.commonclientlibs.constant.AuthConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Entity
@Data
@SQLDelete(sql = "UPDATE public.users SET date_updated = now(), is_deleted = 1, date_deleted = now() WHERE id = ?")
@Table(name = "users")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "verified")
    private Integer verified = AuthConstant.EMAIL_VERIFY.NOT_VERIFIED;

    @JsonIgnore
    @Column(name = "social_type")
    private Integer socialType = AuthConstant.SOCIAL_TYPE.DEFAULT;

    @Column(name = "last_login_date")
    private Date lastLoginDate;

    @JsonIgnore
    @Column(name = "disabled_by")
    private String disabledBy;

    @JsonIgnore
    @Column(name = "deleted_by")
    private String deletedBy;

    @Column(name = "disabled")
    private Integer disabled = AuthConstant.IS_DISABLED.UN_DISABLED;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "time_zone")
    private String timeZone = AuthConstant.DEFAULT_TIMEZONE;

    @CreationTimestamp
    @JsonIgnore
    @Column(name = "date_created")
    private Date dateCreated;

    @UpdateTimestamp
    @JsonIgnore
    @Column(name = "date_updated")
    private Date dateUpdated;

    @JsonIgnore
    @Column(name = "date_deleted")
    private Date dateDeleted;

    @Column(name = "is_deleted")
    private Integer isDeleted = AuthConstant.IS_DELETED.UN_DELETED;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public void setFirstName(String firstName ){
        if( firstName != null )
            firstName = Pattern.compile("^.").matcher(firstName).replaceFirst(m -> m.group().toUpperCase());
        this.firstName = firstName;
    }

    public void setLastName( String lastName ){
        if( lastName != null )
            this.lastName = Pattern.compile("^.").matcher(lastName).replaceFirst(m -> m.group().toUpperCase());
    }
}
