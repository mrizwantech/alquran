
package com.rizwantech.alquran.alqurandata;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("surahs")
    @Expose
    private List<Surah> surahs = null;
    @SerializedName("edition")
    @Expose
    private Edition edition;

    public List<Surah> getSurahs() {
        return surahs;
    }

    public void setSurahs(List<Surah> surahs) {
        this.surahs = surahs;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

}
