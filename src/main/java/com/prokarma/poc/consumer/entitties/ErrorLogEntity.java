package com.prokarma.poc.consumer.entitties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ERROR_LOG")
public class ErrorLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "ERROR_TYPE")
    @NotNull
    private String errorType;
    @NotNull
    @Column(name = "ERROR_DESCRIPTION")
    @Size(max = 500)
    private String errorDescription;
    @NotNull
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private ObjectNode payload;

    public ErrorLogEntity(String errorType, String errorDescription, ObjectNode payload) {
        this.errorType = errorType;
        this.errorDescription = errorDescription;
        this.payload = payload;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public JsonNode getPayload() {
        return payload;
    }

    public void setPayload(ObjectNode payload) {
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorLogEntity that = (ErrorLogEntity) o;
        return id.equals(that.id) &&
                errorType.equals(that.errorType) &&
                errorDescription.equals(that.errorDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, errorType, errorDescription);
    }
}

