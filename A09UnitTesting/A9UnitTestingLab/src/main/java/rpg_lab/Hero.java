package rpg_lab;

import java.util.ArrayList;
import java.util.List;

public class Hero {

    private String name;
    private int experience;
    private Weapon weapon;
    private List<String> loot;

    public Hero(String name,Weapon weapon) {
        this.name = name;
        this.experience = 0;
        this.weapon = weapon;
        this.loot = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getExperience() {
        return this.experience;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public List<String> getLoot() {
        return loot;
    }

    public void attack(Target target) {
        this.weapon.attack(target);

        if (target.isDead()) {
            this.experience += target.giveExperience();
        }
    }

    public void getLoot(Target target) {
        if (target.isDead()) {
            this.loot.add(target.dropLoot());
        }
    }
}
