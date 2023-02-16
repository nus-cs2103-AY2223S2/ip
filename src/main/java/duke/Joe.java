package duke;

import java.io.IOException;

/**
 * This is Joe, my java bot!
 */
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
        try {
            storage.write(parser.returnList(parser.getTaskList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }


}
