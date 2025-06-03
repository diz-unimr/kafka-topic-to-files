/* GNU AFFERO GENERAL PUBLIC LICENSE  Version 3 (C)2025 */
package de.unimarburg.diz.kafkatopic2files.util;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.gics.useXPath", havingValue = "false")
public class JsonPidExtractor implements PidExtractor {

  @Override
  public String readPid(String data, String propertyPath) {
    throw new NotImplementedException("not yet implemented.");
    //        return "";
  }
}
