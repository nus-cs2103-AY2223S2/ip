package duke;

public class Parser {
    public static Task parseFile(String userInput) {
        String recordType = userInput.substring(0, 3);
        String recordStatus = userInput.substring(3, 6);
        String recordDescription = userInput.substring(7);
        Task item = null;

        switch (recordType) {
            case "[T]":
                item = new Todo(recordDescription);
                break;
            case "[D]":
                String byDate = recordDescription.substring(recordDescription.indexOf(":") + 2,
                        recordDescription.length() - 1);
                recordDescription = recordDescription.substring(0, recordDescription.indexOf("("));
                item = new Deadline(recordDescription, new TimeConvertor(byDate, "MMM dd yyyy hh:mma"));
                break;
            case "[E]":
                String fromDate = recordDescription.substring(recordDescription.indexOf("from:") + 6,
                        recordDescription.indexOf("to:") - 1);
                String toDate = recordDescription.substring(recordDescription.indexOf("to:") + 4,
                        recordDescription.length() - 1);
                recordDescription = recordDescription.substring(0, recordDescription.indexOf("("));
                item = new Event(recordDescription, new TimeConvertor(fromDate, "MMM dd yyyy hh:mma"),
                        new TimeConvertor(toDate, "MMM dd yyyy hh:mma"));
                break;
        }
        if (recordStatus.equals("[X]")) {
            item.setIsDone();
        }
        return item;
    }

    public static boolean parseInput(TaskList list, String userInput)
            throws taskNotExist, missingNumber, missingDescription {
        int chosenTask;
        switch (userInput.split("\\s+")[0]) {
            case "bye":
                list.Bye();
                return false;
            case "list":
                list.printList();
                return false;
            case "mark":
                if (userInput.indexOf(" ") == -1) {
                    throw new missingNumber("mark");
                }
                chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
                list.mark(chosenTask);
                return true;
            case "unmark":
                if (userInput.indexOf(" ") == -1) {
                    throw new missingNumber("unmark");
                }
                chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
                list.unmark(chosenTask);
                return true;
            case "todo":
                if (userInput.indexOf(" ") == -1) {
                    throw new missingDescription("todo");
                }
                String todo_descrip = userInput.substring(userInput.indexOf(" ")).trim();
                list.todo(todo_descrip);
                return true;
            case "deadline":
                if (userInput.indexOf(" ") == -1) {
                    throw new missingDescription("deadline");
                }
                if (userInput.indexOf("/") == -1) {
                    throw new missingDescription("deadline");
                }
                String dd_full = userInput.substring(userInput.indexOf(" ")).trim();
                String dd_descrip = dd_full.split("/")[0];
                String dd_date = dd_full.split("/")[1].substring(dd_full.split("/")[1].indexOf(" ")).trim();
                list.deadline(dd_descrip, new TimeConvertor(dd_date));
                return true;
            case "event":
                if (userInput.indexOf(" ") == -1) {
                    throw new missingDescription("event");
                }
                if (userInput.indexOf("/") == -1) {
                    throw new missingDescription("event");
                }
                String event_full = userInput.substring(userInput.indexOf(" ")).trim();
                String event_descrip = event_full.split("/")[0];
                String event_from = event_full.split("/")[1].substring(event_full.split("/")[1].indexOf(" "))
                        .trim();
                String event_to = event_full.split("/")[2].substring(event_full.split("/")[2].indexOf(" "))
                        .trim();
                list.event(event_descrip, new TimeConvertor(event_from), new TimeConvertor(event_to));
                return true;
            case "delete":
                if (userInput.indexOf(" ") == -1) {
                    throw new missingNumber("delete");
                }
                chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
                list.delete(chosenTask);
                return true;
            case "check":
                if (userInput.indexOf("/") == -1) {
                    throw new missingNumber("check");
                }
                String checkDeadline = userInput.split("/")[1];
                list.check(checkDeadline);
                return false;
            default:
                System.out.println("Oh no, I am not sure what that means, could you try again?");
                return false;
        }
    }
}
