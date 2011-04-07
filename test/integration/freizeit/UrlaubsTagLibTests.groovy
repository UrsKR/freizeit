package freizeit

import grails.test.TagLibUnitTestCase

class UrlaubsTagLibTests extends TagLibUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testFormatsDateToGermanDefault() {
        def date = new Date(0)
        String output = new UrlaubsTagLib().formatDate(date: date)
        assert output == '01.01.1970'
    }

    void testFormatsNumbersWithoutFractionsIfTheyAre0() {
        String output = new UrlaubsTagLib().formatDayCount(days: 5)
        assert output == '5'
    }

    void testIncludesFractionsInNumbersIfRequired() {
        String output = new UrlaubsTagLib().formatDayCount(days: 5.7)
        assert output == '5.7'
    }

    void testRendersHalvesWithLigature() {
        String output = new UrlaubsTagLib().formatDayCount(days: 5.5)
        assert output == '5½'
    }
}
