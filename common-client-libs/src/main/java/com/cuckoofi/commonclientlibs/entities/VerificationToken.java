package com.cuckoofi.commonclientlibs.entities;

import com.cuckoofi.commonclientlibs.utils.CommonUtil;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@SQLDelete(sql = "UPDATE public.verification_token SET date_updated = now(), is_deleted = 1, date_deleted = now() WHERE id = ?")
@Table(name = "verification_token")
public class VerificationToken implements Serializable {

    public VerificationToken() {
        super();
    }

    public VerificationToken(String token, User user) {
        this.id = token;
        this.user = user;
        this.expiryDate = CommonUtil.calculateExpiryDate(60 * 24); // 1 day
    }

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(name = "expiry_date", nullable = false)
    private Date expiryDate;

    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @UpdateTimestamp
    @Column(name = "date_updated", nullable = false)
    private Date dateUpdated;

    @UpdateTimestamp
    @Column(name = "date_deleted")
    private Date dateDeleted;

    @Column(name = "is_deleted", nullable = false)
    private Integer isDeleted;
}
