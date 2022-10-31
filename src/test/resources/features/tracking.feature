Feature: shipment tracking

  Rule: A Proper error message should display to users
    @TrackingSanity @RegressionTest
    Scenario: track with an invalid format tracking number
      Given The user typed INVALID_FORMAT_TRACKING_ID to the tracking number input
      When Press Tab button then click the active button
      Then The "unable_to_retrieve_your_tracking" message displays on the error page

    @TrackingSanity @RegressionTest
    Scenario: track with no exists tracking number
      Given The user typed EMPTY_TRACKING_ID to the tracking number input
      When Click TRACK button
      Then The "no_track_number_alert" message displays on the home page

    @TrackingSanity @RegressionTest
    Scenario: track with an invalid tracking number
      Given The user typed INVALID_TRACKING_ID to the tracking number input
      When Press Tab button then click the active button
      Then The "unable_to_retrieve_your_tracking" message displays on the error page