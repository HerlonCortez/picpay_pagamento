
package com.herlon.picpay.domain.service.impl;

import com.herlon.picpay.domain.entity.Transaction;
import com.herlon.picpay.domain.exception.UnauthorizedTransactionException;
import com.herlon.picpay.domain.service.AuthorizerService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
public class AuthorizerServiceImpl implements AuthorizerService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(AuthorizerServiceImpl.class);

    private final RestClient restClient;

    public AuthorizerServiceImpl(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://util.devi.tools/api/v2/authorize")
                .build();
    }

    @Override
    @CircuitBreaker(
            name = "authorize",
            fallbackMethod = "fallback"
    )
    public void authorize(Transaction transaction) {

        LOGGER.info("authorizing transaction {}...", transaction);

        var response = restClient.get()
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        (request, result) -> {
                            throw new UnauthorizedTransactionException(
                                    "Transaction not authorized!"
                            );
                        }
                )
                .toEntity(Authorization.class);

        if (response.getBody() == null ||
                !response.getBody().isAuthorized()) {

            throw new UnauthorizedTransactionException(
                    "Unauthorized!"
            );
        }
    }

    public void fallback(Transaction transaction, Throwable ex) {
        log.error("Serviço externo de autorização indisponível", ex);

        throw new RuntimeException(
                "Serviço de autorização temporariamente indisponível."
        );
    }
}

record Authorization(String message) {

    public boolean isAuthorized() {
        return "Autorizado".equals(message);
    }
}