package edu.ustb.sei.mde.nmc.web;

import java.util.Objects;

public final class Result {
    private final boolean res;
    private final String data;
    private final String msg;

    public Result(final boolean res, final String data, final String msg) {
        this.res = res;
        this.data = data;
        this.msg = msg;
    }

    public boolean isRes() {
        return this.res;
    }

    public String getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return res == result.res &&
                data.equals(result.data) &&
                msg.equals(result.msg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(res, data, msg);
    }

    public String toString() {
        boolean var10000 = this.isRes();
        return "Result(res=" + var10000 + ", data=" + this.getData() + ", msg=" + this.getMsg() + ")";
    }
}

