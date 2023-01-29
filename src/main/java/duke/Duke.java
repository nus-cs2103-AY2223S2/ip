package duke;

import duke.exception.DukeException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;




public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke object.
     * @param filePath Filepath of where txt file is write into.
     * @param dirPath Directory path of where txt file is put into.
     */
    public Duke(String filePath, String dirPath) {
        ui = new Ui();
        storage = new Storage("src/main/data/duke.txt", "src/main/data");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } // change to DukeException bruh
    }


    public void run() {
        ui.greet();

        while (true) {
            try {
                String answer = ui.readCommand();
                Parser parser = new Parser(answer);
                int length = tasks.getLength();
                if (answer.equals("bye")) {
                    ui.sayGoodbye();
                    try {
                        storage.store(tasks);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                if (answer.equals("list")) {
                    tasks.list();
                    continue;
                }

                if (answer.startsWith("mark ")) {
                    Integer index = parser.getMarkIndex(length);
                    ui.showMarked(tasks.mark(index));
                    continue;
                }
                if (answer.startsWith("unmark ")) {
                    Integer index = parser.getUnmarkIndex(length);
                    ui.showUnmarked(tasks.unmark(index));
                    continue;
                }
                if (answer.startsWith("delete ")) {
                    Integer index = parser.getDeleteIndex(length);
                    ui.showDeleted(tasks.delete(index));
                    continue;
                }
                if (answer.startsWith("todo ")) {
                    String description = parser.getTodoDescription();
                    ui.showAddTask(tasks.addTodo(answer));
                    continue;
                }
                if (answer.startsWith("deadline ")) {
                    String description = parser.getDeadlineDescription();
                    ui.askBy();
                    String askBy = ui.readCommand();
                    LocalDateTime by = parser.getDeadlineBy(askBy);
                    ui.showAddTask(tasks.addDeadline(description, by));
                    continue;
                }
                if (answer.startsWith("event ")) {
                    String description = parser.getEventDescription();
                    ui.askFrom();
                    String askFrom = ui.readCommand();
                    LocalDateTime from = parser.getEventFrom(askFrom);
                    ui.askTo();
                    String askTo = ui.readCommand();
                    LocalDateTime to = parser.getEventTo(askTo);
                    ui.showAddTask(tasks.addEvent(description, from, to));
                    continue;
                }
                throw new DukeException("I don't know that one!");
            }

            catch (DukeException e) {
                ui.showError(e.toString());
            }

        }


    }

    public static void main(String[] args) {
        new Duke("src/main/data/duke.txt", "src/main/data").run();

    }

}

