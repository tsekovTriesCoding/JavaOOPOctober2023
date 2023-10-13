package A4InterfacesAndAbstraction.Lab.P04SayHelloExtended;

public abstract class BasePerson implements Person {
    private String name;

    protected BasePerson(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

   @Override
    public abstract String sayHello();

    private void setName(String name) {
        this.name = name;
    }
}
