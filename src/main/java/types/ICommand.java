package types;

public interface ICommand {
    void take(String s);

    boolean canTake(String s);
}
