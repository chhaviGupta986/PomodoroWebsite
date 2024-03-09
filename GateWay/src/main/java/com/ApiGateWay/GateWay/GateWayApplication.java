package com.ApiGateWay.GateWay;

import com.ApiGateWay.GateWay.Configuration.Netty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackageClasses = {Netty.class})
public class GateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}

}
