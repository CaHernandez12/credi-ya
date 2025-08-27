package co.com.auth.model.user;

import java.math.BigDecimal;
import java.time.LocalDate;

public class User {

    private Long userId;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private String phone;
    private String documentNumber;
    private String documentType;
    private String email;
    private BigDecimal baseSalary;

    User(Builder builder) {
        this.userId = builder.userId;
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.birthDate = builder.birthDate;
        this.address = builder.address;
        this.phone = builder.phone;
        this.documentNumber = builder.documentNumber;
        this.documentType = builder.documentType;
        this.email = builder.email;
        this.baseSalary = builder.baseSalary;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public static class Builder {

        private Long userId;
        private String name;
        private String lastName;
        private LocalDate birthDate;
        private String address;
        private String phone;
        private String documentNumber;
        private String documentType;
        private String email;
        private BigDecimal baseSalary;

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder documentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
            return this;
        }

        public Builder documentType(String documentType) {
            this.documentType = documentType;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder baseSalary(BigDecimal baseSalary) {
            this.baseSalary = baseSalary;
            return this;
        }

        public User build() {
           return new User(this);
        }

    }
}

