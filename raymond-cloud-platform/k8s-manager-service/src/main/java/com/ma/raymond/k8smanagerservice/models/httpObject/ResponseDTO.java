package com.ma.raymond.k8smanagerservice.models.httpObject;

/**
 * Response code:
 * 100:received
 * 200:success
 * 300:redirect
 * 400:Client side error
 * 500:Server error
 */
public class ResponseDTO {
    //Error code
    private String error;

    //For display
    private String message;

    //For tracing error
    private String detail;

    private String instance;
    //
    private String helpUrl;

    private Object obj;

    public ResponseDTO(String error, String message, String detail, String instance, String helpUrl, Object obj) {
        this.error = error;
        this.message = message;
        this.detail = detail;
        this.instance = instance;
        this.helpUrl = helpUrl;
        this.obj = obj;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getHelpUrl() {
        return helpUrl;
    }

    public void setHelpUrl(String helpUrl) {
        this.helpUrl = helpUrl;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
