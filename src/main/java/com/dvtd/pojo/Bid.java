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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "bid")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bid.findAll", query = "SELECT b FROM Bid b"),
    @NamedQuery(name = "Bid.findByBidID", query = "SELECT b FROM Bid b WHERE b.bidID = :bidID"),
    @NamedQuery(name = "Bid.findByPrice", query = "SELECT b FROM Bid b WHERE b.price = :price"),
    @NamedQuery(name = "Bid.findByBidTime", query = "SELECT b FROM Bid b WHERE b.bidTime = :bidTime"),
    @NamedQuery(name = "Bid.findByIsChosen", query = "SELECT b FROM Bid b WHERE b.isChosen = :isChosen"),
    @NamedQuery(name = "Bid.findByCreatedDate", query = "SELECT b FROM Bid b WHERE b.createdDate = :createdDate")})
public class Bid implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BidID")
    private Integer bidID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BidTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bidTime;
    @Column(name = "IsChosen")
    private Boolean isChosen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID")
    @ManyToOne(optional = false)
    private Orders orderID;
    @JoinColumn(name = "ShipperID", referencedColumnName = "UserID")
    @ManyToOne(optional = false)
    private Users shipperID;

    public Bid() {
    }

    public Bid(Integer bidID) {
        this.bidID = bidID;
    }

    public Bid(Integer bidID, int price, Date bidTime, Date createdDate) {
        this.bidID = bidID;
        this.price = price;
        this.bidTime = bidTime;
        this.createdDate = createdDate;
    }

    public Integer getBidID() {
        return bidID;
    }

    public void setBidID(Integer bidID) {
        this.bidID = bidID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getBidTime() {
        return bidTime;
    }

    public void setBidTime(Date bidTime) {
        this.bidTime = bidTime;
    }

    public Boolean getIsChosen() {
        return isChosen;
    }

    public void setIsChosen(Boolean isChosen) {
        this.isChosen = isChosen;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Orders getOrderID() {
        return orderID;
    }

    public void setOrderID(Orders orderID) {
        this.orderID = orderID;
    }

    public Users getShipperID() {
        return shipperID;
    }

    public void setShipperID(Users shipperID) {
        this.shipperID = shipperID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bidID != null ? bidID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bid)) {
            return false;
        }
        Bid other = (Bid) object;
        if ((this.bidID == null && other.bidID != null) || (this.bidID != null && !this.bidID.equals(other.bidID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dvtd.pojo.Bid[ bidID=" + bidID + " ]";
    }
    
}
