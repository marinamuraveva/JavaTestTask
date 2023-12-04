package api;

public class UserData {
    private final int id = 1;
    private String userName;
    private final String firstName = "test";
    private final String lastName = "test";
    private final String email = "test@test.com";
    private final String password = "qwerty" ;
    private final String phone = "89990000000";
    private final int userStatus = 0;

    private UserData() {
    }

    public String getUsername() {
        return userName;
    }

    public static Builder newBuilder() {
        return new UserData().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder setUserName(String userName) {
            UserData.this.userName = userName;
            return this;
        }

        public UserData build() {
            UserData userData = new UserData();
            userData.userName = UserData.this.userName;
            return userData;
        }
    }
}