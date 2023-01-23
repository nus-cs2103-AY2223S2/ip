public class Event extends Task {
    String fromDate;
    String toDate;

    public static Event create(String commandInput) throws TaskNameNotSpecified, EventFromToNotSpecified {
        String[] parseInfo = parseCmd(commandInput);
        return new Event(parseInfo[0], parseInfo[1], parseInfo[2], false);
    }

    public Event(String taskName, String fromDate, String toDate, boolean isDone) {
        super(taskName, "E");
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.completed = isDone;
    }

    public static String[] parseCmd(String commandInput) throws TaskNameNotSpecified, EventFromToNotSpecified {
        String taskName;
        String fromDate;
        String toDate;

        commandInput = commandInput.substring(6);
        int indexOfFrom = commandInput.indexOf("/from");
        int indexOfTo = commandInput.indexOf("/to");
        if (indexOfFrom == -1 || indexOfTo == -1) {
            throw new EventFromToNotSpecified("Event task requires keywords '/from' and '/to'");
        }

        taskName = commandInput.substring(0, indexOfFrom - 1);
        if (taskName.equals("")) {
            throw new TaskNameNotSpecified("The description of an event cannot be empty.");
        }

        fromDate = commandInput.substring(indexOfFrom + 6, indexOfTo - 1);
        if (fromDate.equals("")) {
            throw new EventFromToNotSpecified("from/to fields cannot be empty.");
        }

        try {
            toDate = commandInput.substring(indexOfTo + 4, commandInput.length());
           
        } catch (StringIndexOutOfBoundsException e) {
            throw new EventFromToNotSpecified("from/to fields cannot be empty.");
        }

        String[] parseInfo = {taskName, fromDate, toDate};
        return parseInfo;
    }

    @Override
    public String stringFields() {
        return " (from: " + this.fromDate + " to: " + toDate + ")";
    }
}