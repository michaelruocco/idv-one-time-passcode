package uk.co.idv.otp.adapter.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import uk.co.idv.activity.adapter.json.ActivityModule;
import uk.co.idv.method.adapter.json.error.MethodErrorModule;
import uk.co.idv.method.adapter.json.method.MethodMappings;
import uk.co.idv.method.adapter.json.otp.OtpMapping;
import uk.co.idv.method.adapter.json.otp.OtpModule;
import uk.co.idv.method.adapter.json.verification.VerificationModule;
import uk.co.idv.otp.adapter.json.passcode.PasscodeDeserializer;
import uk.co.idv.otp.adapter.json.send.ResendOtpRequestDeserializer;
import uk.co.idv.otp.adapter.json.send.SendOtpRequestDeserializer;
import uk.co.idv.otp.adapter.json.send.message.MessageDeserializer;
import uk.co.idv.otp.adapter.json.verify.VerifyOtpRequestDeserializer;
import uk.co.idv.otp.entities.OtpVerification;
import uk.co.idv.otp.entities.delivery.Deliveries;
import uk.co.idv.otp.entities.delivery.Delivery;
import uk.co.idv.otp.entities.passcode.Passcode;
import uk.co.idv.otp.entities.send.ResendOtpRequest;
import uk.co.idv.otp.entities.send.SendOtpRequest;
import uk.co.idv.otp.entities.send.message.Message;
import uk.co.idv.otp.adapter.json.delivery.DeliveryDeserializer;
import uk.co.idv.otp.adapter.json.delivery.DeliveriesDeserializer;
import uk.co.idv.otp.adapter.json.delivery.DeliveryMixin;
import uk.co.idv.otp.entities.verify.VerifyOtpRequest;

import java.util.Arrays;

public class OtpAppModule extends SimpleModule {

    public OtpAppModule() {
        super("otp-app-module", Version.unknownVersion());

        setUpVerification();
        setUpDeliveries();
        setUpSendRequests();
        setUpVerifyRequests();
    }

    @Override
    public Iterable<? extends Module> getDependencies() {
        return Arrays.asList(
                new ActivityModule(),
                new OtpModule(),
                new MethodErrorModule(),
                new VerificationModule(new MethodMappings(new OtpMapping()))
        );
    }

    private void setUpVerification() {
        setMixInAnnotation(OtpVerification.class, OtpVerificationMixin.class);

        addDeserializer(OtpVerification.class, new OtpVerificationDeserializer());
    }

    private void setUpDeliveries() {
        setMixInAnnotation(Delivery.class, DeliveryMixin.class);

        addDeserializer(Deliveries.class, new DeliveriesDeserializer());
        addDeserializer(Delivery.class, new DeliveryDeserializer());
        addDeserializer(Message.class, new MessageDeserializer());
        addDeserializer(Passcode.class, new PasscodeDeserializer());
    }

    private void setUpSendRequests() {
        addDeserializer(SendOtpRequest.class, new SendOtpRequestDeserializer());
        addDeserializer(ResendOtpRequest.class, new ResendOtpRequestDeserializer());
    }

    private void setUpVerifyRequests() {
        addDeserializer(VerifyOtpRequest.class, new VerifyOtpRequestDeserializer());
    }

}
