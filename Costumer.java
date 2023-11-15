import java.util.ArrayList;
import java.util.List;

// Class Nasabah (Customer)
public class Customer {
    // Atribut-atribut data nasabah
    private String name;
    private String address;
    private String phoneNumber;
    private List<Account> accounts; // Daftar akun yang dimiliki oleh nasabah

    // Konstruktor untuk inisialisasi objek Customer dengan informasi dasar
    public Customer(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accounts = new ArrayList<>(); // Inisialisasi list akun sebagai objek ArrayList
    }

    // Konstruktor overload tanpa parameter, digunakan untuk pembuatan objek default
    public Customer() {

    }

    // Method untuk menambahkan akun baru ke dalam daftar akun nasabah
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // Method untuk mendapatkan daftar akun yang dimiliki oleh nasabah
    public List<Account> getAccounts() {
        return accounts;
    }

    // Method untuk mendapatkan nama pemilik nasabah
    public String getOwner() {
        return name;
    }

    // Method untuk mendapatkan objek akun berdasarkan nomor akun
    public Account getAccountByNumber(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        // Mengembalikan null jika tidak ditemukan akun dengan nomor tertentu
        return null;
    }

    // Method untuk memeriksa apakah nasabah memiliki akun dengan nomor tertentu
    public boolean hasAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return true;
            }
        }
        
        // Mengembalikan false jika tidak ditemukan akun dengan nomor tertentu
        return false;
    }
}
