package moe.nova.bean;

import java.io.Serial;
import java.io.Serializable;

public class BaseParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 42L;

    private String traceId;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

}
