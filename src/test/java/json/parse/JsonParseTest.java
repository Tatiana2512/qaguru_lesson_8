package json.parse;

import com.google.gson.Gson;
import json.models.MenuJson;
import org.junit.jupiter.api.Test;

import java.io.FileReader;

public class JsonParseTest {
    @Test
    public void TestTest() throws Exception{

        System.out.println(jsonParse("src/test/resources/example.json").toString());
        }

    public MenuJson jsonParse(String filepath) throws Exception {

        try( FileReader file = new FileReader(filepath)){
            Gson gson = new Gson();
            MenuJson model = gson.fromJson(file, MenuJson.class);
            return model;
        }

    }

}
