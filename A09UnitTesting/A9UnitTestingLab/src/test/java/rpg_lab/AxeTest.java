package rpg_lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AxeTest {
    private static final int AXE_ATTACK = 10;
    private static final int AXE_DURABILITY = 10;
    private static final int DUMMY_HEALTH = 10;
    private static final int DUMMY_XP = 10;
    private static final int EXPECTED_DURABILITY = AXE_DURABILITY - 1;
    private static final String DUMMY_LOOT = "Gold";

    private Weapon axe;
    private Target dummy;

    @Before
    public void initializeTestObjects() {
        this.axe = new Axe(AXE_ATTACK, AXE_DURABILITY);
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_XP,DUMMY_LOOT);
    }

    @Test
    public void weaponAttacksLosesDurability() {
        //Act
        this.axe.attack(dummy);

        //Assert
        Assert.assertEquals("Wrong Durability",EXPECTED_DURABILITY, this.axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class) // Assert
    public void brokenWeaponCantAttack() {
        //Act
        this.axe.attack(dummy);
        this.axe.attack(dummy);
    }
}
