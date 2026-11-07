package otomotif;

// Menyimpan data motor dan menghitung premi asuransinya.
public class Motorcycle extends Vehicle implements Insurable {
    private int engineCapacityCc;

    public Motorcycle(String registrationNumber, String brand, String model) {
        this(registrationNumber, brand, model, 0, 150);
    }

    public Motorcycle(
            String registrationNumber,
            String brand,
            String model,
            int mileage,
            int engineCapacityCc) {
        super(registrationNumber, brand, model, mileage);
        setEngineCapacityCc(engineCapacityCc);
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
        return "Motor";
    }

    @Override
    public String getDetails() {
        return String.format(
                "%s | Kapasitas mesin: %d cc",
                super.getDetails(), engineCapacityCc);
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
        return 200000 + (engineCapacityCc * 50);
    }
}