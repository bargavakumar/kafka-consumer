package com.prokarma.poc.consumer.entitties;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "CUSTOMER_DETAILS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDetailsEntity extends BaseEntity implements Serializable {

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
    private Date birthDate;
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
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private JsonNode payload;

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
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

    public JsonNode getPayload() {
        return payload;
    }

    public void setPayload(JsonNode payload) {
        this.payload = payload;
    }
}
