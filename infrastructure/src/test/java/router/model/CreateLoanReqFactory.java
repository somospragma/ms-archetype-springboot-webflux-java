package router.model;


import com.mercantil.operationsandexecution.infrastructure.rest.model.request.CreateLoanBodyReq;

import java.math.BigDecimal;

public class CreateLoanReqFactory {
    public static CreateLoanBodyReq build(){
        return new CreateLoanBodyReq("Roberto Carlos", "HIPOTECARIO",
                new BigDecimal(1000), 1.4);
    }

    public static String buildJsonResponse(String date) {
        return "{"
                + "\"statusCode\":\"201 CREATED\","
                + "\"status\":\"SUCCESS\","
                + "\"message\":\"Creación exitosa\","
                + "\"timestamp\":\"" + date + "\","
                + "\"transactionId\":\"" + EntryPointConstantTests.MESSAGE_ID_VALUE + "\""
                + "}";
    }
}
