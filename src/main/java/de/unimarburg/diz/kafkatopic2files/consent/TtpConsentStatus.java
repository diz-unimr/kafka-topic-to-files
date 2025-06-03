/* GNU AFFERO GENERAL PUBLIC LICENSE  Version 3 (C)2025 */
package de.unimarburg.diz.kafkatopic2files.consent;

public enum TtpConsentStatus {
  /** Valid consent found */
  CONSENTED,

  CONSENT_MISSING_OR_REJECTED,

  /** Due technical problems consent status is unknown */
  FAILED_TO_ASK
}
