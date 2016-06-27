package models;

/**
 * Created by Nidhi Mishra on 18/06/16.
 */
public class UrlShortnerError {
    private String reason;
    private String message;

    public UrlShortnerError(String reason, String message) {
        this.reason = reason;
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
