package com.backend.store.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "addresses")
public class Address {
    @Id
    @MongoId
    @Field(name = "_id", targetType = FieldType.STRING)
    private String id;

    @Field(name = "province", targetType = FieldType.STRING)
    private String province;

    @Field(name = "district", targetType = FieldType.STRING)
    private String district;

    @Field(name = "ward", targetType = FieldType.STRING)
    private String ward;

    @Field(name = "specificAddress", targetType = FieldType.STRING)
    private String specificAddress;

    @Field(name = "phone", targetType = FieldType.STRING)
    private String phone;

    @Field(name = "receiver", targetType = FieldType.STRING)
    private String receiver;

    @Field(name = "default", targetType = FieldType.BOOLEAN)
    private Boolean isDefault;

    @Field(name = "deleted", targetType = FieldType.BOOLEAN)
    private Boolean deleted;

    // Constructors, getters, and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getSpecificAddress() {
        return specificAddress;
    }

    public void setSpecificAddress(String specificAddress) {
        this.specificAddress = specificAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
