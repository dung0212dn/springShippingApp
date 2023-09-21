/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "shippers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shippers.findAll", query = "SELECT s FROM Shippers s"),
    @NamedQuery(name = "Shippers.findByShipperID", query = "SELECT s FROM Shippers s WHERE s.shipperID = :shipperID"),
    @NamedQuery(name = "Shippers.findByCmnd", query = "SELECT s FROM Shippers s WHERE s.cmnd = :cmnd"),
    @NamedQuery(name = "Shippers.findByCreatedDate", query = "SELECT s FROM Shippers s WHERE s.createdDate = :createdDate")})
public class Shippers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShipperID")
    private Integer shipperID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CMND")
    private String cmnd;
    @Basic(optional = false)
    @Column(name = "created_date", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdDate;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    @ManyToOne(optional = false)
    private Users userID;
    @OneToMany(mappedBy = "chosenShipperID")
    @JsonIgnore
    private Set<Orders> ordersSet1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipperID")
    @JsonIgnore
    private Set<Bid> bidSet;

    public Shippers() {
    }

    public Shippers(Integer shipperID) {
        this.shipperID = shipperID;
    }

    public Shippers(Integer shipperID, String cmnd, Date createdDate) {
        this.shipperID = shipperID;
        this.cmnd = cmnd;
        this.createdDate = createdDate;
    }

    public Integer getShipperID() {
        return shipperID;
    }

    public void setShipperID(Integer shipperID) {
        this.shipperID = shipperID;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Users getUserID() {
        return userID;
    }

    public void setUserID(Users userID) {
        this.userID = userID;
    }

    @XmlTransient
    public Set<Orders> getOrdersSet1() {
        return ordersSet1;
    }

    public void setOrdersSet1(Set<Orders> ordersSet1) {
        this.ordersSet1 = ordersSet1;
    }

    @XmlTransient
    public Set<Bid> getBidSet() {
        return bidSet;
    }

    public void setBidSet(Set<Bid> bidSet) {
        this.bidSet = bidSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shipperID != null ? shipperID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shippers)) {
            return false;
        }
        Shippers other = (Shippers) object;
        if ((this.shipperID == null && other.shipperID != null) || (this.shipperID != null && !this.shipperID.equals(other.shipperID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dvtd.pojo.Shippers[ shipperID=" + shipperID + " ]";
    }

}
