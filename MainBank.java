/**
 * Kelas MainBank merupakan kelas utama (entry point) program untuk aplikasi perbankan sederhana.
 * Pengguna dapat membuat akun, melakukan login, dan melakukan berbagai transaksi seperti
 * pengecekan saldo, setor uang, tarik tunai, dan transfer uang antar akun.
 *
 * Kelas ini menggunakan objek Bank, Customer, dan Transaction untuk mengelola data pelanggan,
 * informasi akun, serta melakukan transaksi keuangan.
 *
 * Pengguna akan diminta untuk memasukkan informasi pribadi saat membuat akun baru.
 * Selanjutnya, mereka dapat login menggunakan nomor akun untuk mengakses layanan perbankan.
 *
 * Fitur:
 * - Pengecekan akun: Pengguna dapat memeriksa keberadaan akun berdasarkan nomor akun.
 * - Pembuatan Akun: Pengguna dapat membuat akun tabungan atau giro dengan memilih jenis akun.
 * - Setor Uang: Pengguna dapat melakukan setoran uang ke dalam akun mereka.
 * - Tarik Tunai: Pengguna dapat melakukan penarikan uang dari akun mereka.
 * - Transfer Uang: Pengguna dapat mentransfer uang dari satu akun ke akun lainnya.
 *
 * Program akan menyimpan informasi akun ke dalam file "account_info.txt" dan nomor akun ke dalam
 * file "account_numbers.txt" untuk mempertahankan data antar sesi program.
 *
 * Program akan menampilkan menu interaktif yang memandu pengguna dalam menggunakan layanan perbankan
 * dan memberikan umpan balik terkait setiap transaksi yang dilakukan.
 *
 * @author    : Alhusna Hanifah
 * @version   : 14-11-2023
 */

import java.util.Scanner;
import java.util.Map;

