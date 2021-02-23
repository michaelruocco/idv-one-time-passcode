package uk.co.idv.otp.adapter.verification.loader;

import uk.co.idv.context.adapter.verification.client.exception.ApiErrorClientException;
import uk.co.idv.method.adapter.json.error.contextnotfound.ContextNotFoundError;
import uk.co.idv.method.entities.verification.Verification;
import uk.co.idv.otp.adapter.verification.Scenario;

import java.util.UUID;

public class ContextNotFoundScenario implements Scenario {

    public static final String ID = "9ed739ec-a252-4a3f-840c-4e2bdccf56e6";

    @Override
    public boolean shouldExecute(UUID contextId) {
        return ID.equals(contextId.toString());
    }

    @Override
    public Verification apply(UUID contextId) {
        throw new ApiErrorClientException(new ContextNotFoundError(contextId.toString()));
    }

}