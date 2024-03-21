package com.postech30.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouterFunction<ServerResponse> helloRoute() {
		return RouterFunctions.route()
				.GET("/hello", request -> ServerResponse.ok().body("Hello World"))
				.build();
	}

	@Bean
	public RouterFunction<ServerResponse> cardRoute() {
		return route("card_route")
				.GET("/card/*", http("http://localhost:8084/card/*"))
				.POST("/card", http("http://localhost:8084/card"))
				.PUT("/card/*", http("http://localhost:8084/card/*"))
				.DELETE("/card/*", http("http://localhost:8084/card/*"))
				.build();
	}

}