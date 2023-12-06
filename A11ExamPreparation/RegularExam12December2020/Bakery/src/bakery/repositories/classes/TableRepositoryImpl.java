package bakery.repositories.classes;

import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.TableRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TableRepositoryImpl implements TableRepository<Table> {
    private Map<Integer, Table> models;

    public TableRepositoryImpl() {
        this.models = new HashMap<>();
    }

    @Override
    public Collection<Table> getAll() {
        return Collections.unmodifiableCollection(this.models.values());
    }

    @Override
    public void add(Table table) {
        this.models.put(table.getTableNumber(), table);
    }

    @Override
    public Table getByNumber(int number) {
        return this.models.get(number);
    }
}
