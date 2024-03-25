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
	@Bean
	public RouterFunction<ServerResponse> paymentRoute() {
		return route("payment_route")
				.POST("/checkout", http("http://localhost:8084/payment/chekout"))
				.build();
	}

	@Bean
	public RouterFunction<ServerResponse> cartRoute() {
		return route("cart_route")
				.GET("/shopping-cart/*", http("http://localhost:8083/shopping-cart/*"))
				.POST("/shopping-cart", http("http://localhost:8083/shopping-cart"))
				.PUT("/shopping-cart/*", http("http://localhost:8083/shopping-cart/*"))
				.DELETE("/shopping-cart/*", http("http://localhost:8083/shopping-cart/*"))
				.build();
	}

	@Bean
	public RouterFunction<ServerResponse> itemRoute() {
		return route("item_route")
				.GET("/items/*", http("http://localhost:8082/items/*"))
				.POST("/items", http("http://localhost:8082/items"))
				.PUT("/items/*", http("http://localhost:8082/items/*"))
				.DELETE("/items/*", http("http://localhost:8082/items/*"))
				.build();
	}
	@Bean
	public RouterFunction<ServerResponse> userRoute() {
		return route("item_route")
				.GET("/users/*", http("http://localhost:8081/users/*"))
				.POST("/users", http("http://localhost:8081/users"))
				.PUT("/users/*", http("http://localhost:8081/users/*"))
				.DELETE("/users/*", http("http://localhost:8081/users/*"))
				.POST("/login", http("http://localhost:8081/login"))
				.build();
	}
}
