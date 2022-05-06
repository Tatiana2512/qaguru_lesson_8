package json.parse;

import com.google.gson.Gson;
import json.models.MenuJson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.FileReader;

public class GsonJsonParseTest {

    @Test
    public void simpleJsonTest() throws Exception {
        MenuJson dataSimple = jsonParse("src/test/resources/example1.json");

        Assertions.assertThat(dataSimple.getMenu().getPopup()).isNull();
        Assertions.assertThat(dataSimple.getMenu().getId()).isEqualTo("file");
        Assertions.assertThat(dataSimple.getMenu().getValue()).isEqualTo("File");
    }

    @Test
    public void normalJsonTest() throws Exception {
        MenuJson data = jsonParse("src/test/resources/example.json");

        Assertions.assertThat(data.getMenu().getPopup().getMenuitem().get(0).getValue()).isEqualTo("New");
        Assertions.assertThat(data.getMenu().getPopup().getMenuitem().get(0).getOnclick()).startsWith("Create");
    }

    public MenuJson jsonParse(String filepath) throws Exception {

        try (FileReader file = new FileReader(filepath)) {
            Gson gson = new Gson();
            MenuJson model = gson.fromJson(file, MenuJson.class);
            return model;
        }
    }
}
