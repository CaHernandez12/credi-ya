package co.com.oncredit.usecase.credit;

import co.com.oncredit.model.credit.Credit;
import co.com.oncredit.model.credit.exception.BusinessException;
import co.com.oncredit.model.credit.gateways.AuthGateway;
import co.com.oncredit.model.credit.gateways.CreditGateway;
import co.com.oncredit.model.loantype.gateways.LoanTypeGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreditUseCaseTest {

    private CreditGateway creditGateway;
    private LoanTypeGateway loanTypeGateway;
    private AuthGateway authGateway;

    private CreditUseCase creditUseCase;

    @BeforeEach
    void setUp() {
        creditGateway = Mockito.mock(CreditGateway.class);
        loanTypeGateway = Mockito.mock(LoanTypeGateway.class);
        authGateway = Mockito.mock(AuthGateway.class);


        creditUseCase = new CreditUseCase(creditGateway, loanTypeGateway, authGateway);
    }
    @Test
    void shouldThrowBusinessExceptionWhenDocumentIsInvalid() {
        // arrange
        Credit credit = new Credit();
        credit.setDocumentNumber("12345");

        Mockito.when(authGateway.verifyDocument("12345")).thenReturn(Mono.just(false));

        // act
        Mono<Credit> result = creditUseCase.save(credit);

        // assert
        StepVerifier.create(creditUseCase.save(credit))
                .expectErrorSatisfies(throwable -> {
                    assertTrue(throwable instanceof BusinessException);
                    BusinessException ex = (BusinessException) throwable;
                    assertEquals("BE-003", ex.getBusinessExceptionMessage().getCode()); // 👈 Código
                    assertEquals("El número de documento no es válido", ex.getMessage()); // 👈 Mensaje
                })
                .verify();
    }

    @Test
    void shouldSaveCreditWhenDocumentIsValidAndLoanTypeExists() {
        // arrange
        Credit credit = new Credit();
        credit.setDocumentNumber("12345");
        credit.setLoanTypeId(1L);

        Mockito.when(authGateway.verifyDocument("12345")).thenReturn(Mono.just(true));
        Mockito.when(loanTypeGateway.findByLoanType(1L)).thenReturn(Mono.just(new co.com.oncredit.model.loantype.LoanType()));
        Mockito.when(creditGateway.save(credit)).thenReturn(Mono.just(credit));

        // act
        Mono<Credit> result = creditUseCase.save(credit);

        // assert
        StepVerifier.create(result)
                .expectNextMatches(savedCredit ->
                        savedCredit.getStateId() == 1L &&
                                savedCredit.getDocumentNumber().equals("12345")
                )
                .verifyComplete();

        // verify
        Mockito.verify(authGateway).verifyDocument("12345"); // se llamó al Auth
        Mockito.verify(loanTypeGateway).findByLoanType(1L); // se buscó el LoanType
        Mockito.verify(creditGateway).save(credit); // se guardó el crédito
    }



}
