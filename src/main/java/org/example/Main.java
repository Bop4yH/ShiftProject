package org.example;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        ParserOptions options = new ParserOptions(args);
        Stats stats = options.isShortStats() ? new shortStats() : new fullStats();
        List<String> logs = new ArrayList<>();
        if (options.isValid()) {

            for (String file : options.getFiles()) {
                FileParser parser = new FileParser(file, options);
                parser.parse(stats, logs);
            }

            if (!stats.isEmpty()) {
                System.out.println();
                stats.printStats();
            } else {
                logs.add("No data found");
            }
        }


        if (!logs.isEmpty()) {
            System.out.println("Problems during parsing occurred, logs.txt will be created");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("logs.txt"))) {
                for (String log : logs) {
                    writer.write(log);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}