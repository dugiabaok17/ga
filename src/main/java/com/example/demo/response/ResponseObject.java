package com.example.demo.response;

public class ResponseObject {
	private String status;
	private String message;
	private int errCode;
	private Object data;

	public ResponseObject() {
	}

	public ResponseObject(String status, String message, int errCode, Object data) {
		this.status = status;
		this.message = message;
		this.errCode = errCode;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseObject [status=" + status + ", message=" + message + ", errCode=" + errCode + ", data=" + data
				+ "]";
	}
}
