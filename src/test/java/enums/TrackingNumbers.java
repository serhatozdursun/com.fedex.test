package enums;

public enum TrackingNumbers {
    INVALID_FORMAT_TRACKING_ID("123"),
    VALID_TRACKING_ID("9111100000000000000001"),//todo add an valid track number to reach detail track page
    EMPTY_TRACKING_ID(""),
    INVALID_TRACKING_ID("1Z3RA3492201589567");
    private final String value;

    TrackingNumbers(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
