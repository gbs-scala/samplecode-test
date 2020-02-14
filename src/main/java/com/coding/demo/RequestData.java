package com.coding.demo;

public class RequestData {
    private long requestTime;
    private String countryCode;
    private long responseTime;

    public RequestData() {
        this.requestTime = requestTime;
        this.countryCode = countryCode;
        this.responseTime = responseTime;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public boolean isCountryCode(String countryCode) {
        return this.countryCode.equals(countryCode);
    }

    public boolean isResponseTimeAboveLimit(long limit) {
        return (this.responseTime > limit);
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }
}
