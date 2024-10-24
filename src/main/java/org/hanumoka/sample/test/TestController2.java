package org.hanumoka.sample.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 클래스 설명란
 *
 * @author : KYB
 * @since : 24. 10. 24.
 */
@RestController
@RequestMapping("/test2")
public class TestController2 {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping
    public String test(@RequestBody String rawJson) throws JsonProcessingException {

        System.out.println("rawJson: " + rawJson);

        // 1. String으로 받은 JSON을 DTO로 변환
        TestDTO1 dto = objectMapper.readValue(rawJson, TestDTO1.class);

        // 변환된 데이터 확인
        System.out.println("Organization: " + dto.getOrganizationCode());
        System.out.println("CI: " + dto.getCi());
        System.out.println("ProviderKey: " + dto.getProviderKey());

        return "test";
    }

    @ToString
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TestDTO1{
        private String organizationCode;

        @JsonDeserialize(using = CustomStringDeserializer.class)
        private String ci;

        private String providerKey;

    }


}
