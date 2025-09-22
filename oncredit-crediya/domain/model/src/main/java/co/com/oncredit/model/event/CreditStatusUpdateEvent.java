package co.com.oncredit.model.event;

public class CreditStatusUpdate {

    private final Long creditId;
    private final String newStatus;
    private final String email;

    public CreditStatusUpdate(Long creditId, String newStatus, String email) {
        this.creditId = creditId;
        this.newStatus = newStatus;
        this.email = email;
    }

    public Long getCreditId() {
        return creditId;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public String getEmail() {
        return email;
    }
}
