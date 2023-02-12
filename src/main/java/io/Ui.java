package io;

/**
 * User Interface
 */
public interface Ui {
    /**
     * Manages formatting and output of reply to the ui
     * @param reply Message to reply with
     */
    public void showReply(String reply);

    /**
     * Starts the ui and performs any preliminary actions
     */
    public Ui launch();

    /**
     * Obtains user input from the ui
     * @return User input
     */
    public String getInput();    
}
