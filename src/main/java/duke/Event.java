package duke;
class Event extends Task {
    String fromDate;
    String toDate;

    public static Event create(String command) throws TaskNameNotSpecified, EventFromToNotSpecified {
        command = command.substring(6);
        
        int indexOfFrom = command.indexOf("/from");
        int indexOfTo = command.indexOf("/to");
        if (indexOfFrom == -1 || indexOfTo == -1) {
            throw new EventFromToNotSpecified("Event task requires keywords '/from' and '/to'");
        }

        String taskName = command.substring(0, indexOfFrom - 1);
        if (taskName.equals("")) {
            throw new TaskNameNotSpecified("The description of an event cannot be empty.");
        }

        String fromDate = command.substring(indexOfFrom + 6, indexOfTo);
        if (fromDate.equals("")) {
            throw new EventFromToNotSpecified("from/to fields cannot be empty.");
        }

        try {
            String toDate = command.substring(indexOfTo + 4, command.length());
            return new Event(taskName, fromDate, toDate);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EventFromToNotSpecified("from/to fields cannot be empty.");
        }
    }

    public Event(String taskName, String fromDate, String toDate) {
        super(taskName, "E");
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String stringFields() {
        return " (from: " + this.fromDate + " to: " + toDate + ")";
    }
}