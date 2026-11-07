package otomotif;

// Menyimpan data umum kendaraan dan aturan servis.
public abstract class Vehicle implements Serviceable {
    public static final int SERVICE_INTERVAL_KM = 5000;

    private final String registrationNumber;
    private String brand;
    private String model;
    private int mileage;
    private int lastServiceMileage;

    protected Vehicle(String registrationNumber, String brand, String model) {
        this(registrationNumber, brand, model, 0);
    }

    protected Vehicle(String registrationNumber, String brand, String model, int mileage) {
        this.registrationNumber = requireText(registrationNumber, "Nomor registrasi");
        setBrand(brand);
        setModel(model);
        setMileage(mileage);
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = requireText(brand, "Merek");
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = requireText(model, "Model");
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        if (mileage < 0) {
            throw new IllegalArgumentException("Jarak tempuh tidak boleh negatif.");
        }

        this.mileage = mileage;
    }

    public boolean isServiceDue() {
        return mileage - lastServiceMileage >= SERVICE_INTERVAL_KM;
    }

    public int getLastServiceMileage() {
        return lastServiceMileage;
    }

    protected void setLastServiceMileage(int lastServiceMileage) {
        if (lastServiceMileage < 0 || lastServiceMileage > mileage) {
            throw new IllegalArgumentException("Jarak servis terakhir tidak valid.");
        }
        this.lastServiceMileage = lastServiceMileage;
    }

    protected void completeService() {
        setLastServiceMileage(mileage);
    }

    public abstract String getVehicleType();

    @Override
    public abstract String performService();

    public String getDetails() {
        String serviceStatus = isServiceDue() ? "Perlu servis" : "Belum perlu servis";
        return String.format(
                "%s: %s %s | Plat: %s | Jarak: %d km | Status: %s",
                getVehicleType(), brand, model, registrationNumber, mileage, serviceStatus);
    }

    protected String getServiceMessage(String result) {
        return String.format("%s %s %s", result, brand, model);
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " wajib diisi.");
        }
        return value.trim();
    }
}