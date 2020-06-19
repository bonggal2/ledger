package com.prometheus.ledger.core.model;

import com.prometheus.ledger.core.util.JSONUtil;
import org.json.simple.JSONObject;

public class BaseModel {
    public JSONObject toJsonObject(){
        return JSONUtil.toJsonObject(this);
    }

    public String toJsonString(){
        return JSONUtil.toJsonString(this);
    }
}
