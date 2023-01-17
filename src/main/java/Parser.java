public class Parser {

    public Parser() {
    }

    public Command parse(String input) throws DukeException {
        input = input.trim();
        String[] splitInput = input.split(" ");
        String command = splitInput[0];
        if(command.equals("bye")) {
            return new Bye();
        }
        else if(command.equals("list")) {
            return new ListCommand();
        }
        else if(command.equals("mark")) {
            int inputIndex = Integer.parseInt(splitInput[1]);
            return new Mark(inputIndex);
        }
        else if(command.equals("unmark")) {
            int inputIndex = Integer.parseInt(splitInput[1]);
            return new UnMark(inputIndex);
        }
        else if(command.equals("todo")) {
            int startIndex = command.length();
            if(startIndex >= input.length()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String taskName = input.substring(startIndex);
            Task newTask = new ToDos(taskName);
            return new TodoCommand(newTask);
        }
        else if(command.equals("deadline")) {
            int startIndex = command.length();
            if(startIndex >= input.length()) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            String taskNameWithDeadline = input.substring(startIndex);
            String[] splitTaskAndDeadline = taskNameWithDeadline.split("/");
            if(splitTaskAndDeadline.length < 2) {
                throw new DukeException("☹ OOPS!!! The deadline must be specified.");
            }
            Task newTask = new Deadlines(splitTaskAndDeadline[0], splitTaskAndDeadline[1]);
            return new DeadlineCommand(newTask);
        }
        else if(command.equals("event")) {
            int startIndex = command.length();
            if(startIndex >= input.length()) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            String taskNameWithDeadline = input.substring(startIndex);
            String[] splitTaskAndDeadline = taskNameWithDeadline.split("/");
            if(splitTaskAndDeadline.length < 3) {
                throw new DukeException("☹ OOPS!!! The start and end both must be specified.");
            }
            Task newTask = new Events(splitTaskAndDeadline[0], splitTaskAndDeadline[1], splitTaskAndDeadline[2]);
            return new EventsCommand(newTask);
        }
        else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
