package co.com.auth.model.auth;

public class Login {

    private String email;
    private String password;

    public Login() {}

    private Login(Builder builder) {
        this.email = builder.email;
        this.password = builder.password;
    }

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Builder
    public static class Builder {
        private String email;
        private String password;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Login build() {
            return new Login(this);
        }
    }
}

