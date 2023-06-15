package com.example.lectureevaluationdev.primary;

public class ResponseService {
    private LoginResponse.ResponseMap result;
    public LoginResponse setResponse(int code, String message, Object object) throws Exception {
        result = new LoginResponse.ResponseMap();
        result.setCode(code);
        result.setResponseData(message, object);
        return result;
    }

    public LoginResponse addResponse(String message, Object object) throws Exception {
        if(result == null)
            result = new LoginResponse.ResponseMap();
        result.setResponseData(message, object);
        return result;
    }

    public LoginResponse getResult() {
        return result;
    }

    public void closeResult() {
        if(result != null)
            result.clear();
    }
}
