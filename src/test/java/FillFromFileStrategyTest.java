import org.junit.jupiter.api.Test;
import service.fillCollection.fillStrategy.file.FillFromFileStrategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FillFromFileStrategyTest {

    @Test
    //Тест на валидные данные
    void testValidFile() throws IOException {
        Path temp = Files.createTempFile("buses", ".txt");
        Files.writeString(temp,
                "HG342KH777, B832, 6124\n" +
                        "JD909LK44, Bt99, 94323\n");

        FillFromFileStrategy strategy = new FillFromFileStrategy(temp.toString());
        List<HashMap<String, Object>> result = strategy.getFillCollection();

        assertNotNull(result);
        assertEquals(2, result.size());

        //Они обе одинаковые, проверяется содержание последней строки
        HashMap<String, Object> map = result.get(0);

        assertEquals("JD909LK44", map.get("number"));
        assertEquals("Bt99", map.get("model"));
        assertEquals(94323, map.get("mileage"));
    }

    @Test
    //Файл не существует
    void testFileNotExists() {
        FillFromFileStrategy strategy = new FillFromFileStrategy("no_such_file.txt");
        List<HashMap<String, Object>> result = strategy.getFillCollection();

        assertNull(result);
    }

    @Test
    //Невалидный формат строки
    void testInvalidFormatLine() throws IOException {
        Path temp = Files.createTempFile("invalid", ".txt");
        Files.writeString(temp,
                "Too,few\n" +           // 2 поля - пропускается
                        "A,B,C,Extra\n"        // 4 поля - пропускается
        );

        FillFromFileStrategy strategy = new FillFromFileStrategy(temp.toString());
        List<HashMap<String, Object>> result = strategy.getFillCollection();

        // все строки невалидны - list пустой
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    //Третье поле - не число
    void testInvalidMileage() throws IOException {
        Path temp = Files.createTempFile("invalidMileage", ".txt");
        Files.writeString(temp,
                "HG342KH777, B832, gg12\n"  // mileage не число - пропуск
        );

        FillFromFileStrategy strategy = new FillFromFileStrategy(temp.toString());
        List<HashMap<String, Object>> result = strategy.getFillCollection();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    //Тест на пустой файл
    void testEmptyFile() throws IOException {
        Path temp = Files.createTempFile("empty", ".txt");

        FillFromFileStrategy strategy = new FillFromFileStrategy(temp.toString());
        List<HashMap<String, Object>> result = strategy.getFillCollection();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}