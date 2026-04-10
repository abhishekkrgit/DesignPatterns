package DesignPatterns.behavioral.chainOfResponsibility;


class HttpRequest {
    private String url;
    private String method;
    private String body;

    public HttpRequest(String url, String method, String body) {
        this.url = url;
        this.method = method;
        this.body = body;
    }

    public String getUrl() { return url; }
    public String getMethod() { return method; }
    public String getBody() { return body; }
}

abstract class BaseRequestHandler {
    protected  BaseRequestHandler nextHandler;

    public void setNextHandler(BaseRequestHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    public abstract void handle(HttpRequest request);

    protected void forwardRequest(HttpRequest request) {
        if (nextHandler != null) {
            nextHandler.handle(request);
        }
    }
}

class AuthenticationHandler extends BaseRequestHandler {
    @Override
    public void handle(HttpRequest request) {
        System.out.println("AuthenticationHandler: Authenticating request...");
        forwardRequest(request);
    }
}

class RateLimitingHandler extends BaseRequestHandler {
    @Override
    public void handle(HttpRequest request) {
        // Rate limiting logic here
        System.out.println("RateLimitingHandler: Checking rate limits...");
        forwardRequest(request);
    }
}

class ValidationHandler extends BaseRequestHandler {
    @Override
    public void handle(HttpRequest request) {
        // Validation logic here
        System.out.println("ValidationHandler: Validating request...");
        forwardRequest(request);
    }
}

class BusinessLogicHandler extends BaseRequestHandler {
    @Override
    public void handle(HttpRequest request) {
        // Business logic here
        System.out.println("BusinessLogicHandler: Processing business logic...");
        // No next handler since this is the end of the chain
    }
}


public class HttpRequestHandlerApp {
    public static void main(String[] args) {
        BaseRequestHandler authHandler = new AuthenticationHandler();
        BaseRequestHandler rateLimitingHandler = new RateLimitingHandler();
        BaseRequestHandler validationHandler = new ValidationHandler();
        BaseRequestHandler businessLogicHandler = new BusinessLogicHandler();
        authHandler.setNextHandler(rateLimitingHandler);
        rateLimitingHandler.setNextHandler(validationHandler);
        validationHandler.setNextHandler(businessLogicHandler);
        HttpRequest request = new HttpRequest("/api/data", "POST", "{ \"key\": \"value\" }");
        authHandler.handle(request);
    }
}
