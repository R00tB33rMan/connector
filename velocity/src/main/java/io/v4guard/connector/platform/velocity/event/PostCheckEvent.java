package io.v4guard.connector.platform.velocity.event;

import com.velocitypowered.api.event.ResultedEvent;
import io.v4guard.connector.common.check.BlockReason;

import java.util.Objects;

public class PostCheckEvent implements ResultedEvent<ResultedEvent.GenericResult> {

    private final BlockReason reason;
    private final String username;
    private GenericResult result = GenericResult.allowed();

    public PostCheckEvent(String username, BlockReason reason) {
        this.username = username;
        this.reason = reason;
    }

    public BlockReason getBlockReason() {
        return reason;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public GenericResult getResult() {
        return result;
    }

    @Override
    public void setResult(GenericResult genericResult) {
        this.result = Objects.requireNonNull(genericResult);
    }
}