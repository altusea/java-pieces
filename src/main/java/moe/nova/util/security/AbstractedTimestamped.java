package moe.nova.util.security;

public abstract class AbstractedTimestamped implements Timestamped {

    protected String callerMark = "";

    protected String callTimestamp;

    public String getCallerMark() {
        return callerMark;
    }

    public void setCallerMark(String callerMark) {
        this.callerMark = callerMark;
    }

    public String getCallTimestamp() {
        return callTimestamp;
    }

    public void setCallTimestamp(String callTimestamp) {
        this.callTimestamp = callTimestamp;
    }
}
