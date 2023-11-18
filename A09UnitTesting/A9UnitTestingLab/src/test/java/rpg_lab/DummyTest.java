package rpg_lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DummyTest {
    private static final int AXE_ATTACK = 10;
    private static final int AXE_DURABILITY = 10;
    private static final int DUMMY_HEALTH = 20;
    private static final int DUMMY_XP = 10;
    private static final int EXPECTED_HEALTH = DUMMY_HEALTH - AXE_ATTACK;
    private static final String DUMMY_LOOT = "Gold";

    private Weapon axe;
    private Target dummy;

    @Before
    public void initializeTestObjects() {
        this.axe = new Axe(AXE_ATTACK, AXE_DURABILITY);
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_XP,DUMMY_LOOT);
    }

    @Test
    public void dummyLosesHealthWhenAttacked() {
        this.dummy.takeAttack(AXE_ATTACK);

        Assert.assertEquals("Wrong health", EXPECTED_HEALTH, this.dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void dummyCantTakeDamageIfDead() {
        this.dummy.takeAttack(this.axe.getAttackPoints());
        this.dummy.takeAttack(this.axe.getAttackPoints());
        this.dummy.takeAttack(this.axe.getAttackPoints());
    }

    @Test
    public void dummyGivesXp() {
        this.dummy.takeAttack(this.axe.getAttackPoints());
        this.dummy.takeAttack(this.axe.getAttackPoints());
        this.dummy.giveExperience();
    }

    @Test(expected = IllegalStateException.class)
    public void aliveDummyCantGiveXp() {
        this.dummy.takeAttack(this.axe.getAttackPoints());
        this.dummy.giveExperience();
    }

    @Test()
    public void targetDropsLootWhenDead() {
        this.dummy.takeAttack(this.axe.getAttackPoints());
        this.dummy.takeAttack(this.axe.getAttackPoints());
        this.dummy.dropLoot();
    }

    @Test(expected = IllegalStateException.class)
    public void aliveTargetCantDropLoot() {
        this.dummy.takeAttack(this.axe.getAttackPoints());
        this.dummy.dropLoot();
    }

}
