package com.dgsspa.comicverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ComicverseApplication {

	private static final Logger log = LoggerFactory.getLogger(ComicverseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ComicverseApplication.class, args);
	}

	@Bean
	public ApplicationRunner logWorkingDirectory(Environment environment) {
		return args -> {
			String workingDir = System.getProperty("user.dir");
			log.info("Working directory: {}", workingDir);
			String logFileName = environment.getProperty("logging.file.name");
			String logFilePath = environment.getProperty("logging.file.path");
			if (logFileName != null && !logFileName.isBlank()) {
				log.info("Log file configured: {}", logFileName);
			} else if (logFilePath != null && !logFilePath.isBlank()) {
				log.info("Log path configured: {} (file: {}/comicverse.log)", logFilePath, logFilePath);
			} else {
				log.info("Log file path not set; using Logback defaults (logs/comicverse.log)");
			}
			String[] activeProfiles = environment.getActiveProfiles();
			if (activeProfiles.length > 0) {
				log.info("Active profiles: {}", String.join(",", activeProfiles));
			} else {
				log.info("Active profiles: default");
			}
		};
	}
}
