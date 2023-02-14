package duke;

public class Ui {

    /**
     * Returns a String that tells the user save file is unavailable and a new List will be used
     *
     * @return String
     */
    public String savedFileNotFound() {
        return "[Saved file not found, new List initialised~]";
    }

    /**
     * Returns a String that tells the user save file is available and existing List will be used
     *
     * @return String
     */
    public String savedFileFound() {
        return "[Saved file found, saved List initialised~]";
    }
}
