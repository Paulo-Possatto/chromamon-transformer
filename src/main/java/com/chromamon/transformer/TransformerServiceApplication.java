package com.chromamon.transformer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;

/**
 * The main application class for the service.
 */
@SpringBootApplication
@Slf4j
public class TransformerServiceApplication {

   private static final int TAB_COUNTER = 100;

   /**
    * The application main method.
    *
    * @param args the arguments array used in the application.
    */
	public static void main(String[] args) {

      Instant initialInstant = Instant.now();
      SpringApplication.run(TransformerServiceApplication.class, args);
      Instant finalInstant = Instant.now();

      long totalTimeToLoad = finalInstant.toEpochMilli() - initialInstant.toEpochMilli();

      log.info(tabRepeater());
		log.info("'Chromamon Analysis Service' started in {} ms", totalTimeToLoad);
      log.info(tabRepeater());

	}

   private static String tabRepeater(){
      return "-".repeat(TAB_COUNTER);
   }
}
