package magicGame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MagicianTests {
    private Magician magician;

    @Before
    public void initializeMagician() {
        magician = new Magician("Dark Magician", 150);
    }

    @Test
    public void testGetUSerNameShouldReturnUsername() {
        Assert.assertEquals("Dark Magician", magician.getUsername());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnNullUsername() {
        magician = new Magician(null, 150);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnEmptyUsername() {
        magician = new Magician("", 150);
    }

    @Test
    public void testGetHealthShouldReturnHealth() {
        Assert.assertEquals(150, magician.getHealth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowExceptionWhenHealthIsBelowZero() {
        magician = new Magician("Someone", -100);
    }

    @Test
    public void testAddMagicShouldAddMagicToMagician() {
        Assert.assertTrue(magician.getMagics().isEmpty());
        magician.addMagic(new Magic("Spell", 100));
        Assert.assertEquals(1, magician.getMagics().size());
    }

    @Test(expected = NullPointerException.class)
    public void testAddMagicShouldThrowExceptionIfMagicIsNull() {
        magician.addMagic(null);
    }


    @Test
    public void testGetMagicsShouldReturnUnmodifiableListOfMagics() {
        magician.addMagic(new Magic("Spell", 100));
        List<Magic> magics = magician.getMagics();
        Assert.assertEquals(1, magics.size());

        try {
            magics.add(new Magic("Spell", 100));
        } catch (UnsupportedOperationException ignored) {

        }

        Assert.assertEquals(1, magics.size());
    }

    @Test
    public void testTakeDamageShouldDoDamageToMagician() {
        Assert.assertEquals(150, magician.getHealth());
        magician.takeDamage(100);
        Assert.assertEquals(50, magician.getHealth());
    }

    @Test
    public void testTakeDamageShouldDoDamageToMagicianAndIfHealthGoesBelowZeroItShouldBeSetToZero() {
        Assert.assertEquals(150, magician.getHealth());
        magician.takeDamage(151);
        Assert.assertEquals(0, magician.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void testTakeDamageShouldThrowExceptionIfHealthIsBelowOrEqualToZero() {
        magician.takeDamage(200);
        magician.takeDamage(2);
    }

    @Test
    public void testGetMagicShouldReturnExistingMagic() {
        Magic magic = new Magic("Spell", 100);
        magician.addMagic(magic);
        Magic spell = magician.getMagic("Spell");
        Assert.assertEquals(magic, spell);
    }

    @Test
    public void testGetMagicShouldReturnNullIfMagicIsMissing() {
        Magic spell = magician.getMagic("Spell");
        Assert.assertNull(spell);
    }

    @Test
    public void testRemoveMagicShouldRemoveExistingMagicAndReturnTrue() {
        Magic magic = new Magic("Spell", 100);
        magician.addMagic(magic);
        Assert.assertEquals(1, magician.getMagics().size());

        boolean isRemoved = magician.removeMagic(magic);
        Assert.assertTrue(isRemoved);
        Assert.assertEquals(0, magician.getMagics().size());
    }

    @Test
    public void testRemoveMagicShouldReturnFalseOnMissingMagic() {
        magician.addMagic(new Magic("Dark Attack", 245));
        Magic magic = new Magic("Spell", 100);
        Assert.assertEquals(1, magician.getMagics().size());

        boolean isRemoved = magician.removeMagic(magic);
        Assert.assertFalse(isRemoved);
        Assert.assertEquals(1, magician.getMagics().size());
    }


}
