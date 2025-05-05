package com.mercantil.operationsandexecution.infrastructure.adapter.restclientgetsome;



import com.mercantil.operationsandexecution.infrastructure.adapter.restclient.IGenericRestClient;
import com.mercantil.operationsandexecution.infrastructure.adapter.keyvaultsecrets.model.ConsumerSecrets;
import com.mercantil.operationsandexecution.infrastructure.adapter.restclient.model.Constants;
import com.mercantil.operationsandexecution.infrastructure.adapter.restclient.model.ConsumeParams;
import com.mercantil.operationsandexecution.domain.models.SomeByRest;
import com.mercantil.operationsandexecution.domain.ports.out.GetSomeByRest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Log4j2
@AllArgsConstructor
public class RestConsumer implements GetSomeByRest {
    private final ConsumeParams consumeParams;
    private final ConsumerSecrets consumerSecrets;
    private final IGenericRestClient genericRestClient;

    @Override
    public Mono<SomeByRest> getSomeByRest() {

        return Mono.deferContextual(contextView -> genericRestClient.exchange(consumeParams.getCompleteUri(),
                        HttpMethod.GET.name(), null, SomeByRest.class, createHeaders())
                .onErrorResume(Throwable.class, Mono::error)
        );
    }

    private HttpHeaders createHeaders() {
        var headers = new HttpHeaders();
        headers.add(Constants.HeaderKeys.MESSAGE_ID, "MessageId de context");
        headers.add("client-id", consumerSecrets.getClientId());
        headers.add("client-secrets", consumerSecrets.getClientSecret());
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
