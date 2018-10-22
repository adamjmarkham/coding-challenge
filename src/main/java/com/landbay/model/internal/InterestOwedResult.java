package com.landbay.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InterestOwedResult {

    private long total;

    public InterestOwedResult(@JsonProperty("total") long total) {
        this.total = total;
    }

    public long getTotal() {
        return total;
    }
}
