package com.dwrik.gateway.filter;

import com.dwrik.gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@Component
public class AuthFilter implements GatewayFilter {

	// list of secured endpoints
	private final List<String> securedEndpoints = List.of(
			"/reserve",
			"/booking",
			"/checkin"
	);

	// predicate for validating routes
	private final Predicate<ServerHttpRequest> isSecuredEndpoint =
			request -> securedEndpoints.stream().anyMatch(path -> request.getURI().getPath().contains(path));

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// get request
		ServerHttpRequest request = exchange.getRequest();

		// check if secured endpoint
		if (isSecuredEndpoint.test(request)) {
			// check if auth header missing
			if (isAuthHeaderMissing(request)) {
				return onError(exchange, HttpStatus.UNAUTHORIZED);
			}

			// get auth token from header
			final String token = getAuthToken(request);

			// check if auth token valid
			if (jwtUtil.isTokenInvalid(token)) {
				return onError(exchange, HttpStatus.BAD_REQUEST);
			}

			// extract claims from token and add to headers
			populateRequestWithHeaders(exchange, token);
		}

		// forward request to next filter in chain
		return chain.filter(exchange);
	}

	private void populateRequestWithHeaders(ServerWebExchange exchange, final String token) {
		Claims claims = jwtUtil.getAllClaims(token);
		String userId = claims.get("userId", Integer.class).toString();
		String email = claims.get("email", String.class);
		exchange.getRequest()
				.mutate()
				.header("userId", userId)
				.header("email", email)
				.build();
	}

	private Mono<Void> onError(ServerWebExchange exchange, HttpStatus statusCode) {
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(statusCode);
		return response.setComplete();
	}

	private boolean isAuthHeaderMissing(ServerHttpRequest request) {
		return !request.getHeaders().containsKey("Authorization")
				|| !(request.getHeaders().get("Authorization").get(0).length() > 7);
	}

	private String getAuthToken(ServerHttpRequest request) {
		return request.getHeaders().getOrEmpty("Authorization").get(0).substring(7);
	}
}
