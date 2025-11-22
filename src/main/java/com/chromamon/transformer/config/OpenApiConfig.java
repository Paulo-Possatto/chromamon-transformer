package com.chromamon.transformer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

/**
 * The global configuration class for Open API documentation.
 */
public class OpenApiConfig {

   /**
    * The main information displayed in the Open API documentation.
    *
    * @return the OpenAPI bean with the information.
    */
   @Bean
   public OpenAPI transformerServiceOpenApi(){
      return new OpenAPI()
         .info(new Info().title("[CHROMAMON] Transformer Service")
            .description(
               "Spring shop sample application"
            )
            .version("v0.0.1")
            .license(
               new License()
                  .name("GNU GPL-3.0")
                  .url("https://www.gnu.org/licenses/gpl-3.0.txt")
            )
         );
   }
}
