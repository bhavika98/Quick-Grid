package com.app.incroyable.quickgrid.model;

public class CameraFilters {

    int thumb;
    String title;
    FilterType filterType;

    public CameraFilters(int thumb, String title, FilterType filterType) {
        this.thumb = thumb;
        this.title = title;
        this.filterType = filterType;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public enum FilterType {

        IF1977Filter, IFAmaroFilter, IFBrannanFilter, IFEarlybirdFilter, IFHefeFilter,
        IFHudsonFilter, IFInkwellFilter, IFLomoFilter, IFLordKelvinFilter, IFNashvilleFilter,
        IFRiseFilter, IFSierraFilter, IFSutroFilter, IFToasterFilter, IFValenciaFilter,
        IFWaldenFilter, IFXproIIFilter,

        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ELEONE,
        TWELVE, THIRTEEN, FOURTEEN, FIFTEEN, SIXTEEN, SEVENTEEN, EIGHTEEN,
        NINETEEN, TWENTY, TWENTYONE, TWENTYTWO, TWENTYTHREE, TWENTYFOUR,
        TWENTYFIVE, TWENTYSIX, TWENTYSEVEN, TWENTYEIGHT, TWENTYNINE,
        THIRTY, THIRTYONE, THIRTYTWO
    }
}
