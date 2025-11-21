package base;

public class Bus {
    private String model;
    private int serialNumber;
    private int mileage;

    private Bus(BusBuilder builder) {
        this.model = builder.model;
        this.serialNumber = builder.serialNumber;
        this.mileage = builder.mileage;
    }

    public String getModel() {
        return model;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "model='" + model + '\'' +
                ", number=" + serialNumber +
                ", mileage=" + mileage +
                '}';
    }

    public static class BusBuilder {
        private String model;
        private int serialNumber;
        private int mileage;

        public BusBuilder setModel(String model) {
            if (model == null || model.trim().isEmpty()) {
                throw new IllegalArgumentException("У автобуса должна быть модель");
            }
            this.model = model.trim();
            return this;
        }

        public BusBuilder setSerialNumber(int serialNumber) {
            if (serialNumber < 0) {
                throw new IllegalArgumentException("Серийный номер не может быть отрицательным");
            }
            this.serialNumber = serialNumber;
            return this;
        }

        public BusBuilder setMileage(int mileage) {
            if (mileage < 0) {
                throw new IllegalArgumentException("Пробег не может быть отрицательным");
            }
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            return new Bus(this);
        }
    }
}
