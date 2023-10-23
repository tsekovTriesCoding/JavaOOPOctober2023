package A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Classes;

import A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Interfaces.LieutenantGeneral;
import A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Interfaces.Private;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class LieutenantGeneralImpl extends PrivateImpl implements LieutenantGeneral {
    private Collection<PrivateImpl> privates;

    public LieutenantGeneralImpl(int id, String firstName, String lastName, double salary, Collection<PrivateImpl> privates) {
        super(id, firstName, lastName, salary);
        this.privates = privates;
    }

    @Override
    public Collection<Private> getPrivates() {
        return Collections.unmodifiableCollection(this.privates);
    }

    @Override
    public void addPrivate(Private priv) {
        this.privates.add((PrivateImpl) priv);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Privates:").append(System.lineSeparator());
        this.privates.stream()
                .sorted(Comparator.comparingInt(PrivateImpl::getId).reversed())
                .forEach(aPrivate -> sb.append("  ").append(aPrivate));

        return sb.toString();
    }
}
