package A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Interfaces;

import java.util.Collection;

public interface LieutenantGeneral {
    Collection<Private> getPrivates();

    void addPrivate(Private priv);

}
