/* GNU AFFERO GENERAL PUBLIC LICENSE  Version 3 (C)2025 */
package de.unimarburg.diz.kafkatopic2files.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class AppConfigProperties {

  public Integer maxRetryAttempts;

  public String pathToPid;
  public Boolean useXPath;
  private AppConfigOutput appConfigOutput;
  private GIcsConfigProperties gIcsConfigProperties;

  /**
   * @param maxRetryAttempts
   * @param pathToPid Provide JsonPath oder XPath expression to extract patient identification
   *     value, which will be used to query for a valid consent.
   * @param useXPath If <c>true</c> we expect {@link #pathToPid} to be a xPath expression. If
   *     <c>false</c> we expect a json path expression. If key is missing and consent check is
   *     enabled we have a configuration error.
   * @param appConfigOutput
   * @param gIcsConfigProperties
   */
  @Autowired
  public AppConfigProperties(
      @Value("${app.maxRetryAttempts}") Integer maxRetryAttempts,
      @Value("${app.pathToPid}") String pathToPid,
      @Value("${app.useXPath}") Boolean useXPath,
      AppConfigOutput appConfigOutput,
      GIcsConfigProperties gIcsConfigProperties) {
    this.maxRetryAttempts = maxRetryAttempts;
    this.pathToPid = pathToPid;
    this.useXPath = useXPath;
    this.appConfigOutput = appConfigOutput;
    this.gIcsConfigProperties = gIcsConfigProperties;
  }

  public GIcsConfigProperties getgIcsConfigProperties() {
    return gIcsConfigProperties;
  }

  public AppConfigOutput getAppConfigOutput() {
    return appConfigOutput;
  }
}
