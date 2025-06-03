/* GNU AFFERO GENERAL PUBLIC LICENSE  Version 3 (C)2025 */
package de.unimarburg.diz.kafkatopic2files.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.output")
public record AppConfigOutput(String dir, String prefix, String postfix) {

  public AppConfigOutput {}
}
