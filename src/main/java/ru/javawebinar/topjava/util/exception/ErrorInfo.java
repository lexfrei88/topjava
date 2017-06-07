package ru.javawebinar.topjava.util.exception;

public class ErrorInfo {
    private String url;
    private String cause;
    private String detail;

    public ErrorInfo(String url, String cause, String detail) {
        this.url = url;
        this.cause = cause;
        this.detail = detail;
    }

    public ErrorInfo(CharSequence url, Throwable ex) {
        this.url = url.toString();
        this.cause = ex.getClass().getSimpleName();
        this.detail = ex.getLocalizedMessage();
    }

    public ErrorInfo() {}

    public String getUrl() {
        return url;
    }

    public String getCause() {
        return cause;
    }

    public String getDetail() {
        return detail;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "ErrorInfo{" +
                "url='" + url + '\'' +
                ", cause='" + cause + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}