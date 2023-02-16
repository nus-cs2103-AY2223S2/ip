package duke.ui;


/**
 * Class of UI which gives the user a friendly interface.
 */
public class Ui {

    /**
     * Gets the initialization message of the chatbot.
     *
     * @return String the initialization message.
     */
    public static String getInitMessage() {
        String msg = "Hello and welcome! I'm Duke, a chatbot here to assist you. How can I help you today?\n\n"
                + "Here are the commands: \n"
                + "bye \n"
                + "list \n"
                + "mark {task} \n"
                + "unmark {task} \n"
                + "find {task} \n"
                + "delete {task} \n"
                + "todo {task} \n"
                + "deadline {task} /by {yyyy-mm-dd} {hhmm} \n"
                + "event {task} /from {datetime} /to {datetime}";
        return msg;
    }
}
