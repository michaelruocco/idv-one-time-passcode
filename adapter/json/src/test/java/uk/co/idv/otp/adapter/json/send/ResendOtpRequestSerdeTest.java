package uk.co.idv.otp.adapter.json.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import uk.co.idv.common.adapter.json.ObjectMapperFactory;
import uk.co.idv.otp.adapter.json.OtpAppModule;
import uk.co.idv.otp.entities.send.ResendOtpRequest;
import uk.co.idv.otp.entities.send.ResendOtpRequestMother;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

class ResendOtpRequestSerdeTest {

    private static final ObjectMapper MAPPER = ObjectMapperFactory.build(new OtpAppModule());
    private static final ResendOtpRequest REQUEST = ResendOtpRequestMother.build();
    private static final String JSON = ResendOtpRequestJsonMother.build();

    @Test
    void shouldSerialize() throws JsonProcessingException {
        String json = MAPPER.writeValueAsString(REQUEST);

        assertThatJson(json).isEqualTo(JSON);
    }

    @Test
    void shouldDeserialize() throws JsonProcessingException {
        ResendOtpRequest request = MAPPER.readValue(JSON, ResendOtpRequest.class);

        assertThat(request).isEqualTo(REQUEST);
    }

}
