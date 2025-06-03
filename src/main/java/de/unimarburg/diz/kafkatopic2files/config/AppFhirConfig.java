/* GNU AFFERO GENERAL PUBLIC LICENSE  Version 3 (C)2025 */
package de.unimarburg.diz.kafkatopic2files.config;

import ca.uhn.fhir.context.FhirContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppFhirConfig {
  private FhirContext fhirCtx = FhirContext.forR4();

  @Bean
  public FhirContext fhirContext() {
    return fhirCtx;
  }
}
