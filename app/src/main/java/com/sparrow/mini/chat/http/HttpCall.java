package com.sparrow.mini.chat.http;

public class HttpCall implements IHttpCall {

    public HttpCall(Builder builder) {
        // todo 实现HttpCall逻辑
    }

    public static Builder get() {
        return new Builder();
    }

    public void execute() {

    }

    public static class Builder {
        String url;
        String params;
        IHttpCallback callback;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder params(String params) {
            this.params = params;
            return this;
        }

        public Builder callback(IHttpCallback callback) {
            this.callback = callback;
            return this;
        }

        public HttpCall build() {
            return new HttpCall(this);
        }
    }
}
