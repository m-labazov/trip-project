package ua.home.trip.controller;

public class Response {

	private String statusCode;
    private Object data;
	private String httpStatus;

	public Object getData() {
        return data;
	}

    public void setData(Object data) {
        this.data = data;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;

	}

	public String getHttpStatus() {
		return httpStatus;
	}

}
