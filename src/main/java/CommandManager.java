public class CommandManager {
    private static CommandQueue queue = new CommandQueue();
    public static void createCommand (String input) throws DukeException {
        switch (input){
            default : {
                if (input.matches("mark+ [0-9]+")) {
                    queue.add(new Mark(input));
                } else if (input.matches("unmark+ [0-9]+")) {
                    queue.add(new Unmark(input));
                } else if (input.matches("^deadline\\s.*$") || input.matches("^event\\s.*$") || input.matches("^todo\\s.*$")) {
                    if (input.split(" ").length < 2) {
                        throw new InsufficientAruments("☹ OOPS!!! The description of a " +
                                input.split(" ")[0] + " cannot be empty.");
                    } else {
                        queue.add(new Store(input));
                    }
                } else {
                    throw new UnknownCommand("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                break;
            }
            case "list": {
                queue.add(new List(input));
                break;
            }
            case "bye" : {
                queue.add(new Bye(input));
                break;
            }
        }
    }
    public static void executeQueue (User user){
        queue.executeQueue(user);
    }
}
