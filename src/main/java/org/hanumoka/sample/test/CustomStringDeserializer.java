package org.hanumoka.sample.test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * 클래스 설명란
 *
 * @author : KYB
 * @since : 24. 10. 24.
 */
public class CustomStringDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        String originalValue = p.getValueAsString();
        // '/'를 '\/'로 다시 변환
        return originalValue.replace("/", "\\/");
    }
}