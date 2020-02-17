package za.co.discovery.assignment.andrevdwal.dto;

import org.springframework.http.HttpStatus;

public class ServiceResponseFactory {

	public <T> ServiceResponseDto<T> notFound() {

		ServiceResponseDto<T> instance = new ServiceResponseDto<T>();
		instance.setDescription("Does not exists");
		instance.setStatus(HttpStatus.NOT_FOUND.value());
		instance.setSuccess(false);
		return instance;
	}

	public <T> ServiceResponseDto<T> badRequest(String message) {

		ServiceResponseDto<T> instance = new ServiceResponseDto<T>();
		instance.setDescription(message);
		instance.setStatus(HttpStatus.BAD_REQUEST.value());
		instance.setSuccess(false);
		return instance;
	}

	public <T> ServiceResponseDto<T> ok(T payload) {

		ServiceResponseDto<T> instance = new ServiceResponseDto<T>();
		instance.setPayload(payload);
		instance.setStatus(HttpStatus.OK.value());
		instance.setSuccess(true);
		return instance;
	}
}
