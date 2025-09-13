package co.com.oncredit.r2dbc.adapter.loantype;

import co.com.oncredit.model.loantype.gateways.LoanTypeGateway;
import co.com.oncredit.model.loantype.LoanType;
import co.com.oncredit.r2dbc.adapter.loantype.data.MapperLoanType;
import co.com.oncredit.r2dbc.repository.loantype.ReactiveLoanTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class LoanTypeAdapter implements LoanTypeGateway {

    private final ReactiveLoanTypeRepository repository;


    @Override
    public Mono<LoanType> findByLoanType(Long loanTypeId) {
        return repository.findById(loanTypeId)
                .map(MapperLoanType::toModel);
    }
}
