package dataacess;

import java.util.List;
import java.util.Optional;

public interface BaseRepository <T,I>{


    Optional<T> insert(T entitiy);
    Optional<T> getById(I id);
    List<T> getALl();
    Optional<T> update(T entity);
    void deleteById(I id);

}
