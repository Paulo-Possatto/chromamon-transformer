package com.chromamon.transformer;

import org.springframework.boot.SpringApplication;

public class TestTransformerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(TransformerServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
