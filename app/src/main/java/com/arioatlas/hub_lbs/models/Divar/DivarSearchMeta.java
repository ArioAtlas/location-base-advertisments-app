package com.arioatlas.hub_lbs.models.Divar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DivarSearchMeta implements Serializable {
    private String tag;
    private int type;
    private int[] targets;

    public DivarSearchMeta(String tag, int type, int[] targets) {
        this.tag = tag;
        this.type = type;
        this.targets = targets;
    }

    public List<Object> prepare(){
        List<Object> data = new ArrayList<>();
        data.add(tag);
        data.add(type);
        data.add(targets);

        return data;
    }
}
