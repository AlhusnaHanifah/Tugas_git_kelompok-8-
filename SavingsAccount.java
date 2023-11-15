
public class SavingsAccount extends Account {
    private double interestRate;

    // Konstruktor untuk inisialisasi objek SavingsAccount dengan informasi dasar
    public SavingsAccount(String accountNumber, double balance, String owner, double interestRate) {
        // Memanggil konstruktor kelas induk (Account)
        super(accountNumber, balance, owner);
        this.interestRate = interestRate;
    }

    // Override method withdraw untuk menangani penarikan uang dari SavingsAccount
    @Override
    public void withdraw(double amount) {
        if (getBalance() >= amount) {
            setBalance(getBalance() - amount);
            System.out.println("Withdrawal successful. Current balance: " + getBalance());
        } else {
            System.out.println("Saldo tidak mencukupi untuk penarikan.");
        }
    }
}
