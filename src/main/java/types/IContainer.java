package types;

import java.util.List;

public interface IContainer<T> {
    void add(T record);

    void flush();

    void push(List<T> records);

    List<T> fetch();
}
