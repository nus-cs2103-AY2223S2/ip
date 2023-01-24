public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();
        TaskList taskList = Storage.load();
        boolean isExit = false;
        while (!isExit) {
            String userLine = ui.readCommand();
            String[] split = userLine.split(" ");
            String command = split[0];
            switch (command) {
            case "bye":
                ui.message("Bye, hope to see you again.");
                isExit = true;
                break;
            case "list":
                ui.listAllTasks(taskList);
                break;
            case "mark": {
                int number = Integer.parseInt(split[1]) - 1;
                taskList.mark(number);
                ui.listAllTasks(taskList);
                break;
            }
            case "unmark": {
                int number = Integer.parseInt(split[1]) - 1;
                taskList.unmark(number);
                ui.listAllTasks(taskList);
                break;
            }
            case "todo": {
                taskList.addTodo(split[1]);
                break;
            }
            case "deadline": {
                split = userLine.split(" ");
                if (split.length < 4) {
                    ui.error("Invalid format");
                    break;
                }
                taskList.addDeadline(split[1], split[3]);
                break;
            }
            case "event": {
                split = userLine.split(" ");
                if (split.length < 6) {
                    ui.error("Invalid format");
                    break;
                }
                taskList.addEvent(split[1], split[3], split[5]);
                break;
            }
            case "delete": {
                if (split.length != 2) {
                    ui.error("Invalid format");
                }
                int itemIndex;
                try {
                    itemIndex = Integer.parseInt(split[1]);
                    ui.removeItemMessage(itemIndex);
                    taskList.delete(itemIndex - 1);
                    ui.message("Removal successful. New list:");
                    ui.listAllTasks(taskList);
                } catch (IndexOutOfBoundsException e) {
                    ui.error("Item does not exist");
                } catch (NumberFormatException e) {
                    ui.error(split[1] + " is not a number");
                }
                break;
            }
            default:
                ui.error("Command not found");
            }
            Storage.store(taskList);
        }
    }
}
