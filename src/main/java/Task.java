abstract class Task {
    protected String description;

    protected boolean isDone;

    public Task(String description) throws DescriptionException{
        this.description = description;
        this.isDone = false;
        if (this.isEmpty()) {
            throw new DescriptionException();
        }
    }

    public boolean isEmpty() {
        return this.description == null || this.description.trim().isEmpty();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " | " + this.description;
    }

    public static Task commandToTask(String strTask) throws CommandException, StringIndexOutOfBoundsException, DescriptionException {
        if (strTask.startsWith(Commands.deadline.label)) {
            String[] words = strTask.split(" /", 2);
            if (words.length == 2 && words[1].startsWith("by ")){
                return new Deadline(words[0].substring(9), words[1].substring(3));
            }

        } else if (strTask.startsWith(Commands.todo.label)) {
            return new ToDo(strTask.substring(5));

        } else if (strTask.startsWith(Commands.event.label)) {
            String[] words = strTask.split(" /", 3);
            if (words.length == 3 && words[1].startsWith("from ") && words[2].startsWith("to ")) {
                return new Event(words[0].substring(6), words[1].substring(5), words[2].substring(3));
            }
        }
        throw new CommandException();
    }

    public static Task strToTask(String strTask) throws CommandException, DescriptionException {
        Task result;
        String[] separatedStr = strTask.split(" \\| ");
        if (strTask.startsWith("T")) {
            result = new ToDo(separatedStr[2]);
        } else if (strTask.startsWith("D")) {
            result = new Deadline(separatedStr[2], separatedStr[3]);
        } else if (strTask.startsWith("E")) {
            String[] separatedBy = separatedStr[3].split("-");
            result = new Event(separatedStr[2], separatedBy[0], separatedBy[1]);
        } else {
            throw new CommandException();
        }
        try {
            if (separatedStr[1].equals("X")) {
                result.markDone();
            }
            return result;
        } catch (Exception e) {
            throw new CommandException();
        }
    }
}
