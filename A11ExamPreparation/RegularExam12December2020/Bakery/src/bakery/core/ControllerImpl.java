package bakery.core;

import bakery.common.ExceptionMessages;
import bakery.common.OutputMessages;
import bakery.common.enums.BakedFoodType;
import bakery.common.enums.DrinkType;
import bakery.common.enums.TableTYpe;
import bakery.core.interfaces.Controller;
import bakery.entities.bakedFoods.classes.Bread;
import bakery.entities.bakedFoods.classes.Cake;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.classes.Tea;
import bakery.entities.drinks.classes.Water;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.classes.InsideTable;
import bakery.entities.tables.classes.OutsideTable;
import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.*;

import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private FoodRepository<BakedFood> foodRepository;
    private DrinkRepository<Drink> drinkRepository;
    private TableRepository<Table> tableRepository;
    private double totalIncome;


    public ControllerImpl(FoodRepository<BakedFood> foodRepository,
                          DrinkRepository<Drink> drinkRepository,
                          TableRepository<Table> tableRepository) {
        this.foodRepository = foodRepository;
        this.drinkRepository = drinkRepository;
        this.tableRepository = tableRepository;
        this.totalIncome = 0;
    }


    @Override
    public String addFood(String type, String name, double price) {
        if (foodRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST, type, name));
        }

        BakedFood food = null;
        BakedFoodType bakedFoodType = BakedFoodType.valueOf(type);

        switch (bakedFoodType) {
            case Bread:
                food = new Bread(name, price);
                break;
            case Cake:
                food = new Cake(name, price);
        }

        this.foodRepository.add(food);

        return String.format(OutputMessages.FOOD_ADDED, name, type);
    }

    @Override
    public String addDrink(String type, String name, int portion, String brand) {
        if (this.drinkRepository.getByNameAndBrand(name, brand) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST, type, name));
        }

        Drink drink = null;
        DrinkType drinkType = DrinkType.valueOf(type);

        switch (drinkType) {
            case Tea:
                drink = new Tea(name, portion, brand);
                break;
            case Water:
                drink = new Water(name, portion, brand);
        }

        this.drinkRepository.add(drink);

        return String.format(OutputMessages.DRINK_ADDED, name, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        if (this.tableRepository.getByNumber(tableNumber) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.TABLE_EXIST, tableNumber));
        }

        Table table = null;
        TableTYpe tableType = TableTYpe.valueOf(type);

        switch (tableType) {
            case InsideTable:
                table = new InsideTable(tableNumber, capacity);
                break;
            case OutsideTable:
                table = new OutsideTable(tableNumber, capacity);
        }

        this.tableRepository.add(table);

        return String.format(OutputMessages.TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserveTable(int numberOfPeople) {
        Table table = this.tableRepository.getAll()
                .stream()
                .filter(t -> !t.isReserved() && numberOfPeople <= t.getCapacity())
                .findFirst()
                .orElse(null);

        if (table == null) {
            return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }

        table.reserve(numberOfPeople);

        return String.format(OutputMessages.TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderFood(int tableNumber, String foodName) {
        Table table = this.tableRepository.getByNumber(tableNumber);
        BakedFood food = this.foodRepository.getByName(foodName);

        String output;
        if (table == null) {
            output = String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        } else if (food == null) {
            output = String.format(OutputMessages.NONE_EXISTENT_FOOD, foodName);
        } else {
            table.orderFood(food);
            output = String.format(OutputMessages.FOOD_ORDER_SUCCESSFUL, tableNumber, foodName);
        }

        return output;
    }

    @Override
    public String orderDrink(int tableNumber, String drinkName, String drinkBrand) {
        Table table = this.tableRepository.getByNumber(tableNumber);
        Drink drink = this.drinkRepository.getByNameAndBrand(drinkName, drinkBrand);

        String output;
        if (table == null) {
            output = String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        } else if (drink == null) {
            output = String.format(OutputMessages.NON_EXISTENT_DRINK, drinkName, drinkBrand);
        } else {
            table.orderDrink(drink);
            output = String.format(OutputMessages.DRINK_ORDER_SUCCESSFUL, tableNumber, drinkName, drinkBrand);
        }

        return output;
    }

    @Override
    public String leaveTable(int tableNumber) {
        Table table = this.tableRepository.getByNumber(tableNumber);

        double bill = table.getBill();
        this.totalIncome += bill;
        table.clear();

        return String.format(OutputMessages.BILL, tableNumber, bill);
    }

    @Override
    public String getFreeTablesInfo() {
        String info = this.tableRepository.getAll()
                .stream()
                .filter(t -> !t.isReserved())
                .map(Table::getFreeTableInfo)
                .collect(Collectors.joining(System.lineSeparator()));

        return info.trim();
    }

    @Override
    public String getTotalIncome() {
        return String.format(OutputMessages.TOTAL_INCOME, this.totalIncome);
    }
}
