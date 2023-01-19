public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
     }

    public static Task of(String s) throws LuluException {
        String[] task = s.split(" ");
        String type = task[0];
        switch (type) {
            case "deadline":
                if (task.length == 1) {
                    throw new LuluException("(=✖ ᆺ ✖=) The description of a deadline cannot be empty.");
                }
                String appendDeadline = s.substring(9);
                String[] deadlineDetails = appendDeadline.split("/by");
                if (deadlineDetails.length == 1) {
                    throw new LuluException("(=✖ ᆺ ✖=) Please include a deadline using /by");
                }
                return new Deadline(deadlineDetails[0], deadlineDetails[1]);
            case "event":
                if (task.length == 1) {
                    throw new LuluException("(=✖ ᆺ ✖=) The description of a event cannot be empty.");
                }
                String appendEvent = s.substring(6);
                String[] eventDetails = appendEvent.split("/from");
                if (eventDetails.length == 1) {
                    throw new LuluException("(=✖ ᆺ ✖=) Please include a timing using /from and /to");
                }
                String[] toFrom = eventDetails[1].split("/to");
                if (toFrom.length == 1) {
                    throw new LuluException("(=✖ ᆺ ✖=) You included /from but missed out /to");
                }
                return new Event(eventDetails[0], toFrom[0], toFrom[1]);
            default:
                if (type.compareTo("todo") != 0) {
                    throw new LuluException("(=✖ ᆺ ✖=) That's not a valid command.");
                }
                if (task.length == 1) {
                    throw new LuluException("(=✖ ᆺ ✖=) The description of a todo cannot be empty.");
                }
                String description = s.substring(5);
                return new Todo(description);
        }
    }
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }
}
