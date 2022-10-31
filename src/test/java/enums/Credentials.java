package enums;

import utils.Configuration;

public enum Credentials {
    VALID_USER_ID(Configuration.instance().getStringValueOfProp("user.id")),
    VALID_PASSWORD(Configuration.instance().getStringValueOfProp("password")),
    INVALID_USER_ID("test123123asd@asdasd.com"),
    INVALID_PASSWORD("INVALID_PASSWORD"),
    USER_TEXT("MSO");

    private String value;

    Credentials(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
