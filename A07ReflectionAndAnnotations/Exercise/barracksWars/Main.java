package A07ReflectionAndAnnotations.Exercise.barracksWars;

import A07ReflectionAndAnnotations.Exercise.barracksWars.interfaces.Repository;
import A07ReflectionAndAnnotations.Exercise.barracksWars.interfaces.Runnable;
import A07ReflectionAndAnnotations.Exercise.barracksWars.interfaces.UnitFactory;
import A07ReflectionAndAnnotations.Exercise.barracksWars.core.Engine;
import A07ReflectionAndAnnotations.Exercise.barracksWars.core.factories.UnitFactoryImpl;
import A07ReflectionAndAnnotations.Exercise.barracksWars.data.UnitRepository;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }
}
