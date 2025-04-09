package applicant.statistics;

import applicant.statistics.ui.ApplicantsProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Standalone main entry point for running the application from the command line.
 */
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -jar app.jar <input_file.csv> [--out output.json] [--logout summary.txt] [--debug]");
            return;
        }

        Map<String, String> options = parseArguments(args);
        String inputFilePath = args[0];
        String outputFilePath = options.get("out");
        String logOutFile = options.get("logout");
        boolean debugEnabled = Boolean.parseBoolean(options.getOrDefault("debug", "false"));

        if (debugEnabled) {
            System.setProperty("org.apache.logging.log4j.simplelog.level", "DEBUG");
        }

        try (InputStream input = new FileInputStream(inputFilePath)) {
            ApplicantsProcessor processor = new ApplicantsProcessor();
            String jsonOutput = processor.processApplicants(input);

            if (outputFilePath != null) {
                try (FileWriter writer = new FileWriter(outputFilePath)) {
                    writer.write(jsonOutput);
                    System.out.println("Output written to " + outputFilePath);
                }
            } else {
                System.out.println(jsonOutput);
            }

            if (logOutFile != null) {
                try (PrintWriter logWriter = new PrintWriter(logOutFile)) {
                    printSummary(logWriter, processor.getTotalLines(), processor.getValidLines(), processor.getSkippedLines());
                    System.out.println("Log summary written to " + logOutFile);
                } catch (IOException e) {
                    logger.error("Failed to write summary log to file: {}", logOutFile, e);
                }
            }

            logger.info("=== CLI Summary ===");
            printSummary(System.out, processor.getTotalLines(), processor.getValidLines(), processor.getSkippedLines());
            logger.info("Input file: {}", inputFilePath);
            logger.info("Debug mode: {}", debugEnabled);
            logger.info("Run completed.");

        } catch (IOException e) {
            logger.error("Failed to read or write file.", e);
        }
    }

    private static void printSummary(PrintStream out, int total, int valid, int skipped) {
        out.println("=== Summary Output ===");
        out.println("Total lines: " + total);
        out.println("Valid lines: " + valid);
        out.println("Skipped lines: " + skipped);
    }

    private static void printSummary(PrintWriter out, int total, int valid, int skipped) {
        out.println("=== Summary Output ===");
        out.println("Total lines: " + total);
        out.println("Valid lines: " + valid);
        out.println("Skipped lines: " + skipped);
    }

    private static Map<String, String> parseArguments(String[] args) {
        Map<String, String> options = new HashMap<>();
        for (int i = 1; i < args.length; i++) {
            switch (args[i]) {
                case "--out" -> {
                    if (i + 1 < args.length) options.put("out", args[++i]);
                }
                case "--logout" -> {
                    if (i + 1 < args.length) options.put("logout", args[++i]);
                }
                case "--debug" -> options.put("debug", "true");
            }
        }
        return options;
    }
}