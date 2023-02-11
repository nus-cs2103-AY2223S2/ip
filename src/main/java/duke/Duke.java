package duke;

import java.io.FileNotFoundException;
import java.io.IOException;


import javafx.scene.image.Image;

import duke.exception.DukeException;


/**
 * Duke class.
 */
public class Duke  {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/black-cat.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/black-cat.png"));

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
        }
    }

    /**
     * Runs the main program.
     */
    public String getResponse(String answer) {

        try {
            Parser parser = new Parser(answer);
            int length = tasks.getLength();
            if (answer.equals("bye")) {

                try {
                    storage.store(tasks);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return ui.sayGoodbye();
            }
            if (answer.equals("list")) {
                return tasks.toString();
            }

            if (answer.startsWith("find ")) {
                String keyword = parser.getFindKeyword();
                assert !keyword.isEmpty() : "find keyword should not be empty";
                return ui.showFind(tasks.find(keyword));


            }
            if (answer.startsWith("mark ")) {
                Integer index = parser.getMarkIndex(length);
                assert index > 0 && index <= length : "index should be in bounds";
                return ui.showMarked(tasks.mark(index));
            }
            if (answer.startsWith("unmark ")) {
                Integer index = parser.getUnmarkIndex(length);
                assert index > 0 && index <= length : "index should be in bounds";
                return ui.showUnmarked(tasks.unmark(index));
            }
            if (answer.startsWith("delete ")) {
                Integer index = parser.getDeleteIndex(length);
                assert index > 0 && index <= length : "index should be in bounds";
                return ui.showDeleted(tasks.delete(index));
            }
            if (answer.startsWith("todo ")) {
                String description = parser.getTodoDescription();
                assert !description.isEmpty() : "find keyword should not be empty";
                return ui.showAddTask(tasks.addTodo(description));
            }
            if (answer.startsWith("deadline ")) {
                String[] descriptionList = parser.getDeadlineDescription();

                return ui.showAddTask(tasks.addDeadline(descriptionList[0], descriptionList[1]));
            }
            if (answer.startsWith("event ")) {
                String[] descriptionList = parser.getEventDescription();

                assert !descriptionList[0].isEmpty() && !descriptionList[1].isEmpty()
                        : "description should not be empty";
                return ui.showAddTask(tasks.addEvent(descriptionList[0], descriptionList[1], descriptionList[2]));

            }
            if (answer.startsWith("event ")) {
                String[] descriptionList = parser.getEventDescription();
                assert !descriptionList[0].isEmpty() && !descriptionList[1].isEmpty()
                        : "description should not be empty";

                return ui.showAddTask(tasks.addEvent(descriptionList[0], descriptionList[1], descriptionList[2]));


            }

            throw new DukeException("I don't know that one!");
        } catch (DukeException e) {
            return ui.showError(e.toString());
        }

    }

}

