/**
* Class Bank digunakan untuk merepresentasikan suatu bank dengan fungsionalitas dasar, seperti
* menambahkan nasabah baru, membuat akun baru, dan mengelola informasi akun. Kelas ini juga
* menyimpan informasi nomor akun ke dalam file dan memiliki beberapa metode untuk membaca dan
* menyimpan informasi akun.
*
* Atribut:
* - customers: List untuk menyimpan objek Customer yang merupakan nasabah bank.
* - accountNumbers: List untuk menyimpan semua nomor akun yang telah dibuat.
* - accountNumbersFile: Nama file yang digunakan untuk menyimpan informasi nomor akun.
*
* Method Utama pada Class:
* - addCustomer: Menambahkan nasabah baru ke dalam daftar pelanggan.
* - getCustomers: Mengembalikan daftar pelanggan bank.
* - createAccount: Membuat akun baru untuk nasabah dan menyimpan informasi akun ke dalam file.
* - getAccountByNumber: Mengembalikan objek akun berdasarkan nomor akun dari seorang nasabah.
*
* Method Pendukung pada Class:
* - generateAccountNumber: Menghasilkan nomor akun baru secara otomatis.
* - loadAccountNumbers: Membaca semua nomor akun dari file.
* - saveAccountNumbers: Menyimpan semua nomor akun ke dalam file.
* - loadAccountInfo: Membaca informasi akun (nomor akun, nama pemilik, saldo) dari file.
* - saveAccountInfo: Menyimpan informasi akun ke dalam file.
* - getOwnerNameByAccountNumber: Mendapatkan nama pemilik berdasarkan nomor akun.
*
* @author  : Widya Nurul Sukma
* @version : 14-11-2023
*/

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

// Class Bank
public class Bank {
    private List<Customer> customers;
    private List<String> accountNumbers; // List to store all account numbers
    private String accountNumbersFile = "Database/account_numbers.txt"; // File to store account numbers

    // Konstruktor untuk inisialisasi objek Bank
    public Bank() {
        this.customers = new ArrayList<>();
        this.accountNumbers = loadAccountNumbers(); // Load all account numbers from the file
    }
    // Method untuk menambahkan nasabah baru ke dalam daftar pelanggan
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Metode untuk mendapatkan daftar pelanggan
    public List<Customer> getCustomers() {
        return customers;
    }

    // Method membuat akun baru untuk nasabah
    public void createAccount(Customer customer, Account account) {
        String accountNumber = generateAccountNumber();
        account.setAccountNumber(accountNumber);
        customer.addAccount(account);
        accountNumbers.add(accountNumber); // Add the new account number to the list
        saveAccountNumbers(); // Save all account numbers to the file
        System.out.println("Account created successfully. Account Number: " + accountNumber);
    }

    // Method untuk menghasilkan nomor akun baru secara otomatis
    private String generateAccountNumber() {
        String newAccountNumber = "ACC" + (accountNumbers.size() + 1);
        return newAccountNumber;
    }

     // Method untuk membaca semua nomor akun dari file
    public List<String> loadAccountNumbers() {
        List<String> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(accountNumbersFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
    }

     // Method untuk menyimpan semua nomor akun ke dalam file
    private void saveAccountNumbers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(accountNumbersFile))) {
            for (String accountNumber : accountNumbers) {
                writer.write(accountNumber);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method untuk mendapatkan objek akun berdasarkan nomor akun dari seorang nasabah
    public Account getAccountByNumber(Customer customer, String nomorAkun) {
        Map<String, Double> accountInfo = loadAccountInfo();
        String accountNumber = nomorAkun.trim();  // Menghilangkan spasi dari input
        if (accountInfo.containsKey(accountNumber)) {
            // Ambil saldo dari accountInfo
            double balance = accountInfo.get(accountNumber);
            
            // Mencari objek akun yang sesuai di daftar akun nasabah
            for (Account account : customer.getAccounts()) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    // Memperbarui saldo pada objek akun yang sesuai
                    account.setBalance(balance);
                    return account;
                }
            }
            
            // Jika objek akun tidak ditemukan, return null
            System.out.println("Error: Objek akun dengan nomor " + nomorAkun + " tidak ditemukan.");
            return null;
        } else {
            // Return null jika nomor akun tidak ditemukan di accountInfo
            System.out.println("Error: Nomor akun " + nomorAkun + " tidak ditemukan di account_info.txt.");
            return null;
        }
    }

    // Method untuk menyimpan informasi akun (nomor akun, nama pemilik, saldo) ke dalam file
    public void saveAccountInfo(String accountNumber, String owner, double balance) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Database/account_info.txt", true))) {
            writer.write(accountNumber + "," + owner + "," + balance);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method untuk membaca informasi akun (nomor akun, nama pemilik, saldo) dari file
    public Map<String, Double> loadAccountInfo() {
        Map<String, Double> accountInfo = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Database/account_info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String accountNumber = parts[0];
                    double balance = Double.parseDouble(parts[2]);
                    accountInfo.put(accountNumber, balance);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accountInfo;
    }
    // Method untuk mendapatkan nama pemilik berdasarkan nomor akun
    public String getOwnerNameByAccountNumber(String accountNumber) {
        Map<String, String> accountInfo = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Database/account_info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String currentAccountNumber = parts[0];
                    String nama = parts[1];
                    accountInfo.put(currentAccountNumber, nama);

                    // Jika nomor akun yang sedang dibaca sesuai dengan yang dicari, maka akan di kembalikan nama pemilik
                    if (currentAccountNumber.equals(accountNumber)) {
                        return nama;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Jika sampai pada tahap ini, berarti nomor akun tidak ditemukan
        return null;
    }

}
