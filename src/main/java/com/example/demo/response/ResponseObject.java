package com.example.demo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ResponseObject {
	private String status;
	private String message;
	private int errCode;
	private Object error;
	private String detailMessage;

	public ResponseObject() {
	}

	public ResponseObject(String status, String message, int errCode, Object error) {
		this.status = status;
		this.message = message;
		this.errCode = errCode;
		this.error = error;
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

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ResponseObject [status=" + status + ", message=" + message + ", errCode=" + errCode + ", error=" + error
				+ "]";
	}

	public ResponseObject(String status, String message, int errCode, Object error, String detailMessage) {
		this.status = status;
		this.message = message;
		this.errCode = errCode;
		this.error = error;
		this.detailMessage = detailMessage;
	}
}
