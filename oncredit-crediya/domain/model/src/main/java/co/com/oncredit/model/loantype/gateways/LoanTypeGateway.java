package co.com.oncredit.model.loantype.gateways;

import co.com.oncredit.model.loantype.LoanType;
import reactor.core.publisher.Mono;

public interface LoanTypeGateway {

    Mono<LoanType> findByLoanType(Long loanTypeId);

}
