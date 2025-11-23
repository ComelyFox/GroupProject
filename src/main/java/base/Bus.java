package base;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return serialNumber == bus.serialNumber && mileage == bus.mileage && Objects.equals(model, bus.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, serialNumber, mileage);
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
