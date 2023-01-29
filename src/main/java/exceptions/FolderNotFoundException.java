package exceptions;

public class FolderNotFoundException extends Exception {
    public FolderNotFoundException (String str) {
        super(str);
    }
}