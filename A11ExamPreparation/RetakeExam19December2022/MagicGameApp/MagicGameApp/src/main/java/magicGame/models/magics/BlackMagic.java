package magicGame.models.magics;

public class BlackMagic extends MagicImpl {
    public static final int AMMUNITION = 10;
    public BlackMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (this.getBulletsCount() - AMMUNITION < 0) {
            this.setBulletsCount(0);
            return 0;
        } else {
            this.setBulletsCount(this.getBulletsCount() - AMMUNITION);
            return 10;
        }
    }
}
