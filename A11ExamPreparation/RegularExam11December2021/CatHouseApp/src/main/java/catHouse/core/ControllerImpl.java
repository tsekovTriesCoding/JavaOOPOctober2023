package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
        House house;

        switch (type) {
            case "LongHouse":
                house = new LongHouse(name);
                break;
            case "ShortHouse":
                house = new ShortHouse(name);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }

        this.houses.add(house);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy;

        switch (type) {
            case "Mouse":
                toy = new Mouse();
                break;
            case "Ball":
                toy = new Ball();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_TOY_TYPE);
        }

        this.toys.buyToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = this.toys.findFirst(toyType);

        if (toy == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND, toyType));
        }
        this.houses
                .stream()
                .filter(h -> houseName.equals(h.getName()))
                .findFirst()
                .ifPresent(h -> h.buyToy(toy));

        this.toys.removeToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat;

        switch (catType) {
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                break;
            case "ShorthairCat":
                cat = new ShorthairCat(catName, catBreed, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);
        }

        House house = getHouseByName(houseName);

        String result = ConstantMessages.UNSUITABLE_HOUSE;

        if (house.getClass().getSimpleName().equals("ShortHouse") && catType.equals("ShorthairCat")) {
            house.addCat(cat);
            result = String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
        } else if (house.getClass().getSimpleName().equals("LongHouse") && catType.equals("LonghairCat")) {
            house.addCat(cat);
            result = String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
        }

        return result;
    }

    private House getHouseByName(String houseName) {
        return this.houses
                .stream()
                .filter(h -> houseName.equals(h.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String feedingCat(String houseName) {
        House house = getHouseByName(houseName);

        house.feeding();
        return String.format(ConstantMessages.FEEDING_CAT, house.getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = getHouseByName(houseName);

        double sumOfToysPrices = house.getToys().stream().mapToDouble(Toy::getPrice).sum();
        double sumOfCatsPrices = house.getCats().stream().mapToDouble(Cat::getPrice).sum();
        return String.format(ConstantMessages.VALUE_HOUSE, houseName, sumOfToysPrices + sumOfCatsPrices);
    }

    @Override
    public String getStatistics() {
        return this.houses
                .stream()
                .map(House::getStatistics)
                .collect(Collectors.joining(System.lineSeparator()))
                .trim();
    }
}
