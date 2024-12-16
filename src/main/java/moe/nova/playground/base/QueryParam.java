package moe.nova.playground.base;

import moe.nova.bean.BaseParam;

public class QueryParam extends BaseParam {

    public QueryParam() {
        super();
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        var _ = new QueryParam();
    }
}
