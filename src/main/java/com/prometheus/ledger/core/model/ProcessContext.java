package com.prometheus.ledger.core.model;

import com.prometheus.ledger.core.model.request.BaseRequest;
import com.prometheus.ledger.core.model.result.BaseResult;

import java.util.HashMap;
import java.util.Map;

public class ProcessContext<R extends BaseRequest, T extends BaseResult>{
    protected R request;
    protected T result;
    private Map<String, Object> itemMap;

    public ProcessContext(R request, T result) {
        this.request = request;
        this.result = result;
        this.itemMap = new HashMap<>();
    }

    public R getRequest() {
        return request;
    }

    public void setRequest(R request) {
        this.request = request;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Map<String, Object> getItemMap() {
        return itemMap;
    }

    public void setItemMap(Map<String, Object> itemMap) {
        this.itemMap = itemMap;
    }

    public void addItemMap(String key, Object value){
        this.itemMap.put(key, value);
    }
}
