package A07ReflectionAndAnnotations.Exercise.barracksWars.core.factories;

import A07ReflectionAndAnnotations.Exercise.barracksWars.interfaces.Unit;
import A07ReflectionAndAnnotations.Exercise.barracksWars.interfaces.UnitFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"barracksWars.models.units.";

	@Override
	public Unit createUnit(String unitType) {
		try {
			Class unitClass = Class.forName(UNITS_PACKAGE_NAME + unitType);
			Constructor<Unit> constructor = unitClass.getDeclaredConstructor();
			return constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException ex) {
			System.out.println(ex.getMessage());
		}

		return null;
    }
}
