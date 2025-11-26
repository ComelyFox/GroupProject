package base;

import java.util.List;
import java.util.Random;

/**
 * Модели для рандомного заполнения
 */
public enum BusModel {
    MERCEDES("Mercedes"),
    VOLVO("Volvo"),
    SCANIA("Scania"),
    MAN("MAN"),
    PAZ("ПАЗ"),
    LIAZ("ЛИАЗ"),
    BARN("САРАЙ"),
    HYUNDAI("Hyundai"),
    MITSUBISHI("Mitsubishi"),
    GAZELLE("Газель");

    private final String displayName;
    private static final List<BusModel> VALUES = List.of(values());
    private static final Random RANDOM = new Random();

    BusModel(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static BusModel randomModel() {
        return VALUES.get(RANDOM.nextInt(VALUES.size()));
    }
}
