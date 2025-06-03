/* GNU AFFERO GENERAL PUBLIC LICENSE  Version 3 (C)2025 */
package de.unimarburg.diz.kafkatopic2files.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("app.gics")
public class GIcsConfigProperties {
  /** Base URL to gICS System */
  public String uri;

  public GIcsConfigProperties setUri(String uri) {
    this.uri = uri;
    return this;
  }

  public GIcsConfigProperties setUsername(String username) {
    this.username = username;
    return this;
  }

  public GIcsConfigProperties setPassword(String password) {
    this.password = password;
    return this;
  }

  public GIcsConfigProperties setEnabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public GIcsConfigProperties() {}

  public String getUri() {
    return uri;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public String getPersonIdentifierSystem() {
    return personIdentifierSystem;
  }

  public GIcsConfigProperties setPersonIdentifierSystem(String personIdentifierSystem) {
    this.personIdentifierSystem = personIdentifierSystem;
    return this;
  }

  public String getConsentDomainName() {
    return consentDomainName;
  }

  public GIcsConfigProperties setConsentDomainName(String consentDomainName) {
    this.consentDomainName = consentDomainName;
    return this;
  }

  public String getPolicyCode() {
    return policyCode;
  }

  public GIcsConfigProperties setPolicyCode(String policyCode) {
    this.policyCode = policyCode;
    return this;
  }

  public String getPolicySystem() {
    return policySystem;
  }

  public GIcsConfigProperties setPolicySystem(String policySystem) {
    this.policySystem = policySystem;
    return this;
  }

  public String username;
  public String password;

  /**
   * If value is 'true' valid consent at processing time is mandatory for transmission of DNPM files
   * otherwise they will be flagged and skipped. If value 'false' or missing consent status is
   * assumed to be valid.
   */
  public Boolean enabled;

  /** gICS specific system * */
  public String personIdentifierSystem =
      "https://ths-greifswald.de/fhir/gics/identifiers/Patienten-ID";

  /** Domain of consent resources * */
  public String consentDomainName = "MII";

  /** Value to expect in case of positiv consent */
  public String policyCode = "2.16.840.1.113883.3.1937.777.24.5.3.6";

  /** Consent Policy which should be used for consent check */
  public String policySystem = "urn:oid:2.16.840.1.113883.3.1937.777.24.5.3";

  public GIcsConfigProperties(
      String uri,
      String username,
      String password,
      Boolean enabled,
      String personIdentifierSystem,
      String consentDomainName,
      String policyCode,
      String policySystem) {
    this.uri = uri;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    if (personIdentifierSystem != null) this.personIdentifierSystem = personIdentifierSystem;
    if (consentDomainName != null) this.consentDomainName = consentDomainName;

    if (policyCode != null) this.policyCode = policyCode;
    if (policySystem != null) this.policySystem = policySystem;
  }
}
