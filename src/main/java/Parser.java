public class Parser {
    public Parser() {}

    public static Command parse(String fullCommand) {
        String[] fullCommandArr = fullCommand.replaceFirst(" ", "#").split("#");
        String command = fullCommandArr[0];
        String content = "";
        if (fullCommandArr.length > 1) {
            content = fullCommandArr[1];
        }
        switch (command) {
        case "bye":
            return new EndCommand();
        case "list":
            return new PrintListCommand();
        case "mark":
            int markIdx = Integer.parseInt(content);
            return new MarkCommand(markIdx);
        case "unmark":
            int unmarkIdx = Integer.parseInt(content);
            return new MarkCommand(unmarkIdx);
        case "todo":
            return new TodoCommand(content);
        case "deadline":
            return new DeadlineCommand(content);
        case "event":
            return new EventCommand(content);
        case "delete":
            int deleteIdx = Integer.parseInt(content);
            return new DeleteCommand(deleteIdx);
        case "guide":
            return new GuideCommand();
        default:
            return new UnknownCommand();
        }
    }
}
