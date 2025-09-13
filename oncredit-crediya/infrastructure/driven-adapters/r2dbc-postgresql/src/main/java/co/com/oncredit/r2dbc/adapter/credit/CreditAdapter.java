package co.com.oncredit.r2dbc.adapter.credit;

import co.com.oncredit.model.credit.Credit;
import co.com.oncredit.model.credit.gateways.CreditGateway;
import co.com.oncredit.r2dbc.adapter.credit.data.CreditData;
import co.com.oncredit.r2dbc.adapter.credit.data.Mapper;
import co.com.oncredit.r2dbc.repository.credit.ReactiveCreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CreditAdapter implements CreditGateway {

    private final ReactiveCreditRepository repository;
    private final TransactionalOperator operator;

    @Override
    public Mono<Credit> save(Credit credit) {
        CreditData data = Mapper.toData(credit);
        return repository.save(data)
                .as(operator::transactional)
                .map(Mapper::toModel);
        //.onErrorMap(e -> new RuntimeException(e, TechnicalExceptionMessage.DATABASE_ERROR));
    }


}
