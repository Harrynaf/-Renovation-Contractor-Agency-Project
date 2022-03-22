/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.entity;

import com.mycompany.renovationcontractoragency.enums.PropertyType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Ioannis Psathas
 */
@Entity
@Table(name = "property")
public class Property implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "ePropertyCode", nullable = false, unique = true)
    private String eCode;
    @Column(name = "address")
    private String address;
    @Column(name = "yearOfConstruction")
    private LocalDate constructionYear;
    @Column(name = "propertyType")
    @Enumerated(value = EnumType.STRING)
    private PropertyType type;
    @ManyToOne
    private User owner;
    @OneToMany(mappedBy = "property", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Repair> repairs;

    public Property() {
    }

    /**
     * Constructor without id
     */
    public Property(String eCode, String address, LocalDate constructionYear, PropertyType type, User owner) {
        this.eCode = eCode;
        this.address = address;
        this.constructionYear = constructionYear;
        this.type = type;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String geteCode() {
        return eCode;
    }

    public void seteCode(String eCode) {
        this.eCode = eCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(LocalDate constructionYear) {
        this.constructionYear = constructionYear;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.eCode);
        hash = 43 * hash + Objects.hashCode(this.address);
        hash = 43 * hash + Objects.hashCode(this.constructionYear);
        hash = 43 * hash + Objects.hashCode(this.type);
        hash = 43 * hash + Objects.hashCode(this.owner);
        hash = 43 * hash + Objects.hashCode(this.repairs);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Property other = (Property) obj;
        if (!Objects.equals(this.eCode, other.eCode)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.constructionYear, other.constructionYear)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.owner, other.owner)) {
            return false;
        }
        return Objects.equals(this.repairs, other.repairs);
    }

    @Override
    public String toString() {
        return "Property{" + "id=" + id + ", eCode=" + eCode + ", address=" + address + ", constructionYear=" + constructionYear + ", type=" + type + ", owner=" + owner + ", repairs=" + repairs + '}';
    }
}