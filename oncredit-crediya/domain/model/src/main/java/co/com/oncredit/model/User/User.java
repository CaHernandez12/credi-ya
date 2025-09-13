package co.com.oncredit.model.User;

public class User {

    private String email;
    private String documentNumber;

    public User(String email, String documentNumber) {
        this.email = email;
        this.documentNumber = documentNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

}
