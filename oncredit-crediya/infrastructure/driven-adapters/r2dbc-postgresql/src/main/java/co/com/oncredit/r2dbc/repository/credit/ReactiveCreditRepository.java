package co.com.oncredit.r2dbc.repository.credit;

import co.com.oncredit.r2dbc.adapter.credit.data.CreditData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ReactiveCreditRepository  extends ReactiveCrudRepository<CreditData, Long> {
}
