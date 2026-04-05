package creational.builder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class HTTPRequest {
    private final String url;
    private final String method;
    private final Map<String, String>headers;
    private final Map<String, String>queryParams;
    private final String body;
    private final int timeOut;

    private HTTPRequest(final Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers =  Collections.unmodifiableMap(new HashMap<>(builder.headers));
        this.queryParams =  Collections.unmodifiableMap(new HashMap<>(builder.queryParams));
        this.body = builder.body;
        this.timeOut = builder.timeOut; 
    }

    public static class Builder {
        private  String url;
        private  String method;
        private  Map<String, String>headers ;
        private  Map<String, String>queryParams;
        private  String body;
        private  int timeOut;

        Builder(final String url, final String method){
            this.url = url;
            this.method = method;
            headers = new HashMap<>();
            queryParams = new HashMap<>();
        }

        public Builder method(final String method){
            this.method = method;
            return this;
        }

        public Builder addHeaders(Map<String, String>headers) {
            for(String key: headers.keySet()){
                this.headers.put(key, headers.get(key));
            }
            return this;
        }
        public Builder addQueryParams(Map<String, String>queryParams) {
            for(String key: queryParams.keySet()){
                this.queryParams.put(key, queryParams.get(key));
            }
            return this;
        }

        public Builder body (final String body){
            this.body = body;
            return this;
        }

        public Builder timeOut(final int timeOut){
            this.timeOut = timeOut;
            return this;
        }

        public HTTPRequest build(){
            return new HTTPRequest(this);
        }
    }

}