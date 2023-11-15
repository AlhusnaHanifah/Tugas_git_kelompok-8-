// Class CheckingAccount turunan dari Account
public class CheckingAccount extends Account {
    private double serviceFee; // Biaya layanan untuk CheckingAccount

    // Konstruktor untuk inisialisasi objek CheckingAccount dengan informasi dasar
    public CheckingAccount(String accountNumber, double balance, String owner, double serviceFee) {
        super(accountNumber, balance, owner);
        this.serviceFee = serviceFee;
    }

    // Override method withdraw untuk menangani penarikan uang dari CheckingAccount
    @Override
    public void withdraw(double amount) {
        if (getBalance() >= amount + serviceFee) {
            setBalance(getBalance() - amount - serviceFee);
            System.out.println("Withdrawal successful. Current balance: " + getBalance());
        } else {
            System.out.println("Saldo tidak mencukupi untuk penarikan.");
        }
    }
}
