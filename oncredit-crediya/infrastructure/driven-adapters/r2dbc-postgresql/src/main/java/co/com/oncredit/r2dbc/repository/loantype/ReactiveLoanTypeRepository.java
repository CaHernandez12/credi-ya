package co.com.oncredit.r2dbc.repository.loantype;

import co.com.oncredit.r2dbc.adapter.loantype.data.LoanTypeData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ReactiveLoanTypeRepository extends ReactiveCrudRepository<LoanTypeData, Long> {
}
