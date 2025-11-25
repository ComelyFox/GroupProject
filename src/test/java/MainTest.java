import org.junit.jupiter.api.Test;
import app.Main;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void testShowMainMenu() {
        // Проверка, что метод showMainMenu не падает
        assertDoesNotThrow(Main::showMeinMenu);
    }

    @Test
    void testShowDataAndSortMethods() {
        // Методы showData и sort временные, проверка на отсутствие ошибок
        assertDoesNotThrow(Main::showData);
        assertDoesNotThrow(Main::sort);
    }
}