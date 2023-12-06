package bank.core;

import bank.common.ConstantMessages;
import bank.common.ExceptionMessages;
import bank.entities.bank.Bank;
import bank.entities.bank.BranchBank;
import bank.entities.bank.CentralBank;
import bank.entities.client.Adult;
import bank.entities.client.Client;
import bank.entities.client.Student;
import bank.entities.loan.Loan;
import bank.entities.loan.MortgageLoan;
import bank.entities.loan.StudentLoan;
import bank.repositories.LoanRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private LoanRepository loans;
    private Collection<Bank> banks;

    public ControllerImpl() {
        this.loans = new LoanRepository();
        this.banks = new ArrayList<>();
    }

    @Override
    public String addBank(String type, String name) {
        Bank bank;

        switch (type) {
            case "CentralBank":
                bank = new CentralBank(name);
                break;
            case "BranchBank":
                bank = new BranchBank(name);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_BANK_TYPE);
        }

        this.banks.add(bank);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
    }

    @Override
    public String addLoan(String type) {
        Loan loan;

        switch (type) {
            case "StudentLoan":
                loan = new StudentLoan();
                break;
            case "MortgageLoan":
                loan = new MortgageLoan();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_LOAN_TYPE);
        }

        this.loans.addLoan(loan);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
    }

    @Override
    public String returnedLoan(String bankName, String loanType) {
        Loan loan = this.loans.findFirst(loanType);

        if (loan == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_LOAN_FOUND, loanType));
        }

        Bank bank = getBankByName(bankName);

        bank.addLoan(loan);
        this.loans.removeLoan(loan);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, loanType, bankName);
    }

    @Override
    public String addClient(String bankName, String clientType, String clientName, String clientID, double income) {
        Client client;
        switch (clientType) {
            case "Student":
                client = new Student(clientName, clientID, income);
                break;
            case "Adult":
                client = new Adult(clientName, clientID, income);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_CLIENT_TYPE);
        }

        Bank bank = getBankByName(bankName);

        if (clientType.equals("Student") && bank.getClass().getSimpleName().equals("BranchBank")) {
            bank.addClient(client);
        } else if (clientType.equals("Adult") && bank.getClass().getSimpleName().equals("CentralBank")) {
            bank.addClient(client);
        } else {
            return "Unsuitable bank.";
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, clientType, bankName);
    }

    private Bank getBankByName(String bankName) {
        Bank bank = this.banks
                .stream()
                .filter(b -> bankName.equals(b.getName()))
                .findFirst()
                .orElse(null);
        return bank;
    }

    @Override
    public String finalCalculation(String bankName) {
        Bank bank = this.getBankByName(bankName);

        double incomeFromAllClients = bank.getClients().stream().mapToDouble(Client::getIncome).sum();
        double allLoansAmountSum = bank.getLoans().stream().mapToDouble(Loan::getAmount).sum();

        return String.format(ConstantMessages.FUNDS_BANK, bankName, incomeFromAllClients + allLoansAmountSum);
    }

    @Override
    public String getStatistics() {
        return this.banks
                .stream()
                .map(Bank::getStatistics)
                .collect(Collectors.joining(System.lineSeparator()))
                .trim();
    }
}
