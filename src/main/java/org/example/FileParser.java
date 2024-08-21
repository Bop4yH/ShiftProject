package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class FileParser {

    private final Path input;
    private final ParserOptions options;

    private Path outInt = Path.of("integers.txt");
    private Path outFloat = Path.of("floats.txt");
    private Path outStr = Path.of("strings.txt");

    public FileParser(String input, ParserOptions options) {
        this.input = Path.of(input);
        this.options = options;

        definePath();
    }

    private void definePath() {
        if (options.getPrefix() != null) {
            outInt = Path.of(options.getPrefix() + outInt.toString());
            outFloat = Path.of(options.getPrefix() + outFloat.toString());
            outStr = Path.of(options.getPrefix() + outStr.toString());
        }

        if (options.getPath() != null) {
            outInt = Path.of(options.getPath() + '/' + outInt.toString());
            outFloat = Path.of(options.getPath() + '/' + outFloat.toString());
            outStr = Path.of(options.getPath() + '/' + outStr.toString());

        }
    }

    public void parse(Stats stats, List<String> logs) {
        BufferedWriter writer1 = null;
        BufferedWriter writer2 = null;
        BufferedWriter writer3 = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(input.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                boolean addModeRuntime;
                switch (stats.addToStats(line)) {
                    case INTEGER -> {
                        addModeRuntime = stats.getCountInt() != 1 || options.getAddMode();
                        if (writer1 == null) {
                            File file = new File(outInt.toString());
                            file.getParentFile().mkdirs();
                            writer1 = new BufferedWriter(new FileWriter(file, addModeRuntime));
                        }
                        writer1.write(line);
                        writer1.newLine();
                    }
                    case FLOAT -> {
                        addModeRuntime = stats.getCountFloat() != 1 || options.getAddMode();
                        if (writer2 == null) {
                            File file = new File(outFloat.toString());
                            file.getParentFile().mkdirs();

                            writer2 = new BufferedWriter(new FileWriter(file, addModeRuntime));
                        }
                        writer2.write(line);
                        writer2.newLine();
                    }
                    case STRING -> {
                        addModeRuntime = stats.getCountStr() != 1 || options.getAddMode();
                        if (writer3 == null) {
                            File file = new File(outStr.toString());
                            file.getParentFile().mkdirs();
                            writer3 = new BufferedWriter(new FileWriter(file, addModeRuntime));
                        }
                        writer3.write(line);
                        writer3.newLine();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logs.add("file " + input.toString() + " was not found");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer1 != null) writer1.close();
                if (writer2 != null) writer2.close();
                if (writer3 != null) writer3.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
