package com.arioatlas.hub_lbs.models.Divar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DivarSearchResponse {

    @SerializedName("jsonrpc")
    @Expose
    private String jsonrpc;

    @SerializedName("error")
    @Expose
    private DivarError error;

    @SerializedName("result")
    @Expose
    private DivarResult result;

    @SerializedName("id")
    @Expose
    private int id;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public DivarError getError() {
        return error;
    }

    public void setError(DivarError error) {
        this.error = error;
    }

    public DivarResult getResult() {
        return result;
    }

    public void setResult(DivarResult result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
