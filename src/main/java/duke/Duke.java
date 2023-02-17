package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

import static duke.tool.Parser.process_input;

public class Duke {

    public static void print(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>(100);
        process_input(tasks, sc);
    }
}

