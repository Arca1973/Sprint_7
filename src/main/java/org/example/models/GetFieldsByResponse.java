package org.example.models;

public class GetFieldsByResponse {

    private int  code;
    private String message;
    private Boolean ok;
    private String id;

    public GetFieldsByResponse(int code, String message, Boolean ok, String id) {
        this.code = code;
        this.message = message;
        this.ok = ok;
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

