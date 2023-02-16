package io;

/**
 * User Interface
 */
public interface Ui {
    /**
     * Manages formatting and output of reply to the ui
     *
     * @param reply Message to reply with
     */
    void showReply(String reply);

    /**
     * Manages formatting and output of error messages to the ui
     *
     * @param errorMsg Error message
     */
    void showError(String errorMsg);
}
