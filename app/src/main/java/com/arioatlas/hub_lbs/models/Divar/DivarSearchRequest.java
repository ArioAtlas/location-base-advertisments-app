package com.arioatlas.hub_lbs.models.Divar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DivarSearchRequest {

    @SerializedName("jsonrpc")
    @Expose
    private String jsonrpc;

    @SerializedName("id")
    @Expose
    private  int id;

    @SerializedName("method")
    @Expose
    private String method;

    @SerializedName("params")
    @Expose
    private ArrayList<Object> params = new ArrayList<>();

    private ArrayList<Object> metas = new ArrayList<>();

    public DivarSearchRequest() {
        this.jsonrpc = "2.0";
        this.id = 1;
        this.method = "getPostList";
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public ArrayList<Object> getParams() {
        return params;
    }

    public void setParams(ArrayList<Object> params) {
        this.params = params;
    }

    public void addSearchMeta(List<Object> meta){
        metas.add(meta);
    }

    public void setSearchValidity() {
        setSearchValidity(0);
    }

    public void setSearchValidity(int lastPostTime){
        params.add(metas);
        params.add(lastPostTime);
    }
}
