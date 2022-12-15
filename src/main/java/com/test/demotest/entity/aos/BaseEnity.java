package com.test.demotest.entity.aos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// @MappedSuperclass
// @EntityListeners(AuditingEntityListener.class)
// public class BaseEnity {
    
//     @CreatedBy
//     @Column(name = "created_by", length = 30)
//     protected String createdBy;

//     @CreatedDate
//     @Temporal(TemporalType.TIMESTAMP)
//     @Column(name = "created_date", length = 23)
//     protected Date createdAt;

//     @LastModifiedBy
//     @Column(name = "modified_by",length = 30)
//     protected String modifiedBy;

//     @LastModifiedDate
//     @Temporal(TemporalType.TIMESTAMP)
//     @Column(name = "modified_date",length = 23)
//     protected Date modifiedAt;
    
//     public String getModifiedBy() {
//         return modifiedBy;
//     }
//     public void setModifiedBy(String modifiedBy) {
//         this.modifiedBy = modifiedBy;
//     }
//     public Date getModifiedAt() {
//         return modifiedAt;
//     }
//     public void setModifiedAt(Date modifiedAt) {
//         this.modifiedAt = modifiedAt;
//     }

//     public String getCreatedBy() {
//         return createdBy;
//     }
//     public void setCreatedBy(String createdBy) {
//         this.createdBy = createdBy;
//     }
//     public Date getCreatedAt() {
//         return createdAt;
//     }
//     public void setCreatedAt(Date createdAt) {
//         this.createdAt = createdAt;
//     }

    
// }


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEnity<U> {

    @Column(name = "created_by" ,nullable = false, updatable = false)
    @CreatedBy
    protected U createdBy;

    @Column(name = "created_date" ,nullable = false, updatable = false)
    @CreatedDate()
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;

    @LastModifiedBy
    @Column(name = "modified_by" ,updatable = true)
    protected U modifiedBy;

    @LastModifiedDate
    @Column(name = "modified_date", updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date modifiedDate;

    public U getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(U createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public U getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(U modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}