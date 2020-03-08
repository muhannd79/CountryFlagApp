package org.fooshtech.countryflagapp.model;

import com.google.gson.annotations.SerializedName;

public class CountryModel {

    @SerializedName("name")
    String countryname;

    @SerializedName("capital")
    String capital;

    @SerializedName("flagPNG")
    String flag;


    public CountryModel(String countryname, String capital, String flag) {
        this.countryname = countryname;
        this.capital = capital;
        this.flag = flag;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
