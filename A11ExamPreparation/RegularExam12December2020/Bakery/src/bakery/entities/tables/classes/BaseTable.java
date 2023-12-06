package bakery.entities.tables.classes;

import bakery.common.ExceptionMessages;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseTable implements Table {
    private Collection<BakedFood> foodOrders;
    private Collection<Drink> drinkOrders;
    private int tableNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    public BaseTable(int tableNumber, int capacity, double pricePerPerson) {
        this.tableNumber = tableNumber;
        this.setCapacity(capacity);
        this.pricePerPerson = pricePerPerson;
        this.foodOrders = new ArrayList<>();
        this.drinkOrders = new ArrayList<>();
        this.numberOfPeople = 0;
        this.isReserved = false;
        this.price = 0;
    }

    @Override
    public int getTableNumber() {
        return this.tableNumber;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double getPricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void reserve(int numberOfPeople) {
        this.setNumberOfPeople(numberOfPeople);
        this.isReserved = true;
        this.price = this.pricePerPerson * numberOfPeople;
    }

    @Override
    public void orderFood(BakedFood food) {
        this.foodOrders.add(food);
    }

    @Override
    public void orderDrink(Drink drink) {
        this.drinkOrders.add(drink);
    }

    @Override
    public double getBill() {
        double foodsBill = this.foodOrders.stream().mapToDouble(BakedFood::getPrice).sum();
        double drinksBill = this.drinkOrders.stream().mapToDouble(Drink::getPrice).sum();

        return foodsBill + drinksBill + this.price;
    }

    @Override
    public void clear() {
        this.foodOrders.clear();
        this.drinkOrders.clear();
        this.isReserved = false;
        this.numberOfPeople = 0;
        this.price = 0;
    }

    @Override
    public String getFreeTableInfo() {
        String tableInfoBuilder = String.format("Table: %d", this.tableNumber) + System.lineSeparator() +
                String.format("Type: %s", this.getClass().getSimpleName()) + System.lineSeparator() +
                String.format("Capacity: %d", this.capacity) + System.lineSeparator() +
                String.format("Price per Person: %.2f", this.pricePerPerson);

        return tableInfoBuilder.trim();
    }

    private void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_CAPACITY);
        }

        this.capacity = capacity;
    }

    private void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }

        this.numberOfPeople = numberOfPeople;
    }
}
