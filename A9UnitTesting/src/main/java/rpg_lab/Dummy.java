package rpg_lab;

public class Dummy implements Target {

    private int health;
    private int experience;
    private String loot;

    public Dummy(int health, int experience, String loot) {
        this.health = health;
        this.experience = experience;
        this.loot = loot;
    }

    public int getHealth() {
        return this.health;
    }

    public void takeAttack(int attackPoints) {
        if (this.isDead()) {
            throw new IllegalStateException("Dummy is dead.");
        }

        this.health -= attackPoints;
    }

    public int giveExperience() {
        if (!this.isDead()) {
            throw new IllegalStateException("Target is not dead.");
        }

        return this.experience;
    }

    public boolean isDead() {
        return this.health <= 0;
    }

    @Override
    public String dropLoot() {
        if (isDead()) {
            return this.loot;
        }

        throw new IllegalStateException("Target is not dead.");
    }
}
