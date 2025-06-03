# Kafka Topic 2 FILE

1. Read Messages from topic
2. Optional consent check via REST API to gIcs
3. Export message to filesystem 

## Configuration

| env property           | description                                                                                                    |
|------------------------|----------------------------------------------------------------------------------------------------------------|
| app.maxRetryAttempts   | retry count to get consent                                                                                     |
| app.pathToPid          | Xpath to XML property holding patient ID                                                                       |
| app.useXPath           | currentyl always true (json path no yet implemneted)                                                           |
| app.gics.username      | user name to get consent data                                                                                  |
| app.gics.password      | user password to get consent data                                                                              |
| app.gics.enabled       | *true* if consent should be checked before file writing                                                        |
| app.gics.url           | base url to fhir endpoint                                                                                      |
| app.gics.policySystem  | Consent Policy which should be used for consent check (default: 'urn:oid:2.16.840.1.113883.3.1937.777.24.5.3') |
| policyCode             | Value to expect in case of positiv consent (default:2.16.840.1.113883.3.1937.777.24.5.3.6)                     |
| consentDomainName      | Domain of consent resources (default:'MII')                                                                    | 
| personIdentifierSystem | gICS specific system (default: 'https://ths-greifswald.de/fhir/gics/identifiers/Patienten-ID')                 | 