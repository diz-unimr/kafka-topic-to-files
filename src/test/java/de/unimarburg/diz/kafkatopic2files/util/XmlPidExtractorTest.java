/* GNU AFFERO GENERAL PUBLIC LICENSE  Version 3 (C)2025 */
package de.unimarburg.diz.kafkatopic2files.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.Test;

class XmlPidExtractorTest {

  XmlPidExtractor fixture = new XmlPidExtractor();

  @Test
  void readPid() {
    String xml = null;
    try {
      xml = readExampleXml("Testfile_1.xml");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    var result = fixture.readPid(xml, "//Menge_Patient/Patient/Patienten_Stammdaten/@Patient_ID");

    assertThat(result).isEqualTo("1234567890");
  }

  private String readExampleXml(String filename) throws IOException {
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource(filename).getFile());

    String data = null;
    try {
      data = new String(Files.readAllBytes(file.toPath()));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return data;
  }
}
