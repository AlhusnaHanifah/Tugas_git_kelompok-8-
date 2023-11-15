/**
* Class SavingsAccount adalah turunan dari kelas Account dan merepresentasikan jenis akun tabungan.
* Konstruktor SavingsAccount digunakan untuk inisialisasi objek dengan informasi dasar, seperti nomor akun,
* saldo awal, pemilik akun, dan tingkat bunga. Konstruktor ini memanggil konstruktor kelas induk (Account)
* menggunakan kata kunci 'super'.
*
* Metode withdraw di-override untuk menangani penarikan uang dari akun tabungan. Jika saldo mencukupi, 
* penarikan berhasil dan saldo dikurangi sesuai jumlah penarikan. Jika tidak, pesan kesalahan ditampilkan.
*
* Kode program ini memanfaatkan konsep inheritance dalam pemrograman berorientasi objek,
* di mana SavingsAccount mewarisi atribut dan metode dari kelas induknya, Account.
*
* @author   : Widya Nurul Sukma
* @version  : 14-11-2023
*/

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
