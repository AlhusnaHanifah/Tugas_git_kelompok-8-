import java.util.Map;

// Kelas Transaksi (Transaction)
public class Transaction {
    static Bank bank = new Bank();

    // Method untuk melakukan penarikan uang dari akun
    public static void makeWithdrawal(String accountNumber, double amount) {
        Map<String, Double> accountInfo = bank.loadAccountInfo();

        if (accountInfo.containsKey(accountNumber)) {
            double currentBalance = accountInfo.get(accountNumber);

            // Melakukan penarikan jika saldo mencukupi
            if (currentBalance >= amount) {
                currentBalance -= amount;
                accountInfo.put(accountNumber, currentBalance);

                // Menyimpan perubahan ke file
                bank.saveAccountInfo(accountNumber, bank.getOwnerNameByAccountNumber(accountNumber), currentBalance);
                System.out.println("Withdrawal successful. Current balance: " + currentBalance);
            } else {
                System.out.println("Saldo tidak mencukupi untuk penarikan.");
            }
        } else {
            System.out.println("Nomor akun tidak valid.");
        }
    }

    // Method untuk melakukan setoran uang ke akun
    public static void makeDeposit(String accountNumber, double amount) {
        Map<String, Double> accountInfo = bank.loadAccountInfo();

        if (accountInfo.containsKey(accountNumber)) {
            double currentBalance = accountInfo.get(accountNumber);

            // Melakukan setoran
            currentBalance += amount;
            accountInfo.put(accountNumber, currentBalance);

            // Menyimpan saldo yang diperbarui ke file
            bank.saveAccountInfo(accountNumber, bank.getOwnerNameByAccountNumber(accountNumber), currentBalance);
            System.out.println("Deposit successful. Current balance: " + currentBalance);
        } else {
            System.out.println("Nomor akun tidak valid.");
        }
    }


    // Method untuk transfer antara akun
    public static void makeTransfer(String sourceAccountNumber, String targetAccountNumber, double amount) {
        Map<String, Double> accountInfo = bank.loadAccountInfo();

        // Memeriksa apakah objek akun sumber dan target ditemukan
        if (accountInfo.containsKey(sourceAccountNumber) && accountInfo.containsKey(targetAccountNumber)) {
            double sourceBalance = accountInfo.get(sourceAccountNumber);
            double targetBalance = accountInfo.get(targetAccountNumber);

            // Melakukan penarikan dari akun sumber
            if (sourceBalance >= amount) {
                sourceBalance -= amount;
                accountInfo.put(sourceAccountNumber, sourceBalance);

                // Melakukan setoran ke akun target
                targetBalance += amount;
                accountInfo.put(targetAccountNumber, targetBalance);

                // Menyimpan saldo yang diperbarui ke file
                bank.saveAccountInfo(sourceAccountNumber, bank.getOwnerNameByAccountNumber(sourceAccountNumber), sourceBalance);
                bank.saveAccountInfo(targetAccountNumber, bank.getOwnerNameByAccountNumber(targetAccountNumber), targetBalance);

                // Menampilkan pesan berhasil
                System.out.println("Transfer successful. Amount: " + amount);
            } else {
                // Menampilkan pesan jika saldo tidak mencukupi
                System.out.println("Error: Saldo tidak mencukupi untuk transfer.");
            }
        } else {
            // Menampilkan pesan jika ada masalah dalam mendapatkan objek akun
            System.out.println("Error: Objek akun tidak ditemukan.");
        }
    }

}
