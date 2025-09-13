package co.com.oncredit.r2dbc.adapter.credit.data;

import co.com.oncredit.model.credit.Credit;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Mapper {

    public CreditData toData(Credit credit){
        return CreditData.builder()
                .amount(credit.getAmount())
                .term(credit.getTerm())
                .email(credit.getEmail())
                .documentNumber(credit.getDocumentNumber())
                .stateId(credit.getStateId())
                .loanTypeId(credit.getLoanTypeId())
                .build();

    }

    public Credit toModel(CreditData creditData){

        return new Credit.Builder()
                .creditId(creditData.getCreditId())
                .amount(creditData.getAmount())
                .term(creditData.getTerm())
                .email(creditData.getEmail())
                .documentNumber(creditData.getDocumentNumber())
                .stateId(creditData.getStateId())
                .loanTypeId(creditData.getLoanTypeId())
                .build();
    }


}

