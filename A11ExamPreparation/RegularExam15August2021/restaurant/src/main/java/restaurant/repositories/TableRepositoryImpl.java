package restaurant.repositories;

import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.TableRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TableRepositoryImpl implements TableRepository<Table> {
    private Collection<Table> entities;

    public TableRepositoryImpl() {
        this.entities = new ArrayList<>();
    }

    @Override
    public Collection<Table> getAllEntities() {
        return Collections.unmodifiableCollection(this.entities);
    }

    @Override
    public void add(Table entity) {
        this.entities.add(entity);
    }

    @Override
    public Table byNumber(int number) {
        return this.entities
                .stream()
                .filter(t -> number == t.getTableNumber())
                .findFirst()
                .orElse(null);
    }

}
