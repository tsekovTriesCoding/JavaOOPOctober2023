package rpg_lab;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class HeroTest {
    private static final String HERO_NAME = "John";
    private static final int TARGET_XP = 10;
    private static final String TARGET_LOOT = "Gold";

    @Test
    public void attackGainsExperienceIfTargetIsDead() {
        Target fakeTarget = Mockito.mock(Target.class);
        Mockito.when(fakeTarget.getHealth()).thenReturn(0);
        Mockito.when(fakeTarget.giveExperience()).thenReturn(10);
        Mockito.when(fakeTarget.isDead()).thenReturn(true);

        Weapon fakeWeapon = Mockito.mock(Weapon.class);
        Mockito.when(fakeWeapon.getAttackPoints()).thenReturn(10);
        Mockito.when(fakeWeapon.getDurabilityPoints()).thenReturn(0);

        Hero hero = new Hero(HERO_NAME, fakeWeapon);
        hero.attack(fakeTarget);
        Assert.assertEquals("Wrong experience", TARGET_XP, hero.getExperience());
    }

    @Test
    public void heroGetsLootIfTargetDies() {
        Target fakeTarget = Mockito.mock(Target.class);
        Mockito.when(fakeTarget.isDead()).thenReturn(true);
        Mockito.when(fakeTarget.dropLoot()).thenReturn(TARGET_LOOT);

        Weapon fakeWeapon = Mockito.mock(Weapon.class);
        Mockito.when(fakeWeapon.getAttackPoints()).thenReturn(10);
        Mockito.when(fakeWeapon.getDurabilityPoints()).thenReturn(0);

        Hero hero = new Hero(HERO_NAME, fakeWeapon);
        hero.getLoot(fakeTarget);
        Assert.assertEquals("Wrong loot", TARGET_LOOT, hero.getLoot().get(hero.getLoot().size() - 1));
    }
}
