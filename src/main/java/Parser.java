public class Parser {
    private static final int INPUT_LENGTH_VALIDATE= 2;
    private static final int LENGTH_OF_INPUTS_FOR_DEADLINE = 2;
    private static final int LENGTH_OF_INPUTS_FOR_EVENTS = 2;
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
            this.validateInputNotEmpty(splitInput, "☹ OOPS!!! You must specify which task to mark");
            int inputIndex = Integer.parseInt(splitInput[1]);
            return new MarkCommand(inputIndex);
        }
        else if(command.equals("unmark")) {
            this.validateInputNotEmpty(splitInput, "☹ OOPS!!! You must specify which task to unmark");
            int inputIndex = Integer.parseInt(splitInput[1]);
            return new UnMarkCommand(inputIndex);
        }
        else if(command.equals("delete")) {
            this.validateInputNotEmpty(splitInput, "☹ OOPS!!! You must specify which task to delete");
            int inputIndex = Integer.parseInt(splitInput[1]);
            return new DeleteCommand(inputIndex);
        }
        else if(command.equals("todo")) {
            int startIndex = command.length();
            this.validateInputNotEmpty(splitInput, "☹ OOPS!!! The description of a todo cannot be empty.");
            String taskName = input.substring(startIndex);
            Task newTask = new ToDos(taskName);
            return new TodoCommand(newTask);
        }
        else if(command.equals("deadline")) {
            int startIndex = command.length();
            this.validateInputNotEmpty(splitInput, "☹ OOPS!!! The description of a deadline cannot be empty.");
            String taskNameWithDeadline = input.substring(startIndex);
            String[] splitTaskAndDeadline = taskNameWithDeadline.split("/");
            this.validateDateAndTimeInputs(splitTaskAndDeadline, LENGTH_OF_INPUTS_FOR_DEADLINE, taskNameWithDeadline);
            Task newTask = new Deadlines(splitTaskAndDeadline[0], splitTaskAndDeadline[1]);
            return new DeadlineCommand(newTask);
        }
        else if(command.equals("event")) {
            int startIndex = command.length();
            this.validateInputNotEmpty(splitInput, "☹ OOPS!!! The description of a event cannot be empty.");
            String taskNameWithDeadline = input.substring(startIndex);
            String[] splitTaskAndDeadline = taskNameWithDeadline.split("/");
            this.validateDateAndTimeInputs(splitTaskAndDeadline, LENGTH_OF_INPUTS_FOR_EVENTS, "☹ OOPS!!! The start and end both must be specified.");
            Task newTask = new Events(splitTaskAndDeadline[0], splitTaskAndDeadline[1], splitTaskAndDeadline[2]);
            return new EventsCommand(newTask);
        }
        else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    private void validateInputNotEmpty(String[] splitInput, String message) throws DukeException {
        if(splitInput.length < INPUT_LENGTH_VALIDATE) {
            throw new DukeException(message);
        }
    }
    
    private void validateDateAndTimeInputs(String[] splitTaskAndDeadline, int noOfDatesAndTime, String message) throws DukeException {
        if(splitTaskAndDeadline.length < noOfDatesAndTime) {
            throw new DukeException(message);
        }
    }
}
