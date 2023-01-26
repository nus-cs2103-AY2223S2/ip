package domain.entities.core;

public interface StringReadable {
    /**
     * Read the next line from the interface. Will employ a blocking read.
     * @return the next line from the interface.
     */
    public String nextLine();
}
