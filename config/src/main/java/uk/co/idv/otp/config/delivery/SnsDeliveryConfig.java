package uk.co.idv.otp.config.delivery;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import uk.co.idv.otp.adapter.delivery.SnsDeliverOtp;
import uk.co.idv.otp.usecases.send.DeliverOtp;

import java.time.Clock;

@Slf4j
@Builder
public class SnsDeliveryConfig implements DeliveryConfig {

    private final Clock clock;
    private final String endpointUri;
    private final String region;

    @Builder.Default
    private final AWSCredentialsProvider credentialsProvider = new DefaultAWSCredentialsProviderChain();

    @Override
    public DeliverOtp deliverOtp() {
        return SnsDeliverOtp.builder()
                .client(buildClient())
                .clock(clock)
                .build();
    }

    private AmazonSNS buildClient() {
        return build(getEndpointConfiguration());
    }

    private EndpointConfiguration getEndpointConfiguration() {
        return new EndpointConfiguration(endpointUri, region);
    }

    private AmazonSNS build(EndpointConfiguration endpointConfiguration) {
        log.info("connecting to sns service endpoint {}", endpointConfiguration.getServiceEndpoint());
        log.info("connecting to sns signing region {}", endpointConfiguration.getSigningRegion());
        return AmazonSNSClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withEndpointConfiguration(endpointConfiguration)
                .build();
    }

}