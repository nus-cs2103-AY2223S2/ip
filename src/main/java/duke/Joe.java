package duke;

import java.io.IOException;

class Joe {
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    public Joe() {
        try {
            storage = new Storage("duke.txt");
            taskList = storage.load();
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
        parser = new Parser(taskList);
    }
    String handleResponse(String input) {
        String out = parser.parse(input);;
        if (out.startsWith("added: ")) {
            String t = out.split("added: ", 2)[1];
            System.out.println(storage.matchesFormat(t));
            if (storage.matchesFormat(t)) {
                try {
                    storage.write(t);
                } catch (Exception e) {
                    out = e.getMessage();
                }
            }
        }
        if (out.equals("")) {
            out = sayBye();
        }
        return out;
    }

    String sayBye() {
        try {
            storage.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\tBye. Hope to see you again soon!");
        System.exit(0);
        return "";
    }
    public static void printNewLine() {
        String newline = "\t____________________________________________________________";
        System.out.println(newline);
    }
}
