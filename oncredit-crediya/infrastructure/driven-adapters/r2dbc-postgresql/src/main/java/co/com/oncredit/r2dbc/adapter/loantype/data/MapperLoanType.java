package co.com.oncredit.r2dbc.adapter.loantype.data;

import co.com.oncredit.model.loantype.LoanType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MapperLoanType {

    public LoanTypeData toData(LoanType loanType) {
        return LoanTypeData.builder()
                .name(loanType.getName())
                .minAmount(loanType.getMinAmount())
                .maxAmount(loanType.getMaxAmount())
                .interestRate(loanType.getInterestRate())
                .automaticValidation(loanType.getAutomaticValidation())
                .build();
    }

    public LoanType toModel(LoanTypeData loanTypeData) {
        return new LoanType.Builder()
                .loanTypeId(loanTypeData.getLoanTypeId())
                .name(loanTypeData.getName())
                .minAmount(loanTypeData.getMinAmount())
                .maxAmount(loanTypeData.getMaxAmount())
                .interestRate(loanTypeData.getInterestRate())
                .automaticValidation(loanTypeData.getAutomaticValidation())
                .build();
    }
}
