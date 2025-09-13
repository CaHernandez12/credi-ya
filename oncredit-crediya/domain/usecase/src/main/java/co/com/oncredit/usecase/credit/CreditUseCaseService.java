package co.com.oncredit.usecase.credit;

import co.com.oncredit.model.credit.Credit;
import reactor.core.publisher.Mono;

public interface CreditUseCaseService {

    Mono<Credit> save(Credit credit, Long userId);
}
