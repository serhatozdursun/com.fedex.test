Feature: Localization

    Rule : Page should has Localization support
        @LocalizationSanity @RegressionTest
        Scenario: Change location
            Given Choose Location Page
            When Select "English" language for TURKEY
            Then Current country should be TURKEY
            And There should be just language of TURKEY on language dropdown
