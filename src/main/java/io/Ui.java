package io;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ui {
        private static final int MAX_LINE_LENGTH = 69;
        private static final String OUTPUT_FORMAT = "%4s %s";
        private static final Scanner scanner = new Scanner(System.in);

        private static final String LOGO = " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|";

        public static void showReply(String msg) {
                System.out.println();
                List<String> lst = msg.lines()
                                .flatMap(s -> split(s))
                                .collect(Collectors.toList());
                IntStream.range(0, lst.size())
                                .mapToObj(i -> (i == 0 ? String.format(OUTPUT_FORMAT, "D:  ", lst.get(i))
                                                : String.format(OUTPUT_FORMAT, " ", lst.get(i))))
                                .forEach(System.out::println);
                System.out.println();
        }

        public static void showWelcome() {
                System.out.println(LOGO);
        }

        private static Stream<String> split(String line) {
                return line.length() < MAX_LINE_LENGTH ? Stream.of(line)
                                : Stream.concat(Stream.of(line.substring(0, MAX_LINE_LENGTH + 1)),
                                                split(line.substring(MAX_LINE_LENGTH + 1)));
        }

        public static String getInput() {
                return scanner.nextLine();
        }
}
