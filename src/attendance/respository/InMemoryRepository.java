package attendance.repository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryRepository<T, ID> implements Repository<T, ID> {

    private final Map<ID, T> storage = new HashMap<>();
    private final Function<T, ID> idExtractor;

    // Lambda ile id çıkarıcı fonksiyonu alıyoruz
    public InMemoryRepository(Function<T, ID> idExtractor) {
        this.idExtractor = idExtractor;
    }

    @Override
    public void save(T entity) {
        ID id = idExtractor.apply(entity); // Lambda burada kullanıldı
        storage.put(id, entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(ID id) {
        storage.remove(id);
    }

    // Generic + Lambda ile filtreleme fonksiyonu
    public List<T> findAllWhere(Function<T, Boolean> predicate) {
        return storage.values().stream()
                .filter(predicate::apply)
                .collect(Collectors.toList());
    }
}
