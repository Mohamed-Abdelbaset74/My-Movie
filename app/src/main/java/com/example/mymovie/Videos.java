
package com.example.mymovie;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Videos {

    @SerializedName("results")
    @Expose
    private List<ResultDetail> resultDetails = null;

    public List<ResultDetail> getResultDetails() {
        return resultDetails;
    }

    public void setResultDetails(List<ResultDetail> resultDetails) {
        this.resultDetails = resultDetails;
    }

}
