/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "promotions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promotions.findAll", query = "SELECT p FROM Promotions p"),
    @NamedQuery(name = "Promotions.findByPromotionID", query = "SELECT p FROM Promotions p WHERE p.promotionID = :promotionID"),
    @NamedQuery(name = "Promotions.findByCode", query = "SELECT p FROM Promotions p WHERE p.code = :code"),
    @NamedQuery(name = "Promotions.findByType", query = "SELECT p FROM Promotions p WHERE p.type = :type"),
    @NamedQuery(name = "Promotions.findByValue", query = "SELECT p FROM Promotions p WHERE p.value = :value"),
    @NamedQuery(name = "Promotions.findByQuantity", query = "SELECT p FROM Promotions p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "Promotions.findByExpirationDate", query = "SELECT p FROM Promotions p WHERE p.expirationDate = :expirationDate"),
    @NamedQuery(name = "Promotions.findByCreatedDate", query = "SELECT p FROM Promotions p WHERE p.createdDate = :createdDate")})
public class Promotions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PromotionID")
    private Integer promotionID;
    @Basic(optional = false)
    @NotNull(message = "{error.field.required}")
    @Size(min = 12, max = 50, message = "{error.promotions.code.length}")
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @NotNull(message = "{error.field.required}")
    @Size(min = 1, max = 20)
    @Column(name = "Type")
    private String type;
    @Basic(optional = false)
    @NotNull(message = "")
    @Column(name = "Value")
    private int value;
    @Basic(optional = false)
    @NotNull(message = "{error.field.required}")
    @Column(name = "Quantity")
    @Min(value = 1, message = "{error.field.invalid}")
    private int quantity;
    @Basic(optional = false)
    @NotNull(message = "{error.field.required}")
    @Column(name = "ExpirationDate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expirationDate;
    @Basic(optional = false)
    @Column(name = "created_date", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdDate;

    public Promotions() {
    }

    public Promotions(Integer promotionID) {
        this.promotionID = promotionID;
    }

    public Promotions(Integer promotionID, String code, String type, int value, int quantity, Date expirationDate, Date createdDate) {
        this.promotionID = promotionID;
        this.code = code;
        this.type = type;
        this.value = value;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.createdDate = createdDate;
    }

    public Integer getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(Integer promotionID) {
        this.promotionID = promotionID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promotionID != null ? promotionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotions)) {
            return false;
        }
        Promotions other = (Promotions) object;
        if ((this.promotionID == null && other.promotionID != null) || (this.promotionID != null && !this.promotionID.equals(other.promotionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dvtd.pojo.Promotions[ promotionID=" + promotionID + " ]";
    }

    @AssertTrue(message = "{error.promotions.value.INVALID.percentage}")
    public boolean isPercentageValueValid() {
        if ("percentage".equals(this.type)) {
            return this.value >= 0 && this.value <= 100;
        }
        return true;
    }

    @AssertTrue(message = "{error.promotions.value.INVALID.fixedValue}")
    public boolean isFixedCostValueValid() {
        if ("fixedValue".equals(this.type)) {
            return this.value >= 1000 && this.value <= 1000000000;
        }
        return true;
    }

}
