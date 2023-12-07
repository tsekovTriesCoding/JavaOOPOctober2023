package viceCity.models.guns;

public class Rifle extends BaseGun {
    public static final int DEFAULT_BULLETS_PER_BARREL = 50;
    public static final int DEFAULT_TOTAL_BULLETS = 500;

    public Rifle(String name) {
        super(name, DEFAULT_BULLETS_PER_BARREL, DEFAULT_TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        int bulletsLeftInBarrel = super.getBulletsPerBarrel() - 5;

        if (super.canFire()) {
            super.setBulletsPerBarrel(bulletsLeftInBarrel);

            if (super.getBulletsPerBarrel() == 0 && super.getTotalBullets() > 0) {
                super.setBulletsPerBarrel(DEFAULT_BULLETS_PER_BARREL);
                super.setTotalBullets(super.getTotalBullets() - DEFAULT_BULLETS_PER_BARREL);
            }
        }

        return 5;
    }
}
