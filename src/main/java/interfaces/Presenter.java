package interfaces;

public interface Presenter {
    void handleInput(String string);
    void registerListener(CommandEventListener listener);
}
