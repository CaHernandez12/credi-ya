package co.com.oncredit.model.page;

import java.math.BigDecimal;

public class CreditSummary {

    private BigDecimal amount;
    private Integer term;
    private String email;
    private String loanType;
    private BigDecimal interestRate;
    private String state;

    public CreditSummary() {}

    public CreditSummary(BigDecimal amount, Integer term, String email, String loanType, BigDecimal interestRate, String state) {
        this.amount = amount;
        this.term = term;
        this.email = email;
        this.loanType = loanType;
        this.interestRate = interestRate;
        this.state = state;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoanTypeId() {
        return loanType;
    }

    public void setLoanTypeId(String loanTypeId) {
        this.loanType = loanTypeId;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