public class MainBank {
    //constructor
    public MainBank(){

    }
    public static void main(String[] args) {
        // Inisialisasi objek Scanner untuk menerima input dari pengguna
        Scanner scanner = new Scanner(System.in);
        // Inisialisasi objek Bank
        Bank bank = new Bank();
        // Variabel untuk menentukan apakah program akan berjalan ulang
        boolean ulang = true;

        // Tampilan selamat datang dan informasi bank
        System.out.println("\t\t========================================================");
        System.out.println("\t\t||     ##       WELCOME TO NADY'S BANK  !!    ##      ||");
        System.out.println("\t\t||        Your Gateway to Exceptional Banking         ||");
        System.out.println("\t\t||        Where Your Financial Needs Meet Care        ||");
        System.out.println("\t\t||             and Excellence Every Time!             ||");
        System.out.println("\t\t========================================================");
        System.out.println("\n==================================================================================");
        System.out.println("||                        INFORMASI TENTANG NADY'S BANK                         ||");
        System.out.println("||  1. Anda dapat membuat akun bank sesuai keinginan Anda                       ||");
        System.out.println("||  2. Terdapat 2 Akun yaitu, SavingAccount dan CheckingAccount                 ||");
        System.out.println("||  3. Anda dapat melakukan berbagai transaksi, seperti                         ||");
        System.out.println("||     cek saldo, menarik, simpan, dan transfer uang                            ||");
        System.out.println("||  4. Anda dapat langsung melakukan transaksi jika                             ||");
        System.out.println("||     sudah pernah membuat akun sebelumnya                                     ||");
        System.out.println("||  5. Akun Anda akan tersimpan jika Anda telah berhasil membuat akun           ||");
        System.out.println("==================================================================================\n");

        // Pengecekan apakah pengguna sudah memiliki akun atau belum
        System.out.println("\t\t============================================");
        System.out.println("\t\t||   Apakah Anda sudah memiliki Akun??    ||");
        System.out.println("\t\t||                (YES/NO)                ||");
        System.out.println("\t\t============================================");
        System.out.print("\t\tMasukkan Pilihan Anda : ");
        String haveAccount = scanner.next();
        String nomorAkun = null;
        Customer customer = null;

        // Jika pengguna belum memiliki akun, maka membuat akun baru
        if (haveAccount.equalsIgnoreCase("No")) {
            System.out.println();
            System.out.print("Masukkan nama Anda : ");
            String nama = scanner.next();
            System.out.print("Masukkan alamat Anda : ");
            String alamat = scanner.next();
            System.out.print("Masukkan nomor HP Anda : ");
            String noHp = scanner.next();

            // Membuat objek Customer
            Customer customer2 = new Customer(nama, alamat, noHp);
            bank.addCustomer(customer2);

            // Memilih jenis akun yang ingin dibuat
            System.out.println("\n ===========================================");
            System.out.println("||             Pilih Jenis Akun            ||");
            System.out.println("||              1. Savings                 ||");
            System.out.println("||              2. Checking                ||");
            System.out.println(" ============================================");
            System.out.print("Masukkan pilihan (1-2): ");
            int jenisAkun = scanner.nextInt();
            System.out.println();

            // Membuat akun berdasarkan pilihan pengguna
            if (jenisAkun == 1) {
                System.out.print("Masukkan saldo awal untuk akun tabungan: Rp.");
                double saldoTabungan = scanner.nextDouble();
                SavingsAccount newSavingsAccount = new SavingsAccount("", saldoTabungan, customer2.getOwner(), 0.0);
                bank.createAccount(customer2, newSavingsAccount);
                nomorAkun = newSavingsAccount.getAccountNumber();

                // Menyimpan informasi ke dalam file txt
                bank.saveAccountInfo(newSavingsAccount.getAccountNumber(), newSavingsAccount.getOwner(), saldoTabungan);

            } else if (jenisAkun == 2) {
                System.out.print("Masukkan saldo awal untuk akun tabungan: ");
                double saldoGiro = scanner.nextDouble();
                CheckingAccount newCheckingAccount = new CheckingAccount("", saldoGiro, customer2.getOwner(), 0.0);
                bank.createAccount(customer2, newCheckingAccount);
                nomorAkun = newCheckingAccount.getAccountNumber();

                // Menyimpan informasi ke dalam file txt
                bank.saveAccountInfo(newCheckingAccount.getAccountNumber(), newCheckingAccount.getOwner(), saldoGiro);
            } else {
                System.out.println("Pilihan jenis akun tidak valid.");
            }
        }

        // Loop untuk memasukkan nomor akun yang valid
        while (ulang) {
            System.out.println();
            System.out.print("Masukkan nomor akun Anda : ");
            nomorAkun = scanner.next();
        
            boolean akunValid = false;
        
            // Mengecek nomor akun pada file account_info.txt
            if (bank.loadAccountInfo().containsKey(nomorAkun)) {
                System.out.println();
                System.out.println("\n\t\t\t------------------------------------ ");
                System.out.println("\t\t\t      Selamat datang, "+bank.getOwnerNameByAccountNumber(nomorAkun) + "!");
                System.out.println("\t\t\t------------------------------------ "); 
                akunValid = true;
            }
        
            if (!akunValid) {
                System.out.println();
                System.out.println("Nomor akun Anda salah. Silakan coba lagi.");
            } else {
                ulang = false;
            }
        }        

        // Loop untuk melakukan transaksi
        while (ulang == false) {
            System.out.println();
            System.out.println("\n\t\t==================== NADY's Bank ====================");
            System.out.println("\t\t||     Pilih transaksi yang ingin Anda lakukan     ||");
            System.out.println("\t\t||              1. Cek Saldo                       ||");
            System.out.println("\t\t||              2. Setor Uang                      ||");
            System.out.println("\t\t||              3. Tarik Tunai                     ||");
            System.out.println("\t\t||              4. Transfer Uang                   ||");
            System.out.println("\t\t||              5. Exit                            ||");
            System.out.println("\t\t=====================================================");
            System.out.print("\t\tMasukkan pilihan (1-5): ");
            int pilihan = scanner.nextInt();
            System.out.println();
            Map<String, Double> accountInfo = bank.loadAccountInfo();

            // Switch case untuk memilih transaksi
            switch (pilihan) {
                case 1:
                    // Cek Saldo
                    double saldoAkun = accountInfo.get(nomorAkun);
                    if (saldoAkun != 0.0) {
                        System.out.println("Saldo akun " + nomorAkun + ": Rp." + saldoAkun);
                    } else {
                        System.out.println("Nomor akun tidak valid.");
                    }
                    break;

                case 2:
                    // Setor Uang
                    double saldoAkun1 = accountInfo.get(nomorAkun);
                    if (saldoAkun1 != 0.0) {
                        System.out.print("Masukkan jumlah uang yang ingin disetor: Rp.");
                        double jumlahSetor = scanner.nextDouble();
                        Transaction.makeDeposit(nomorAkun, jumlahSetor);
                    } else {
                        System.out.println("Nomor akun tidak valid.");
                    }
                    
                    break;

                case 3:
                    // Tarik Tunai
                    double saldoAkun2 = accountInfo.get(nomorAkun);
                    if (saldoAkun2 != 0.0) {
                        System.out.print("Masukkan jumlah uang yang ingin ditarik: Rp.");
                        double jumlahTarik = scanner.nextDouble();
                        Transaction.makeWithdrawal(nomorAkun, jumlahTarik);
                    } else {
                        System.out.println("Nomor akun tidak valid.");
                    }

                    break;

                case 4:
                    // Transfer Uang

                    double saldoAkun3 = accountInfo.get(nomorAkun);
                    if (saldoAkun3 != 0.0) {
                        System.out.print("Masukkan nomor akun tujuan transfer: ");
                        String nomorAkunTujuan = scanner.next();
                       
                        if (saldoAkun3 != 0.0) {
                            System.out.print("Masukkan jumlah uang yang ingin ditransfer: Rp.");
                            double jumlahTransfer = scanner.nextDouble();
                            Transaction.makeTransfer(nomorAkun, nomorAkunTujuan, jumlahTransfer);
                        } else {
                            System.out.println("Nomor akun tujuan transfer tidak valid.");
                        }
                    } else {
                        System.out.println("Nomor akun sumber transfer tidak valid.");
                    }
                    break;

                case 5:
                    // Keluar dari program
                    ulang = true;
                    System.out.println("\t\t=======================================================");
                    System.out.println("\t\t||   Terima kasih atas kepercayaan Anda menggunakan  ||");
                    System.out.println("\t\t||          layanan eksklusif NADY'S Bank            ||");
                    System.out.println("\t\t||                   Sampai jumpa!                   ||");
                    System.out.println("\t\t=======================================================");
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        }
    }
}
