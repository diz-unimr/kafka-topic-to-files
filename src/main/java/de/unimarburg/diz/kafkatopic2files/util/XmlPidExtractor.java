/* GNU AFFERO GENERAL PUBLIC LICENSE  Version 3 (C)2025 */
package de.unimarburg.diz.kafkatopic2files.util;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;

@ConditionalOnProperty(name = "app.gics.useXPath", havingValue = "true")
@Service
public class XmlPidExtractor implements PidExtractor {
  private static final Logger log = LoggerFactory.getLogger(XmlPidExtractor.class);

  @Override
  public String readPid(String data, String propertyPath) {
    if (!StringUtils.hasText(data)) return null;
    var asXml = convertXMLFileToXMLDocument(data);
    XPathFactory xpathfactory = XPathFactory.newInstance();
    XPath xpath = xpathfactory.newXPath();

    try {

      String patientId = xpath.evaluate(propertyPath, asXml);

      return patientId;
    } catch (XPathExpressionException e) {
      log.error(
          "patient id extraction failed. please check your configuration property 'app.gics.pathToPid'.",
          e);
      throw new RuntimeException("patient id extraction failed!");
    }
  }

  private static Document convertXMLFileToXMLDocument(String xmlAsString) {
    // Parser that produces DOM object trees from XML content
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    // API to obtain DOM Document instance
    DocumentBuilder builder = null;
    try {
      // Create DocumentBuilder with default configuration
      builder = factory.newDocumentBuilder();

      // Parse the content to Document object
      Document doc =
          builder.parse(new ByteArrayInputStream(xmlAsString.getBytes(StandardCharsets.UTF_8)));
      return doc;
    } catch (Exception e) {
      log.error("parse to xml document failed!", e);
    }
    return null;
  }
}
