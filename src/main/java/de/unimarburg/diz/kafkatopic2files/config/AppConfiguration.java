/* GNU AFFERO GENERAL PUBLIC LICENSE  Version 3 (C)2025 */
package de.unimarburg.diz.kafkatopic2files.config;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.retry.support.RetryTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@EnableConfigurationProperties(
    value = {GIcsConfigProperties.class, AppConfigOutput.class, AppConfigProperties.class})
public class AppConfiguration {

  private final AppConfigProperties configProperties;
  private Logger logger = LoggerFactory.getLogger(AppConfiguration.class);

  @Autowired
  public AppConfiguration(AppConfigProperties configProperties) {
    this.configProperties = configProperties;
  }

  @Bean
  public RetryTemplate retryTemplate(AppConfigProperties configProperties) {
    return new RetryTemplateBuilder()
        .notRetryOn(IllegalArgumentException.class)
        .notRetryOn(HttpClientErrorException.BadRequest.class)
        .notRetryOn(HttpClientErrorException.UnprocessableEntity.class)
        .exponentialBackoff(Duration.ofSeconds(2), 1.25, Duration.ofSeconds(5))
        .customPolicy(new SimpleRetryPolicy(configProperties.maxRetryAttempts))
        .withListener(
            new RetryListener() {
              @Override
              public <T, E extends Throwable> void onError(
                  RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
                logger.warn(
                    "Error occured: {}. Retrying {}",
                    throwable.getMessage(),
                    context.getRetryCount());
                RetryListener.super.onError(context, callback, throwable);
              }
            })
        .build();
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
