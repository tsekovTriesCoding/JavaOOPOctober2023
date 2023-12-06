package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HeroRepositoryTests {
    private HeroRepository heroRepository;

    @Before
    public void init() {
        this.heroRepository = new HeroRepository();
    }

    @Test
    public void testGetCountShouldReturnTheCountOfHeroesIntHeRepository() {
        Assert.assertEquals(0, this.heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testCreateShouldThrowExceptionOnNullHero() {
        this.heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateShouldThrowExceptionOnHeroWithGivenNameExists() {
        Hero hero1 = new Hero("Peter", 10);
        Hero hero2 = new Hero("Peter", 10);
        this.heroRepository.create(hero1);
        this.heroRepository.create(hero2);
    }

    @Test
    public void testCreateShouldAddHeroToRepository() {
        Hero hero = new Hero("Peter", 10);
        Assert.assertEquals(0, this.heroRepository.getCount());

        this.heroRepository.create(hero);
        Assert.assertEquals(1, this.heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveShouldThrowExceptionOnNullName() {
        this.heroRepository.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveShouldThrowExceptionOnEmptyName() {
        this.heroRepository.remove("");
    }

    @Test
    public void testRemoveShouldNotRemoveMissingHero() {
        Hero hero = new Hero("Peter", 10);
        this.heroRepository.create(hero);
        Assert.assertEquals(1, this.heroRepository.getCount());

        boolean isRemoved = this.heroRepository.remove("MissingHero");
        Assert.assertEquals(1, this.heroRepository.getCount());
        Assert.assertFalse(isRemoved);
    }

    @Test
    public void testRemoveShouldRemoveHero() {
        Hero hero = new Hero("Peter", 10);
        this.heroRepository.create(hero);
        Assert.assertEquals(1, this.heroRepository.getCount());

        boolean isRemoved = this.heroRepository.remove("Peter");
        Assert.assertEquals(0, this.heroRepository.getCount());
        Assert.assertTrue(isRemoved);
    }

    @Test
    public void testGetHeroWithHighestLevelShouldReturnHeroWithHighestLevel() {
        Hero hero1 = new Hero("Peter", 10);
        Hero hero2 = new Hero("Exzodia", 100);
        Hero hero3 = new Hero("Naruto", 85);

        this.heroRepository.create(hero1);
        this.heroRepository.create(hero2);
        this.heroRepository.create(hero3);

        Hero heroWithHighestLevel = this.heroRepository.getHeroWithHighestLevel();
        Assert.assertEquals(hero2, heroWithHighestLevel);
    }

    @Test
    public void testGetHeroWithHighestLevelShouldReturnNull() {
        Hero heroWithHighestLevel = this.heroRepository.getHeroWithHighestLevel();
        Assert.assertNull(heroWithHighestLevel);
    }

    @Test
    public void testGetHeroShouldReturnHeroWithGivenName() {
        Hero hero1 = new Hero("Peter", 10);
        Hero hero2 = new Hero("Exzodia", 100);
        Hero hero3 = new Hero("Naruto", 85);
        this.heroRepository.create(hero1);
        this.heroRepository.create(hero2);
        this.heroRepository.create(hero3);

        Hero hero = this.heroRepository.getHero("Peter");
        Assert.assertEquals(hero, hero1);
    }

    @Test
    public void testGetHeroShouldReturnNullOnMissingHero() {
        Hero hero1 = new Hero("Peter", 10);
        Hero hero2 = new Hero("Exzodia", 100);
        Hero hero3 = new Hero("Naruto", 85);
        this.heroRepository.create(hero1);
        this.heroRepository.create(hero2);
        this.heroRepository.create(hero3);

        Hero hero = this.heroRepository.getHero("Missing");
        Assert.assertNull(hero);
    }

    @Test
    public void testGetHeroesShouldReturnCollectionOfHeroesInRepository() {
        Hero hero1 = new Hero("Peter", 10);
        Hero hero2 = new Hero("Exzodia", 100);
        Hero hero3 = new Hero("Naruto", 85);
        this.heroRepository.create(hero1);
        this.heroRepository.create(hero2);
        this.heroRepository.create(hero3);

        List<Hero> expectedHeroes = List.of(hero1, hero2, hero3);
        List<Hero> actualHeroesList = new ArrayList<>(this.heroRepository.getHeroes());

        for (int i = 0; i < expectedHeroes.size(); i++) {
            Assert.assertEquals(expectedHeroes.get(i), actualHeroesList.get(i));
        }
    }

}

