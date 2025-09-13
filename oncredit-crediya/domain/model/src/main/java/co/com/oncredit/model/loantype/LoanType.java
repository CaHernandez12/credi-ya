package co.com.oncredit.model.loantype;

import java.math.BigDecimal;

public class LoanType {

    private Long loanTypeId;
    private String name;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private BigDecimal interestRate;
    private Boolean automaticValidation;

    public LoanType() {
    }

    private LoanType(Builder builder) {
        this.loanTypeId = builder.loanTypeId;
        this.name = builder.name;
        this.minAmount = builder.minAmount;
        this.maxAmount = builder.maxAmount;
        this.interestRate = builder.interestRate;
        this.automaticValidation = builder.automaticValidation;
    }

    public Long getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(Long loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Boolean getAutomaticValidation() {
        return automaticValidation;
    }

    public void setAutomaticValidation(Boolean automaticValidation) {
        this.automaticValidation = automaticValidation;
    }

    public static class Builder {
        private Long loanTypeId;
        private String name;
        private BigDecimal minAmount;
        private BigDecimal maxAmount;
        private BigDecimal interestRate;
        private Boolean automaticValidation;

        public Builder loanTypeId(Long loanTypeId) {
            this.loanTypeId = loanTypeId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder minAmount(BigDecimal minAmount) {
            this.minAmount = minAmount;
            return this;
        }

        public Builder maxAmount(BigDecimal maxAmount) {
            this.maxAmount = maxAmount;
            return this;
        }

        public Builder interestRate(BigDecimal interestRate) {
            this.interestRate = interestRate;
            return this;
        }

        public Builder automaticValidation(Boolean automaticValidation) {
            this.automaticValidation = automaticValidation;
            return this;
        }

        public LoanType build() {
            return new LoanType(this);
        }
    }

}
