package james;

import james.command.Command;
import james.parser.Parser;
import james.task.TaskList;

public class James {
        private UI ui;
        private Storage storage;
        private TaskList taskList;
        private Parser parser;

        public James() throws JamesException {
            parser = new Parser();
            storage = new Storage();
            ui = new UI();
            taskList = storage.loadFile();

            ui.welcome();
            String input = ui.readCommand();

            while (!input.equals("bye")) {
                try {
                    Command command = parser.parseCommand(input);
                    command.assign(taskList, ui);
                    command.execute();
                } catch (JamesException e) {
                    ui.printError(e);
                } finally {
                    input = ui.readCommand();
                }
            }
            storage.writeToFile(taskList);
            ui.exit();
        }

        public static void main(String[] args) throws JamesException {
            new James();
        }
    }



