package A4InterfacesAndAbstraction.Exercise.P06MilitaryElite;

import A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Classes.*;
import A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Enums.Corps;
import A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Enums.State;
import A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Interfaces.Mission;
import A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Interfaces.Repair;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Map<Integer, PrivateImpl> privatesMap = new HashMap<>();
        List<SoldierImpl> soldiers = new ArrayList<>();
        while (!"End".equals(input)) {
            String[] tokens = input.split("\\s+");
            String soldierType = tokens[0];

            switch (soldierType) {
                case "Private":
                    int id = Integer.parseInt(tokens[1]);
                    String firstName = tokens[2];
                    String lastName = tokens[3];
                    double salary = Double.parseDouble(tokens[4]);

                    PrivateImpl privateImpl = new PrivateImpl(id, firstName, lastName, salary);
                    privatesMap.put(id, privateImpl);
                    soldiers.add(privateImpl);
                    break;
                case "LieutenantGeneral":
                    id = Integer.parseInt(tokens[1]);
                    firstName = tokens[2];
                    lastName = tokens[3];
                    salary = Double.parseDouble(tokens[4]);

                    Collection<PrivateImpl> privates = new LinkedHashSet<>();
                    for (int i = 5; i < tokens.length; i++) {
                        int privateId = Integer.parseInt(tokens[i]);
                        privates.add(privatesMap.get(privateId));
                    }
                    LieutenantGeneralImpl lieutenantGeneral = new LieutenantGeneralImpl(id, firstName, lastName, salary, privates);
                    soldiers.add(lieutenantGeneral);
                    break;
                case "Engineer":
                    id = Integer.parseInt(tokens[1]);
                    firstName = tokens[2];
                    lastName = tokens[3];
                    salary = Double.parseDouble(tokens[4]);

                    try {
                        Corps corps = Corps.valueOf(tokens[5]);
                        Collection<Repair> repairs = new LinkedHashSet<>();
                        for (int i = 6; i < tokens.length; i += 2) {
                            String partName = tokens[i];
                            int hoursWorked = Integer.parseInt(tokens[i + 1]);
                            RepairImpl repair = new RepairImpl(partName, hoursWorked);
                            repairs.add(repair);
                        }
                        EngineerImpl engineer = new EngineerImpl(id, firstName, lastName, salary, corps, repairs);
                        soldiers.add(engineer);
                    } catch (IllegalArgumentException e) {
                        input = scanner.nextLine();
                        continue;
                    }
                    break;
                case "Commando":
                    id = Integer.parseInt(tokens[1]);
                    firstName = tokens[2];
                    lastName = tokens[3];
                    salary = Double.parseDouble(tokens[4]);
                    Collection<Mission> missions = new LinkedHashSet<>();
                    try {
                        Corps corps = Corps.valueOf(tokens[5]);
                        for (int i = 6; i < tokens.length; i += 2) {
                            String codeName = tokens[i];
                            try {
                                State state = State.valueOf(tokens[i + 1]);
                                MissionImpl mission = new MissionImpl(codeName, state);
                                missions.add(mission);
                            } catch (IllegalArgumentException ignored) {

                            }
                        }

                        CommandoImpl commando = new CommandoImpl(id, firstName, lastName, salary, corps, missions);
                        soldiers.add(commando);

                    } catch (IllegalArgumentException e) {
                        input = scanner.nextLine();
                        continue;
                    }
                    break;
                case "Spy":
                    id = Integer.parseInt(tokens[1]);
                    firstName = tokens[2];
                    lastName = tokens[3];
                    String codeNumber = tokens[4];

                    SpyImpl spy = new SpyImpl(id, firstName, lastName, codeNumber);
                    soldiers.add(spy);
                    break;

            }

            input = scanner.nextLine();
        }

        soldiers.forEach(System.out::print);
    }
}
