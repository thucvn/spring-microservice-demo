package com.uu.microservice.coreproxy.errordecoder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uu.microservice.core.config.Error;
import com.uu.microservice.core.exception.ResponseCodeException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class CustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        String message = null;
        InputStream reader = null;

        try {
            reader = response.body().asInputStream();
            ObjectMapper mapper = new ObjectMapper();
            ExceptionMessage exceptionMessage = mapper.readValue(reader,
                    ExceptionMessage.class);
            var code = exceptionMessage.code;
            var er = Error.valueOfNull(code);
            if (er != null) {
                return new ResponseCodeException(response.status(), er);
            }
            return new Exception(exceptionMessage.message);
        } catch (Exception e) {
            e.printStackTrace();
            return errorDecoder.decode(s, response);
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        return errorDecoder.decode(s, response);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ExceptionMessage {

        private String timestamp;
        private int status;
        private String error;
        private String code;
        private String message;
        private String path;

    }
}
