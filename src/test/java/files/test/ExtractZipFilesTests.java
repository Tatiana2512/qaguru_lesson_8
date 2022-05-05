package files.test;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ExtractZipFilesTests {

    ClassLoader classLoader = getClass().getClassLoader();
    String[] csvHeader = {"Username,Identifier,One-time_password,Recovery_code,First_name,Last_name,Department,Location"};

    @Test
    @Owner("Tatiana Belotserkovskaia")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Extract'n'Check PDF file")
    public void zipPdfTest() throws Exception {
        SelenideLogger.addListener("allure", new AllureSelenide());
        ZipFile zf = new ZipFile(new File("src/test/resources/business.zip"));
        ZipInputStream is = new ZipInputStream(classLoader.getResourceAsStream("business.zip"));
        ZipEntry entry;
        while ((entry = is.getNextEntry()) != null) {
            try (InputStream inputStream = zf.getInputStream(entry)) {
                if (getExtension(entry.getName()).equals("pdf")) {
                    PDF pdf = new PDF(inputStream);
                    assertThat(pdf.numberOfPages).isEqualTo(5);
                    assertThat(pdf.encrypted).isFalse();
                }
            }
        }
    }

    @Test
    @Owner("Tatiana Belotserkovskaia")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Extract'n'Check PNG file")
    public void zipPngTest() throws Exception {
        SelenideLogger.addListener("allure", new AllureSelenide());
        SelenideLogger.addListener("allure", new AllureSelenide());
        ZipFile zf = new ZipFile(new File("src/test/resources/business.zip"));
        ZipInputStream is = new ZipInputStream(classLoader.getResourceAsStream("business.zip"));
        ZipEntry entry;
        while ((entry = is.getNextEntry()) != null) {
            try (InputStream inputStream = zf.getInputStream(entry)) {
                if (getExtension(entry.getName()).equals("png")) {
                    BufferedImage img = ImageIO.read(inputStream);
                    Assertions.assertThat(img.getHeight()).isGreaterThan(255);
                    Assertions.assertThat(img.getTransparency()).isEqualTo(Transparency.TRANSLUCENT);
                }
            }

        }
    }


    @Test
    @Owner("Tatiana Belotserkovskaia")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Extract'n'Check XLSX file")
    public void zipXlsxTest() throws Exception {
        SelenideLogger.addListener("allure", new AllureSelenide());
        ZipFile zf = new ZipFile(new File("src/test/resources/business.zip"));
        ZipInputStream is = new ZipInputStream(classLoader.getResourceAsStream("business.zip"));
        ZipEntry entry;
        while ((entry = is.getNextEntry()) != null) {
            try (InputStream inputStream = zf.getInputStream(entry)) {
                if (getExtension(entry.getName()).equals("xlsx")) {
                    XLS xls = new XLS(inputStream);
                    String cellValue = xls.excel.getSheetAt(0).getRow(5).getCell(1).getStringCellValue();
                    assertThat(cellValue).isEqualTo("Nereida");
                    assertThat(xls.excel.getSheetAt(0).getLastRowNum()).isEqualTo(50);
                }
            }
        }
    }

    @Test
    @Owner("Tatiana Belotserkovskaia")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Extract'n'Check CSV file")
    public void zipCsvTest() throws Exception {

        SelenideLogger.addListener("allure", new AllureSelenide());
        ZipFile zf = new ZipFile(new File("src/test/resources/business.zip"));
        ZipInputStream is = new ZipInputStream(classLoader.getResourceAsStream("business.zip"));
        ZipEntry entry;
        while ((entry = is.getNextEntry()) != null) {
            try (InputStream inputStream = zf.getInputStream(entry)) {
                if (getExtension(entry.getName()).equals("csv")) {
                    try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                        List<String[]> content = reader.readAll();
                        Assertions.assertThat(content)
                                .contains(csvHeader);
                        assertThat(content.size()).isEqualTo(6);
                        assertThat(Arrays.toString(content.get(4))).contains("Mary,Jenkins");
                    }
                }
            }
        }
    }

    public static String getExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }
}
