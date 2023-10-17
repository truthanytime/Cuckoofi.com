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

@Entity
@Data
@Table(name = "roles")
@SQLDelete(sql = "UPDATE public.roles SET date_updated = now(), is_deleted = 1, date_deleted = now() WHERE id = ?")
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "date_created")
    private Date dateCreated;

    @JsonIgnore
    @Column(name = "date_updated")
    @UpdateTimestamp
    private Date dateUpdated;

    @JsonIgnore
    @Column(name = "date_deleted")
    private Date dateDeleted;

    @JsonIgnore
    @Column(name = "is_deleted")
    private Integer isDeleted = AuthConstant.IS_DELETED.UN_DELETED;

    public static Role of(Integer id, String name ){
        Role role = new Role();
        role.setId( id );
        role.setName( name );
        return role;
    }
}
