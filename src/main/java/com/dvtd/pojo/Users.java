/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserID", query = "SELECT u FROM Users u WHERE u.userID = :userID"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByRole", query = "SELECT u FROM Users u WHERE u.role = :role"),
    @NamedQuery(name = "Users.findByAvatar", query = "SELECT u FROM Users u WHERE u.avatar = :avatar"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone"),
    @NamedQuery(name = "Users.findByCreatedDate", query = "SELECT u FROM Users u WHERE u.createdDate = :createdDate")})
public class Users implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipperID")
    @JsonIgnore
    private Collection<Bid> bidCollection;

    @Column(name = "active")
    private Short active;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UserID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;
    @Basic(optional = false)
    @NotNull(message = "{error.field.required}")
    @Size(min = 1, max = 50)
    @Column(name = "Username", unique = true)
    private String username;
    @Basic(optional = false)
    @NotNull(message = "{error.field.required}")
    @Size(min = 1, max = 100)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @NotNull(message = "{error.field.required}")
    @Size(min = 1, max = 20)
    @Column(name = "Role")
    private String role;
    @Size(max = 100)
    @Column(name = "Avatar")
    private String avatar;
    @NotNull(message = "{error.field.required}")
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Email không hợp lệ")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Số điện thoại không hợp lệ")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull(message = "{error.field.required}")
    @Column(name = "Phone")
    private String phone;
    @Basic(optional = false)
    @Column(name = "created_date", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    @JsonIgnore
    private Set<Reviews> reviewsSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    @JsonIgnore
    private Set<Shippers> shippersSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    @JsonIgnore
    private Set<Orders> ordersSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    @JsonIgnore
    private Set<Notifications> notificationsSet;
    
    @Transient
    private MultipartFile file;

    public Users() {
    }

    public Users(Integer userID) {
        this.userID = userID;
    }

    public Users(Integer userID, String username, String password, String role, String email, String phone, Date createdDate) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.createdDate = createdDate;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @XmlTransient
    public Set<Reviews> getReviewsSet() {
        return reviewsSet;
    }

    public void setReviewsSet(Set<Reviews> reviewsSet) {
        this.reviewsSet = reviewsSet;
    }

    @XmlTransient
    public Set<Shippers> getShippersSet() {
        return shippersSet;
    }

    public void setShippersSet(Set<Shippers> shippersSet) {
        this.shippersSet = shippersSet;
    }

    @XmlTransient
    public Set<Orders> getOrdersSet() {
        return ordersSet;
    }

    public void setOrdersSet(Set<Orders> ordersSet) {
        this.ordersSet = ordersSet;
    }

    @XmlTransient
    public Set<Notifications> getNotificationsSet() {
        return notificationsSet;
    }

    public void setNotificationsSet(Set<Notifications> notificationsSet) {
        this.notificationsSet = notificationsSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dvtd.pojo.Users[ userID=" + userID + " ]";
    }

    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @XmlTransient
    public Collection<Bid> getBidCollection() {
        return bidCollection;
    }

    public void setBidCollection(Collection<Bid> bidCollection) {
        this.bidCollection = bidCollection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
