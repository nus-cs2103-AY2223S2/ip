package duke.ui;

/**
 * Deals with interactions with the user
 */
public class Ui {
    /**
     * Prints welcome message.
     */
    public String showWelcomeMessage() {
        return "I am the Ronnie buff bot. \nWhat can I do for you?";
    }

    /**
     * Prints farewell message when user exits.
     */
    public String farewellMessage() {
        return "Bye. Make sure to consume your protein!!\n "
                + "You can now exit the session by clicking the 'x' on the top right.";
    }
}

