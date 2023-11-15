/**
 * Kelas abstrak Account merupakan dasar dari hierarki kelas akun dalam sistem perbankan.
 * Setiap akun memiliki nomor unik, saldo, dan pemilik akun.
 *
 * Terdapat metode abstrak withdraw(double amount) memungkinkan implementasi khusus untuk tiap jenis akun
 * seperti SavingsAccount dan CheckingAccount, sesuai dengan kebutuhan spesifik akun tersebut.
 *
 * @author   : Alhusna Hanifah
 * @version  : 14-11-2023
 */

abstract class Account {
    private String accountNumber; // Nomor unik yang mengidentifikasi akun
    private double balance; // Saldo akun
    private String owner; // Nama pemilik akun

    // Konstruktor untuk inisialisasi objek Account
    public Account(String accountNumber, double balance, String owner) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
    }

    // Method untuk mendapatkan nomor Account
    public String getAccountNumber() {
        return accountNumber;
    }

    // Method untuk mengatur nomor Account
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // Method untuk mendapatkan saldo Account
    public double getBalance() {
        return balance;
    }

    // Method untuk mengatur saldo Account
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Method untuk melakukan setoran ke Account
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. Current balance: " + balance);
    }

    // Method abstrak untuk menarik sejumlah uang dari Account
    public abstract void withdraw(double amount);

     // Method untuk mendapatkan nama pemilik Account
    public String getOwner() {
        return owner;
    }
}
