package restclient;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercantil.operationsandexecution.infrastructure.RestConsumerErrorManager;
import com.mercantil.operationsandexecution.domain.exception.AppException;
import com.mercantil.operationsandexecution.domain.exception.ConstantException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RestConsumerErrorManagerTest {
    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private RestConsumerErrorManager restConsumerErrorManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldHandleException_whenBodyIsInvalidJson() throws JsonProcessingException {
        // Given
        ClientResponse clientResponse = mock(ClientResponse.class);
        String invalidJson = "{invalid json}";
        ConstantException constantException =
                ConstantException.ERROR_EXTERNAL_SERVICE_CONSUME;

        // When
        when(clientResponse.bodyToMono(String.class))
                .thenReturn(Mono.just(invalidJson));
        when(objectMapper.readValue(invalidJson.replaceAll("[\\n\\r\\t]", ""), Map.class))
                .thenThrow(new JsonProcessingException("Invalid JSON") {});

        // Then
        restConsumerErrorManager.buildException(clientResponse, "msgId", constantException)
                .as(StepVerifier::create)
                .expectErrorMatches(throwable -> throwable instanceof AppException &&
                        ((AppException) throwable).getOptionalInfo().equals(invalidJson))
                .verify();

        verify(objectMapper, never()).writeValueAsString(any());
    }

    @Test
    void shouldBuildExceptionSuccessfully_whenBodyIsValidJson() throws JsonProcessingException {
        // Given
        ClientResponse clientResponse = mock(ClientResponse.class);
        String validJson = "{\"key\":\"value\"}";
        String expectedSerializedMap = "{\"key\":\"value\"}";
        Map<String, String> parsedMap = Map.of("key", "value");

        // When
        when(clientResponse.bodyToMono(String.class))
                .thenReturn(Mono.just(validJson));
        when(objectMapper.readValue(validJson.replaceAll("[\\n\\r\\t]", ""), Map.class))
                .thenReturn(parsedMap);
        when(objectMapper.writeValueAsString(parsedMap))
                .thenReturn(expectedSerializedMap);

        ConstantException constantException =
                ConstantException.ERROR_EXTERNAL_SERVICE_CONSUME;

        // Then
        StepVerifier.create(restConsumerErrorManager.buildException(clientResponse, "msgId", constantException))
                .expectErrorMatches(throwable -> throwable instanceof AppException &&
                        ((AppException) throwable).getOptionalInfo().equals(expectedSerializedMap))
                .verify();

        verify(objectMapper).writeValueAsString(parsedMap);
    }
}
