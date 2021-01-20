package com.prokarma.poc.consumer.entitties;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "CUSTOMER_DETAILS")
public class CustomerDetailsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String customerNumber;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    @NotNull
    private String country;
    @NotNull
    private String countryCode;
    @NotNull
    private String mobileNumber;
    @NotNull
    private String email;
    @NotNull
    @Enumerated(EnumType.STRING)
    private CustomerStatusEnum customerStatus;
    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Address address;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private ObjectNode payload;

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerStatusEnum getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(CustomerStatusEnum customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ObjectNode getPayload() {
        return payload;
    }

    public void setPayload(ObjectNode payload) {
        this.payload = payload;
    }
}
