package com.prometheus.ledger.core.model;

public interface Processor <E extends ProcessContext<?,?>> {
    boolean isSkipped(E context);
    void check(E context);
    void doProcess(E context);
}
