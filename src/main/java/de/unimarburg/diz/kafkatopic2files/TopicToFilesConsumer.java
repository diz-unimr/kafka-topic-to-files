/* GNU AFFERO GENERAL PUBLIC LICENSE  Version 3 (C)2025 */
package de.unimarburg.diz.kafkatopic2files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class TopicToFilesConsumer {

  private static final Logger log = LoggerFactory.getLogger(TopicToFilesConsumer.class);

  private final String outputDir;
  private final String outPrefix;
  private final String outPostfix;

  @Autowired
  public TopicToFilesConsumer(
      @Value("${app.output.dir}") String outputDir,
      @Value("${app.output.prefix}") String outPrefix,
      @Value("${app.output.postfix}") String outPostfix) {

    this.outputDir = StringUtils.hasText(outputDir) ? outputDir.trim() : "";
    this.outPrefix = StringUtils.hasText(outPrefix) ? outPrefix.trim() : "";
    this.outPostfix = StringUtils.hasText(outPostfix) ? outPostfix.trim() : "";
  }

  @KafkaListener(
      topics = "${spring.kafka.consumer.topic.name}",
      properties = {"auto.offset.reset=earliest"})
  public void writeMessageToDirectory(ConsumerRecord<String, String> record) {
    String message = record.value();

    final String fileName = getFileName(record);

    // makes sure directory is created
    Path path = Paths.get(outputDir);
    try {
      createDirectoryPath(path);
    } catch (IOException e) {
      log.error("could not create target directory '{}'", outputDir, e);
    }
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {

      writer.write(message);
      writer.newLine();

      log.debug("Message written to file: {}", fileName);
    } catch (IOException e) {
      log.error("Failed to write message to file: {}", e.getMessage());
      throw new RuntimeException("failed to write message to fiele" + fileName, e);
    }

    log.debug("Key: {}, Value:{}", record.key(), record.value());
    log.debug("Partition:{},Offset:{}", record.partition(), record.offset());
    log.info(
        "message consumed. starts {} ...",
        record.value().substring(0, Math.min(20, record.value().length())));
  }

  private static void createDirectoryPath(Path path) throws IOException {
    if (path == null) {
      return;
    }
    createDirectoryPath(path.getParent());
    Files.createDirectories(path);
  }

  private String getFileName(ConsumerRecord<String, String> record) {
    return Paths.get(
            outputDir,
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssn-"))
                + outPrefix
                + record.key()
                + outPostfix)
        .toString();
  }
}
