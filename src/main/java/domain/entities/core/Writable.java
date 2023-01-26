package domain.entities.core;


public interface Writable extends ThrowingWritable {
    @Override
    void writeln(Object content);

    @Override
    void write(Object content);

    @Override
    void clear();
}