import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Menu;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONReadingTest {

    private final ClassLoader cl =getClass().getClassLoader();
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Успешное чтение JSON файла средставми библиотеки Jackson")
    public void successfullyReadingJSONFileViaJackson() throws Exception{
        try(InputStream stream = cl.getResourceAsStream("RestaurantMenu.json")){
            Menu menu = mapper.readValue(stream,Menu.class);
            assertThat(menu.getTitle())
                    .isEqualTo("Cute italian restaurant menu");
            assertThat(menu.getSeason())
                    .isEqualTo("Autumn");
            assertThat(menu.getDishes())
                    .containsKey("Risotto with white mushrooms");
            assertThat(menu.getDishes().get("Ribeye steak").getIngredients().get(1)).isEqualTo("Butter");
        }
    }
}
