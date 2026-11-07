package otomotif;

// Menyimpan data mobil dan menghitung biaya yang berlaku untuk mobil.
public class Car extends Vehicle implements Insurable, Taxable {
    private int seatCount;
    private int engineCapacityCc;

    public Car(String registrationNumber, String brand, String model) {
        this(registrationNumber, brand, model, 0, 5, 1500);
    }

    public Car(
            String registrationNumber,
            String brand,
            String model,
            int mileage,
            int seatCount,
            int engineCapacityCc) {
        super(registrationNumber, brand, model, mileage);
        setSeatCount(seatCount);
        setEngineCapacityCc(engineCapacityCc);
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        if (seatCount < 1) {
            throw new IllegalArgumentException("Jumlah kursi harus lebih dari 0.");
        }
        this.seatCount = seatCount;
    }

    public int getEngineCapacityCc() {
        return engineCapacityCc;
    }

    public void setEngineCapacityCc(int engineCapacityCc) {
        if (engineCapacityCc < 1) {
            throw new IllegalArgumentException("Kapasitas mesin harus lebih dari 0.");
        }
        this.engineCapacityCc = engineCapacityCc;
    }

    @Override
    public String getVehicleType() {
        return "Mobil";
    }

    @Override
    public String getDetails() {
        return String.format(
                "%s | Kursi: %d | Kapasitas mesin: %d cc",
                super.getDetails(), seatCount, engineCapacityCc);
    }

    @Override
    public String performService() {
        if (isServiceDue()) {
            completeService();
            return getServiceMessage("Servis") + " selesai. Status servis: tidak perlu servis.";
        } else {
            return getServiceMessage("Servis") + " belum perlu dilakukan.";
        }
    }

    @Override
    public int calculateInsurancePremium() {
        return 500000 + (engineCapacityCc * 100);
    }

    @Override
    public int calculateAnnualTax() {
        return 300000 + (engineCapacityCc * 100);
    }
}