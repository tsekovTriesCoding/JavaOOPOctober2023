package magicGame.models.magics;

public class RedMagic extends MagicImpl {
    private static final int AMMUNITION = 1;

    public RedMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (this.getBulletsCount() - AMMUNITION < 0) {
            this.setBulletsCount(0);
            return 0;
        } else {
            this.setBulletsCount(this.getBulletsCount() - AMMUNITION);
            return 1;
        }
    }

}
