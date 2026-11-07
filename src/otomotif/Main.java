package otomotif;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Mengatur menu dan data kendaraan selama program berjalan.
public class Main {
        private static final Scanner INPUT = new Scanner(System.in);
        private static final List<Vehicle> VEHICLES = new ArrayList<Vehicle>();

    public static void main(String[] args) {
                loadDummyData();
                System.out.println("=== SISTEM LAYANAN OTOMOTIF ===");
                System.out.println("Data contoh dimuat: 3 kendaraan.");
                System.out.println("Interval servis: setiap " + Vehicle.SERVICE_INTERVAL_KM + " km");

                boolean running = true;
                while (running) {
                        printMenu();
                        Integer choice = readInt("Pilih menu", 0, 6);
                        if (choice == null) {
                                continue;
                        }

                        switch (choice) {
                                case 1:
                                        addVehicle();
                                        break;
                                case 2:
                                        showAllVehicles();
                                        break;
                                case 3:
                                        showVehiclesDueForService();
                                        break;
                                case 4:
                                        serviceVehicle();
                                        break;
                                case 5:
                                        updateMileage();
                                        break;
                                case 6:
                                        showCostEstimate();
                                        break;
                                case 0:
                                        running = false;
                                        System.out.println("Program selesai.");
                                        break;
                                default:
                                        break;
                        }
        }
        }

        private static void loadDummyData() {
                VEHICLES.add(new Car("B 1234 CD", "Toyota", "Avanza", 12500, 7, 1500));
                VEHICLES.add(new Motorcycle("D 5678 EF", "Honda", "Vario", 3200, 160));
                VEHICLES.add(new Motorcycle("F 9012 GH", "Yamaha", "NMAX", 11000, 155));
        }

        private static void printMenu() {
                System.out.println("\n--- MENU UTAMA ---");
                System.out.println("1. Tambah kendaraan");
                System.out.println("2. Lihat semua kendaraan");
                System.out.println("3. Lihat kendaraan yang perlu servis");
                System.out.println("4. Proses servis kendaraan");
                System.out.println("5. Perbarui odometer kendaraan");
                System.out.println("6. Lihat estimasi biaya");
                System.out.println("0. Keluar");
                System.out.println("Ketik /b saat mengisi data untuk kembali ke menu utama.");
        }

        private static void addVehicle() {
                System.out.println("\n--- TAMBAH KENDARAAN ---");
                System.out.println("1. Mobil");
                System.out.println("2. Motor");
                System.out.println("0. Kembali ke menu utama");

                Integer vehicleType = readInt("Pilih jenis", 0, 2);
                if (vehicleType == null || vehicleType == 0) {
                        return;
                }

                String registrationNumber;
                while (true) {
                        registrationNumber = readRequiredText("Nomor plat");
                        if (registrationNumber == null) {
                                return;
                        }
                        if (!registrationExists(registrationNumber)) {
                                break;
                        }
                        System.out.println("Nomor plat sudah terdaftar. Masukkan nomor plat lain.");
                }

                String brand = readRequiredText("Merek");
                if (brand == null) {
                        return;
        }

                String model = readRequiredText("Model");
                if (model == null) {
                        return;
        }

                Integer mileage = readInt("Odometer saat ini (km)", 0, Integer.MAX_VALUE);
                if (mileage == null) {
                        return;
                }

                Vehicle vehicle;
                if (vehicleType == 1) {
                        Integer seatCount = readInt("Jumlah kursi", 1, Integer.MAX_VALUE);
                        if (seatCount == null) {
                                return;
                        }

                        Integer engineCapacityCc = readInt("Kapasitas mesin (cc)", 1, Integer.MAX_VALUE);
                        if (engineCapacityCc == null) {
                                return;
                        }
                        vehicle = new Car(
                                        registrationNumber, brand, model, mileage, seatCount, engineCapacityCc);
                } else {
                        Integer engineCapacityCc = readInt("Kapasitas mesin (cc)", 1, Integer.MAX_VALUE);
                        if (engineCapacityCc == null) {
                                return;
                        }
                        vehicle = new Motorcycle(registrationNumber, brand, model, mileage, engineCapacityCc);
        }

                VEHICLES.add(vehicle);
                System.out.println("Kendaraan berhasil ditambahkan.");
                System.out.println(vehicle.getDetails());
        }

        private static void showAllVehicles() {
                displayVehicles(VEHICLES, "Belum ada kendaraan.");
        }

