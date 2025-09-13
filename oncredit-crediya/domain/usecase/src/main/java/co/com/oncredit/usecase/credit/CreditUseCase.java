package co.com.oncredit.usecase.credit;

import co.com.oncredit.model.credit.Credit;
import co.com.oncredit.model.credit.exception.BusinessException;
import co.com.oncredit.model.credit.exception.BusinessExceptionMessage;
import co.com.oncredit.model.credit.gateways.AuthGateway;
import co.com.oncredit.model.credit.gateways.CreditGateway;
import co.com.oncredit.model.loantype.gateways.LoanTypeGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreditUseCase implements CreditUseCaseService {

    private final CreditGateway creditGateway;
    private final LoanTypeGateway loanTypeGateway;
    private final AuthGateway authGateway;

    @Override
    public Mono<Credit> save(Credit credit, Long userId) {
        return loanTypeGateway.findByLoanType(credit.getLoanTypeId())
                .switchIfEmpty(Mono.error(new BusinessException(BusinessExceptionMessage.INVALID_LOAN_TYPE)))
                .flatMap(validLoan ->
                        authGateway.findById(userId)
                                .flatMap(userInfo -> {
                                    if (!userInfo.getEmail().equals(credit.getEmail()) ||
                                            !userInfo.getDocumentNumber().equals(credit.getDocumentNumber())) {
                                        return Mono.error(new BusinessException(BusinessExceptionMessage.USER_NOT_VALID));
                                    }
                                    credit.setStateId(1L);
                                    return creditGateway.save(credit);
                                })
                );
    }
}

