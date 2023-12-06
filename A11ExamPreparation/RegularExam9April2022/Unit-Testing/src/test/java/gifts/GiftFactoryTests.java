package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GiftFactoryTests {
    private GiftFactory giftFactory;

    @Before
    public void init() {
        this.giftFactory = new GiftFactory();
    }

    @Test
    public void testGetCountShouldReturnTheCountOfGifts() {
        Assert.assertEquals(0, this.giftFactory.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateGiftShouldThrowExceptionOnExistingGift() {
        Gift gift = new Gift("Car", 20);
        this.giftFactory.createGift(gift);
        Gift giftNew = new Gift("Car", 20);
        this.giftFactory.createGift(giftNew);
    }

    @Test
    public void testCreateGiftShouldAddGiftToFactory() {
        Assert.assertEquals(0, this.giftFactory.getCount());
        Gift gift = new Gift("Car", 20);
        this.giftFactory.createGift(gift);
        Assert.assertEquals(1, this.giftFactory.getCount());
        Assert.assertEquals(gift.getType(), "Car");
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveGiftShouldThrowExceptionOnNullName() {
        this.giftFactory.removeGift(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveGiftShouldThrowExceptionOnEmptyName() {
        this.giftFactory.removeGift("");
    }

    @Test
    public void testRemoveGiftShouldRemove() {
        Gift gift = new Gift("Car", 20);
        this.giftFactory.createGift(gift);
        Assert.assertEquals(1, this.giftFactory.getCount());

        boolean isRemoved = this.giftFactory.removeGift("Car");
        Assert.assertEquals(0, this.giftFactory.getCount());
        Assert.assertTrue(isRemoved);
    }

    @Test
    public void testRemoveGiftShouldNotRemoveMissingGift() {
        Gift gift = new Gift("Car", 20);
        this.giftFactory.createGift(gift);
        Assert.assertEquals(1, this.giftFactory.getCount());

        boolean isRemoved = this.giftFactory.removeGift("No");
        Assert.assertEquals(1, this.giftFactory.getCount());
        Assert.assertFalse(isRemoved);
    }

    @Test
    public void testGetPresentWithLeastMagicShouldReturnCorrectGift() {
        Gift gift1 = new Gift("Car", 20);
        Gift gift2 = new Gift("Ps5", 100);
        Gift gift3 = new Gift("Napkin", 2);
        this.giftFactory.createGift(gift1);
        this.giftFactory.createGift(gift2);
        this.giftFactory.createGift(gift3);

        Gift presentWithLeastMagic = this.giftFactory.getPresentWithLeastMagic();
        Assert.assertEquals(presentWithLeastMagic, gift3);
    }

    @Test
    public void testGetPresentWithLeastMagicShouldReturnNullOnMissingGifts() {
        Gift presentWithLeastMagic = this.giftFactory.getPresentWithLeastMagic();
        Assert.assertNull(presentWithLeastMagic);
    }

    @Test
    public void testGetPresentShouldReturnTheWantedGiftByName() {
        Gift gift1 = new Gift("Car", 20);
        Gift gift2 = new Gift("Ps5", 100);
        Gift gift3 = new Gift("Napkin", 2);
        this.giftFactory.createGift(gift1);
        this.giftFactory.createGift(gift2);
        this.giftFactory.createGift(gift3);

        Gift car = this.giftFactory.getPresent("Car");
        Assert.assertEquals(car, gift1);
    }

    @Test
    public void testGetPresentShouldReturnNullOnMissingGift() {
        Gift gift1 = new Gift("Car", 20);
        Gift gift2 = new Gift("Ps5", 100);
        Gift gift3 = new Gift("Napkin", 2);
        this.giftFactory.createGift(gift1);
        this.giftFactory.createGift(gift2);
        this.giftFactory.createGift(gift3);

        Gift car = this.giftFactory.getPresent("Missing");
        Assert.assertNull(car);
    }

    @Test
    public void testGetPresentsShouldReturnListOfGiftsInFactory() {
        Gift gift1 = new Gift("Car", 20);
        Gift gift2 = new Gift("Ps5", 100);
        Gift gift3 = new Gift("Napkin", 2);
        this.giftFactory.createGift(gift1);
        this.giftFactory.createGift(gift2);
        this.giftFactory.createGift(gift3);

        Collection<Gift> expected = List.of(gift1, gift2, gift3);
        Collection<Gift> presents = new ArrayList<>(this.giftFactory.getPresents());

        Assert.assertEquals(expected, presents);
    }
}
