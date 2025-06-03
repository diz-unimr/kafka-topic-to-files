/* GNU AFFERO GENERAL PUBLIC LICENSE  Version 3 (C)2025 */
package de.unimarburg.diz.kafkatopic2files.consent;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@ConditionalOnProperty(name = "app.consent.enabled", havingValue = "false")
public class SkipConsentCheck implements ICheckConsent {

  @Override
  public TtpConsentStatus getTtpConsentStatus(String personIdentifierValue) {
    return TtpConsentStatus.CONSENTED;
  }
}
