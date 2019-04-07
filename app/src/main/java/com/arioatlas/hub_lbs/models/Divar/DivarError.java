package com.arioatlas.hub_lbs.models.Divar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

class DivarError implements Serializable {
    @SerializedName("code")
    @Expose
    private int code;
}
