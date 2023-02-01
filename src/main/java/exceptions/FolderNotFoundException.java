package exceptions;

/**
 * Represents the FolderNotFoundException that is thrown whenever a directory cannot be located and extends from
 * the Exception class.
 *
 * @author MrTwit99
 * @since 2023-02-02
 */
public class FolderNotFoundException extends Exception {
    /**
     * Returns a FolderNotFoundException object that is thrown whenever a directory cannot be located.
     *
     * @param str String message that is to be printed with the error.
     */
    public FolderNotFoundException(String str) {
        super(str);
    }
}
