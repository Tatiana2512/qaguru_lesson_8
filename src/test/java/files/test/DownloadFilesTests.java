package files.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class DownloadFilesTests {
    @Test
    @DisplayName("Download file test")
    @Owner("Tatiana Belotserkovskaia")
    @Severity(SeverityLevel.BLOCKER)
    public void selenideDownloadTest() throws Exception {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Open page", () -> {
            Selenide.open("https://github.com/qa-guru/knowledge-base/blob/main/README.md");
        });

        step("Download file and search for the text", () -> {
            File file = Selenide.$("#raw-url").download();
            try (InputStream stream = new FileInputStream(file)) {
                assertThat(new String(stream.readAllBytes(), StandardCharsets.UTF_8).contains("База знаний QA.GURU"));

            }
        });
    }
}


