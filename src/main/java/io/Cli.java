package io;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import command.Command;
import command.Error;
import task.TaskList;
import util.Pair;

/**
 * User interface (Command line).
 */
public class Cli implements Ui {
    private static final int MAX_LINE_LENGTH = 69;
    private static final String OUTPUT_FORMAT = "%4s %s";

    private static final String LOGO = "██████╗\n"
            + "██╔══██╗\n"
            + "██║  ██║\n"
            + "██║  ██║\n"
            + "██████╔╝\n"
            + "╚═════╝\n"
            + "___                                                            \n"
            + " |  _.  _ |    _|_ ._ _.  _ |  o ._   _     _ |_   _. _|_ |_   _ _|_\n"
            + " | (_| _> |<    |_ | (_| (_ |< | | | (_|   (_ | | (_|  |_ |_) (_) |_\n"
            + "                                      _|                             ";

    private final Scanner scanner;
    private final TaskList taskList;
    private final Storage<TaskList> storage;
    private boolean isExit;

    /**
     * @param storage taskList storage object
     */
    private Cli(Storage<TaskList> storage) {
        System.out.println(LOGO);
        this.scanner = new Scanner(System.in);
        this.storage = storage;
        this.taskList = this.storage.load().match(
                lst -> lst,
                error -> {
                    switch (error) {
                        case IO_ERROR:
                        case CAST_ERROR:
                            showReply("Error loading tasks, tasks have been reset.");
                            return new TaskList();
                        default:
                            return new TaskList();
                    }
                });
        this.showReply(String.format("Current tasks: %s", this.taskList.toString()));
        this.isExit = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showReply(String msg) {
        System.out.println();
        List<String> lst = msg.lines()
                .flatMap(Cli::split)
                .collect(Collectors.toList());
        IntStream.range(0, lst.size())
                .mapToObj(i -> (i == 0 ? String.format(OUTPUT_FORMAT, "D:  ", lst.get(i))
                        : String.format(OUTPUT_FORMAT, " ", lst.get(i))))
                .forEach(System.out::println);
        System.out.println();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showError(String errorMsg) {
        showReply(errorMsg);
    }

    /**
     * Entry point into app
     */
    public static void launch(String... args) {
        Cli cli = new Cli(Storage.of(TaskList.class, "taskList.ser"));
        cli.run();
    }

    /**
     * Main run loop
     */
    private void run() {
        while (!isExit) {
            String userInput = scanner.nextLine();
            Command command = Command.parser().parse(userInput).match(
                    Pair::first,
                    Error::of);
            command.execute(taskList, this, storage);
            isExit = command.isExit();
        }
    }

    /**
     * Splits large strings into lines below a max line length.
     *
     * @param line string to be split.
     * @return Stream of lines.
     */
    private static Stream<String> split(String line) {
        return line.length() <= MAX_LINE_LENGTH ? Stream.of(line)
                : Stream.concat(Stream.of(line.substring(0, MAX_LINE_LENGTH + 1)),
                split(line.substring(MAX_LINE_LENGTH + 1)));
    }
}
