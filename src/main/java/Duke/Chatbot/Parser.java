package Duke.Chatbot;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

/**
 * Converts user input to make sense of user command
 */
public class Parser {
    public static final Map<String, BiPredicate<Chatbot, String>> commands =
            new HashMap<String, BiPredicate<Chatbot, String>>();
    private final Chatbot chatbot;
    private final Ui ui;

    /**
     * Hooks onto a chatbot and its ui
     *
     * @param chatbot chatbot to connect to
     * @param ui      ui of chatbot
     */
    public Parser(Chatbot chatbot, Ui ui) {
        this.chatbot = chatbot;
        this.ui = ui;
        setupCommands();
    }

    private void setupCommands() {
        addEndCommand();
        addListCommand();
        addMarkCommand();
        addUnmarkCommand();
        addTodoCommand();
        addDeadlineCommand();
        addEventCommand();
        addDeleteCommand();
        addDeleteAllCommand();
        addFindCommand();
    }

    private void addEndCommand() {
        commands.put(Messages.MESSAGE_END, (chatbot, args) -> {
            System.out.println(Messages.MESSAGE_END);
            chatbot.isActive = false;
            return true;
        });

    }

    private void addListCommand() {
        commands.put(Messages.MESSAGE_LIST, (chatbot, args) -> {
            chatbot.printTasks();
            return true;
        });
    }

    private void addMarkCommand() {
        commands.put(Messages.MESSAGE_MARK, (chatbot, args) -> {

            Integer index = -1;
            try {
                index = Integer.valueOf(args);
            } catch (NumberFormatException ex) {
                ui.showIndexNotSpecifiedError();

                return true;
            }
            index -= 1;

            chatbot.markAsComplete(index);


            return true;

        });
    }

    private void addUnmarkCommand() {
        commands.put(Messages.MESSAGE_UNMARK, (chatbot, args) -> {

            Integer index = -1;
            try {
                index = Integer.valueOf(args);
            } catch (NumberFormatException ex) {
                ui.showIndexError();
                return true;
            }

            index -= 1;
            chatbot.markAsIncomplete(index);
            return true;
        });
    }

    private void addTodoCommand() {
        commands.put(Messages.MESSAGE_TODO, (chatbot, args) -> {
            if (args.trim() == "") {
                ui.showEmptyDescriptionError();
                return true;
            }
            chatbot.addTask(TaskList.Tasktype.TODO, args.trim());
            return true;
        });
    }

    private void addDeadlineCommand() {
        commands.put(Messages.MESSAGE_DEADLINE, (chatbot, args) -> {
            String[] inputs = args.split("/by", 2);

            if (inputs.length != 2) {
                ui.showIncompleteDeadlineArgumentsError();
                return true;
            } else if (inputs[0].trim() == "" || inputs[1].trim() == "") {
                ui.showEmptyDescriptionError();
                return true;
            }

            chatbot.addTask(TaskList.Tasktype.DEADLINE, inputs[0].trim(), inputs[1].trim());

            return true;
        });
    }

    private void addEventCommand() {
        commands.put(Messages.MESSAGE_EVENT, (chatbot, args) -> {
            String[] inputs = args.split("(/from | /to)", 3);

            if (inputs.length != 3) {
                ui.showIncompleteEventArgumentsError();
                return true;
            } else if (inputs[0].trim() == "" || inputs[1].trim() == "" || inputs[2].trim() == "") {
                ui.showEmptyDescriptionError();
                return true;
            }

            chatbot.addTask(TaskList.Tasktype.EVENT, inputs[0].trim(), inputs[1].trim(), inputs[2].trim());

            return true;
        });
    }

    private void addDeleteCommand() {
        commands.put(Messages.MESSAGE_DELETE, (chatbot, args) -> {
            Integer index = -1;
            try {
                index = Integer.valueOf(args);
            } catch (NumberFormatException ex) {
                ui.showIndexError();
                return true;
            }
            index -= 1;
            chatbot.removeTask(index);

            return true;
        });
    }

    private void addDeleteAllCommand() {
        commands.put(Messages.MESSAGE_DELETE_ALL_DATA, (chatbot, args) -> {
            chatbot.removeAllTasks();
            return true;
        });
    }

    private void addFindCommand() {
        commands.put(Messages.MESSAGE_FIND, (chatbot, args) -> {
            if (args == "") {
                ui.showEmptyFindKeywordError();
            }
            chatbot.find(args);
            return true;
        });
    }

    /**
     * Checks if the Userinput is valid and calls the corresponding BiFunction
     * to execute the command
     *
     * @param nextLine Next line of User Input
     */
    public void parse(String nextLine) {
        //Assuming commands start with a space.
        String[] userCommand = nextLine.split(" ", 2);
        String upperCaseUserCommand = userCommand[0].toUpperCase();

        if (commands.containsKey(upperCaseUserCommand)) {
            boolean processedCommandState = commands.get(upperCaseUserCommand)
                    .test(chatbot, userCommand.length > 1 ? userCommand[1] : "");
            if (!processedCommandState) {
                chatbot.isActive = false;
                throw new RuntimeException();
            }
        } else {
            //Command not found.
            ui.showUnrecognisedCommandError();
        }
    }

}