        private static void showVehiclesDueForService() {
                List<Vehicle> dueVehicles = getVehiclesDueForService();
                displayVehicles(dueVehicles, "Tidak ada kendaraan yang perlu diservis.");
        }

        private static List<Vehicle> getVehiclesDueForService() {
                List<Vehicle> dueVehicles = new ArrayList<Vehicle>();
                for (Vehicle vehicle : VEHICLES) {
                        if (vehicle.isServiceDue()) {
                                dueVehicles.add(vehicle);
                        }
                }
                return dueVehicles;
        }

        private static void serviceVehicle() {
                List<Vehicle> dueVehicles = getVehiclesDueForService();
                Vehicle vehicle = selectVehicle(
                                "Pilih kendaraan yang akan diservis", dueVehicles,
                                "Tidak ada kendaraan yang perlu diservis.");
                if (vehicle == null) {
                        return;
                }

                Serviceable serviceable = vehicle;
                System.out.println(serviceable.performService());
                System.out.println("Status terbaru: " + vehicle.getDetails());
        }

        private static void updateMileage() {
                Vehicle vehicle = selectVehicle("Pilih kendaraan untuk memperbarui odometer");
                if (vehicle == null) {
                        return;
                }

                Integer mileage = readInt(
                                "Odometer baru (minimal " + vehicle.getMileage() + " km)",
                                vehicle.getMileage(), Integer.MAX_VALUE);
                if (mileage == null) {
                        return;
                }
                vehicle.setMileage(mileage);
                System.out.println("Odometer berhasil diperbarui.");
                System.out.println(vehicle.getDetails());
        }

        private static void showCostEstimate() {
                Vehicle vehicle = selectVehicle("Pilih kendaraan untuk melihat estimasi");
                if (vehicle == null) {
                        return;
                }

                if (vehicle instanceof Insurable) {
                        Insurable insurable = (Insurable) vehicle;
                        System.out.println("Estimasi asuransi: "
                                        + formatRupiah(insurable.calculateInsurancePremium()));
                }

                if (vehicle instanceof Taxable) {
                        Taxable taxable = (Taxable) vehicle;
                        System.out.println("Estimasi pajak tahunan: "
                                        + formatRupiah(taxable.calculateAnnualTax()));
        }
        }

        private static Vehicle selectVehicle(String prompt) {
                return selectVehicle(prompt, VEHICLES, "Belum ada kendaraan.");
        }

        private static Vehicle selectVehicle(
                        String prompt, List<Vehicle> options, String emptyMessage) {
                if (options.isEmpty()) {
                        System.out.println(emptyMessage);
                        return null;
        }

                displayVehicles(options, "");
                System.out.println("0. Kembali ke menu utama");
                Integer selection = readInt(prompt, 0, options.size());
                if (selection == null || selection == 0) {
                        return null;
        }
                return options.get(selection - 1);
        }

        private static void displayVehicles(List<Vehicle> vehicles, String emptyMessage) {
                if (vehicles.isEmpty()) {
                        System.out.println(emptyMessage);
                        return;
                }

                for (int index = 0; index < vehicles.size(); index++) {
                        System.out.println((index + 1) + ". " + vehicles.get(index).getDetails());
        }
        }

        private static boolean registrationExists(String registrationNumber) {
                for (Vehicle vehicle : VEHICLES) {
                        if (vehicle.getRegistrationNumber().equalsIgnoreCase(registrationNumber.trim())) {
                                return true;
                        }
                }
                return false;
        }

        private static String readRequiredText(String prompt) {
                while (true) {
                        System.out.print(prompt + " (ketik /b untuk batal): ");
                        String value = INPUT.nextLine().trim();
                        if (value.equalsIgnoreCase("/b")) {
                                return null;
                        }
                        if (!value.isEmpty()) {
                                return value;
                        }
                        System.out.println("Input tidak boleh kosong.");
                }
        }

        private static Integer readInt(String prompt, int minimum, int maximum) {
                while (true) {
                        System.out.print(prompt + " (ketik /b untuk kembali): ");
                        String value = INPUT.nextLine().trim();
                        if (value.equalsIgnoreCase("/b")) {
                                return null;
                        }

                        try {
                                int number = Integer.parseInt(value);
                                if (number >= minimum && number <= maximum) {
                                        return number;
                                }
                        } catch (NumberFormatException exception) {
                                System.out.println("Masukkan angka yang valid.");
                                continue;
                        }

                        System.out.println("Masukkan angka antara " + minimum + " dan " + maximum + ".");
                }
        }

        private static String formatRupiah(int amount) {
                return String.format("Rp %,d", amount).replace(',', '.');
    }
}