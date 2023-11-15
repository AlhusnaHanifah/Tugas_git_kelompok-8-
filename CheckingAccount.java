/**
* Class CheckingAccount adalah turunan dari kelas Account, digunakan untuk merepresentasikan akun bank khusus jenis Checking.
*
* Konstruktor CheckingAccount digunakan untuk inisialisasi objek dengan menyediakan informasi dasar seperti nomor akun,
* saldo awal, pemilik akun, dan biaya layanan. Konstruktor ini memanggil konstruktor kelas induk (Account) menggunakan
* kata kunci 'super' untuk menginisialisasi atribut dasar akun.
*
* Method withdraw di-override untuk menangani proses penarikan uang dari CheckingAccount. Proses ini memeriksa apakah
* saldo mencukupi untuk jumlah penarikan beserta biaya layanan. Jika cukup, saldo dikurangi dan pesan berhasil
* dicetak. Jika tidak, pesan kesalahan mencetak bahwa saldo tidak mencukupi untuk penarikan.
*
* @author : Widya Nurul Sukma
* @version : 14-11-2023
*/
    
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
