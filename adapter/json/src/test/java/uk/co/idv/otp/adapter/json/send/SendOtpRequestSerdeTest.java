package uk.co.idv.otp.adapter.json.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import uk.co.idv.common.adapter.json.ObjectMapperFactory;
import uk.co.idv.otp.adapter.json.OtpAppModule;
import uk.co.idv.otp.entities.send.SendOtpRequest;
import uk.co.idv.otp.entities.send.SendOtpRequestMother;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

class SendOtpRequestSerdeTest {

    private static final ObjectMapper MAPPER = ObjectMapperFactory.build(new OtpAppModule());
    private static final SendOtpRequest REQUEST = SendOtpRequestMother.build();
    private static final String JSON = SendOtpRequestJsonMother.build();

    @Test
    void shouldSerialize() throws JsonProcessingException {
        String json = MAPPER.writeValueAsString(REQUEST);

        assertThatJson(json).isEqualTo(JSON);
    }

    @Test
    void shouldDeserialize() throws JsonProcessingException {
        SendOtpRequest request = MAPPER.readValue(JSON, SendOtpRequest.class);

        assertThat(request).isEqualTo(REQUEST);
    }

}
