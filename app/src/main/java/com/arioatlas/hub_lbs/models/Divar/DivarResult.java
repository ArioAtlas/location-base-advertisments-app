package com.arioatlas.hub_lbs.models.Divar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DivarResult implements Serializable {

    @SerializedName("last_post_date")
    @Expose
    private Long lastPostDate;

    @SerializedName("error")
    @Expose
    private int error;

    @SerializedName("seo_details")
    @Expose
    private DivarSeoDetails seoDetails;

    @SerializedName("paginator")
    @Expose
    private int paginator;

    @SerializedName("post_list")
    @Expose
    private List<DivarAdv> postList;

    @SerializedName("cat_count")
    @Expose
    private Object catCount;

    public Long getLastPostDate() {
        return lastPostDate;
    }

    public void setLastPostDate(Long lastPostDate) {
        this.lastPostDate = lastPostDate;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public DivarSeoDetails getSeoDetails() {
        return seoDetails;
    }

    public void setSeoDetails(DivarSeoDetails seoDetails) {
        this.seoDetails = seoDetails;
    }

    public int getPaginator() {
        return paginator;
    }

    public void setPaginator(int paginator) {
        this.paginator = paginator;
    }

    public List<DivarAdv> getPostList() {
        return postList;
    }

    public void setPostList(List<DivarAdv> postList) {
        this.postList = postList;
    }

    public Object getCatCount() {
        return catCount;
    }

    public void setCatCount(Object catCount) {
        this.catCount = catCount;
    }
}
