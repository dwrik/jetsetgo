package com.dwrik.booking.feignclients;

import feign.HeaderMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient("flight")
public interface FlightClient {

	@RequestMapping(method = RequestMethod.POST, value = "{id}/reserve")
	Map<String, Object> reserveSeat(@HeaderMap Map<String, Object> headers, @PathVariable Long id);
}
