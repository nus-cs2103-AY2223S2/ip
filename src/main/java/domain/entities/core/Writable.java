package domain.entities.core;


import core.exceptions.WriteException;

public interface Writable extends ThrowingWritable {
    @Override
    void writeln(Object content);

    @Override
    void write(Object content);

    @Override
    void clear();
}