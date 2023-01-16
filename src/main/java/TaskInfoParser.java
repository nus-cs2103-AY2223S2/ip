public class TaskInfoParser {

    /**
     * This is a parser to extract information from the task to decide which object it is referring
     * to in the command input and returns it accordingly.
     * @param input command input from stdin
     * @return Task object specific to the command input string
     */
    public static Task obtainTask(String input) {
        String[] commands = input.split(" ");
        switch(commands[0]) {
            case "todo" :
                return ToDo.create(commands);
            case "deadline" :
                return Deadline.create(input);
            case "event" :
                return Event.create(input);
            default:
                break;
        }
        throw new UnknownCommandException(String.format("Fall to the Dark Side You must Not," +
                " for not know what %s means!!", commands[0]), null);
    }


}
