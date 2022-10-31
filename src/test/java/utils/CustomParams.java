package utils;

import enums.Countries;
import enums.Credentials;
import enums.TrackingNumbers;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;

import java.util.List;

public class CustomParams {
    @ParameterType("INVALID_TRACKING_ID|VALID_TRACKING_ID|NO_EXISTS_TRACKING_ID|EMPTY_TRACKING_ID")
    public TrackingNumbers trackingNumbers(String trackingNumbers) {
        return TrackingNumbers.valueOf(trackingNumbers);
    }

    @ParameterType("TURKEY|VALID_TRACKING_ID|UNITED_STATES")
    public Countries countries(String countries) {
        return Countries.valueOf(countries);
    }

    @ParameterType("VALID_USER_ID|VALID_PASSWORD|INVALID_USER_ID|INVALID_PASSWORD")
    public Credentials credentials(String credentials) {
        return Credentials.valueOf(credentials);
    }
}
