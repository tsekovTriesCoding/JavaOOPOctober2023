package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Map;

public class ShopTest {
    private Shop shop;

    @Before
    public void init() {
        this.shop = new Shop();
    }

    @Test
    public void testGetShelvesShouldReturnShelves() {
        Map<String, Goods> shelves = this.shop.getShelves();
        Assert.assertEquals(12, shelves.keySet().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsShouldThrowExceptionOnMissingShelf() throws OperationNotSupportedException {
        this.shop.addGoods("NoShelf", new Goods("Santa", "Hdbndh45"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsShouldThrowExceptionOnTakenShelf() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves1", new Goods("Santa", "Hdbndh45"));
        this.shop.addGoods("Shelves1", new Goods("Santa", "Hdbndh45"));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddGoodsShouldThrowExceptionOnExistingGood() throws OperationNotSupportedException {
        Goods goods = new Goods("Santa", "Hdbndh45");
        this.shop.addGoods("Shelves1", goods);
        this.shop.addGoods("Shelves2", goods);
    }

    @Test
    public void testAddGoodsShouldAdd() throws OperationNotSupportedException {
        Map<String, Goods> shelves = this.shop.getShelves();
        Assert.assertNull(shelves.get("Shelves1"));

        Goods goods = new Goods("Santa", "Hdbndh45");
        String actualMessage = this.shop.addGoods("Shelves1", goods);
        String expectedMessage = String.format("Goods: %s is placed successfully!", goods.getGoodsCode());
        Assert.assertEquals(goods, shelves.get("Shelves1"));
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsShouldThrowExceptionOnMissingShelf() {
        this.shop.removeGoods("NoShelf", new Goods("Santa", "Hdbndh45"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsShouldThrowExceptionOnMissingGoodsOnShelf() throws OperationNotSupportedException {
        Goods goods = new Goods("Santa", "Hdbndh45");
        Goods goodsToRemove = new Goods("Car", "ncbs^54xd");
        this.shop.addGoods("Shelves1", goods);

        this.shop.removeGoods("Shelves1", goodsToRemove);
    }

    @Test
    public void testRemoveGoodsShouldRemove() throws OperationNotSupportedException {
        Goods goods = new Goods("Santa", "Hdbndh45");
        this.shop.addGoods("Shelves1", goods);
        Assert.assertEquals(goods, this.shop.getShelves().get("Shelves1"));

        String actualMessage = this.shop.removeGoods("Shelves1", goods);
        String expectedMessage = String.format("Goods: %s is removed successfully!", goods.getGoodsCode());
        Assert.assertNull( this.shop.getShelves().get("Shelves1"));
        Assert.assertEquals(expectedMessage, actualMessage);
    }


}