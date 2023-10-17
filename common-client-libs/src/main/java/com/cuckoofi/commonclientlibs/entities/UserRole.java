package com.cuckoofi.commonclientlibs.entities;

import com.cuckoofi.commonclientlibs.constant.AuthConstant;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "user_roles")
@SQLDelete(sql = "UPDATE user_roles SET date_deleted = now() WHERE id = ?")
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @CreationTimestamp
    @Column(name = "date_created")
    private Date dateCreated;

    @UpdateTimestamp
    @Column(name = "date_updated")
    private Date dateUpdated;

    @Column(name = "date_deleted")
    private Date dateDeleted;

    @Column(name = "is_deleted")
    private Integer isDeleted = AuthConstant.IS_DELETED.UN_DELETED;
}
