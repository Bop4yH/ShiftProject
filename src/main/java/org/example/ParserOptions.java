package org.example;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.List;

public class ParserOptions {
    public static final Option ARG_PATH = new Option("o", true, "results path");
    public static final Option ARG_PREFIX = new Option("p", true, "prefix to result path");
    public static final Option ARG_ADD_MODE = new Option("a", false, "adding to existing files");
    public static final Option ARG_FULL = new Option("f", false, "full statistics");
    public static final Option ARG_SHORT = new Option("s", false, "short statistics");

    private final Options options;
    private List<String> files;

    private String path;
    private String prefix;
    private boolean addMode = false;
    private boolean shortStats;
    private boolean fullStats;

    private boolean isValid = true;

    public boolean isShortStats() {
        return shortStats;
    }

    public boolean isFullStats() {
        return fullStats;
    }




    public String getPath() {
        return path;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean getAddMode() {
        return addMode;
    }

    public List<String> getFiles() {
        return files;
    }

    public boolean isValid() {
        return isValid;
    }

    public ParserOptions(String[] args) {
        options = new Options();
        options.addOption(ARG_PATH);
        options.addOption(ARG_PREFIX);
        options.addOption(ARG_ADD_MODE);
        options.addOptionGroup(new OptionGroup().addOption(ARG_SHORT).addOption(ARG_FULL));
        files = new ArrayList<>();

        defineOptions(args);
    }

    private void defineOptions(String[] args) {
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (!cmd.getArgList().isEmpty()) {
                files = cmd.getArgList();

            } else {
                throw new ParseException(" No input files ");
            }

            if (cmd.hasOption("o")) {
                path = cmd.getOptionValue("o").replaceFirst("/", "");
            }

            if (cmd.hasOption("p")) {
                prefix = cmd.getOptionValue("p");
            }

            if (cmd.hasOption("a")) {
                addMode = true;
            }

            if (cmd.hasOption("s")) {
                shortStats = true;
            }

            if (cmd.hasOption("f")) {
                fullStats = true;
            }
        } catch (ParseException e) {
            isValid = false;
            System.err.println("Error parsing command line arguments: " + e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar util.jar -s -o /some/path -p sample- in1.txt in2.txt", options);
        }
    }
}
