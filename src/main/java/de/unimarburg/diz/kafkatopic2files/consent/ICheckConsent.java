/* GNU AFFERO GENERAL PUBLIC LICENSE  Version 3 (C)2025 */
package de.unimarburg.diz.kafkatopic2files.consent;

public interface ICheckConsent {

  TtpConsentStatus getTtpConsentStatus(String personIdentifierValue);
}
