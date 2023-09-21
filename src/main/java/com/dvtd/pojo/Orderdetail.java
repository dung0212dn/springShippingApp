/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "orderdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderdetail.findAll", query = "SELECT o FROM Orderdetail o"),
    @NamedQuery(name = "Orderdetail.findByOrderDetailID", query = "SELECT o FROM Orderdetail o WHERE o.orderDetailID = :orderDetailID"),
    @NamedQuery(name = "Orderdetail.findByProductName", query = "SELECT o FROM Orderdetail o WHERE o.productName = :productName"),
    @NamedQuery(name = "Orderdetail.findByQuantity", query = "SELECT o FROM Orderdetail o WHERE o.quantity = :quantity")})
public class Orderdetail implements Serializable {

    /**
     * @return the fileImage
     */
    public MultipartFile getFileImage() {
        return fileImage;
    }

    /**
     * @param fileImage the fileImage to set
     */
    public void setFileImage(MultipartFile fileImage) {
        this.fileImage = fileImage;
    }

    @Size(max = 255)
    @Column(name = "ProductImg")
    private String productImg;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OrderDetailID")
    private Integer orderDetailID;
    @Size(max = 255)
    @Column(name = "ProductName")
    private String productName;
    @Column(name = "Quantity")
    private Integer quantity;
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID")
    @ManyToOne
    private Orders orderID;
    
    @Transient
    private MultipartFile fileImage;

    public Orderdetail() {
    }

    public Orderdetail(Integer orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public Integer getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(Integer orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Orders getOrderID() {
        return orderID;
    }

    public void setOrderID(Orders orderID) {
        this.orderID = orderID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderDetailID != null ? orderDetailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orderdetail)) {
            return false;
        }
        Orderdetail other = (Orderdetail) object;
        if ((this.orderDetailID == null && other.orderDetailID != null) || (this.orderDetailID != null && !this.orderDetailID.equals(other.orderDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dvtd.pojo.Orderdetail[ orderDetailID=" + orderDetailID + " ]";
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }
    
}
