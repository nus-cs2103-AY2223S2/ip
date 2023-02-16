package exceptions;

public class FileLoadException extends DukeException {
    public FileLoadException(String fileString) {
        super("WHAT?! its ur first time?! WELCOME WELCOME! \n\nIf this is a mistake, check your local read/write settings for \n        " 
                + fileString
                + "\n then reboot Duke again. \n\nOtherwise, continue on with a new list! Enjoy!");
    }
}
