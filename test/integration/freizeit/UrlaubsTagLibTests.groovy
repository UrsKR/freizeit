package freizeit

import grails.test.TagLibUnitTestCase

class UrlaubsTagLibTests extends TagLibUnitTestCase {
    def lib = new UrlaubsTagLib()

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testFormatsDateToGermanDefault() {
        def date = new Date(0)
        String output = lib.formatDate(date: date)
        assert output == '01.01.1970'
    }

    void testFormatsNumbersWithoutFractionsIfTheyAre0() {
        String output = lib.formatDayCount(days: 5)
        assert output == '5'
    }

    void testIncludesFractionsInNumbersIfRequired() {
        String output = lib.formatDayCount(days: 5.7)
        assert output == '5.7'
    }

    void testRendersHalvesWithLigature() {
        String output = lib.formatDayCount(days: 5.5)
        assert output == '5½'
    }

    void testRendersHalfWithoutLeadingZero() {
        String output = lib.formatDayCount(days: 0.5)
        assert output == '½'
    }
}
