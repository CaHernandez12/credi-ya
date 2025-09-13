package co.com.oncredit.model.credit.gateways;

import co.com.oncredit.model.credit.Credit;
import reactor.core.publisher.Mono;

public interface CreditGateway {

    Mono<Credit> save(Credit credit);

}
