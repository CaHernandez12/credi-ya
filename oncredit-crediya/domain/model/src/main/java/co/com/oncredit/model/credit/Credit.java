package co.com.oncredit.model.credit;

import java.math.BigDecimal;

public class Credit {

    private Long creditId;
    private BigDecimal amount;
    private Integer term;
    private String email;
    private String documentNumber;
    private Long stateId;
    private Long loanTypeId;

    public Credit() {}

    private Credit(Builder builder) {
        this.creditId = builder.creditId;
        this.amount = builder.amount;
        this.term = builder.term;
        this.email = builder.email;
        this.documentNumber = builder.documentNumber;
        this.stateId = builder.stateId;
        this.loanTypeId = builder.loanTypeId;
    }

    public Long getCreditId() {
        return creditId;
    }

    public void setCreditId(Long creditId) {
        this.creditId = creditId;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(Long loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public static class Builder {
        private Long creditId;
        private BigDecimal amount;
        private Integer term;
        private String email;
        private String documentNumber;
        private Long stateId;
        private Long loanTypeId;

        public Builder creditId(Long creditId) {
            this.creditId = creditId;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder term(Integer term) {
            this.term = term;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder documentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
            return this;
        }

        public Builder stateId(Long stateId) {
            this.stateId = stateId;
            return this;
        }

        public Builder loanTypeId(Long loanTypeId) {
            this.loanTypeId = loanTypeId;
            return this;
        }

        public Credit build() {
            return new Credit(this);
        }
    }
}
