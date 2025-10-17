import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Тесты на успешное чтение содержимого файлов внутри Zip архива")
public class ZipfileReadingTest {


    private final ClassLoader cl = getClass().getClassLoader();

    @Test
    @DisplayName("Успешное чтение PDF файла внутри Zip архива Homework.zip")
    void successfullyReadingPdfFileInsideZipArchive() throws Exception{
        try(ZipInputStream zis = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream("zip/Homework.zip")))) {

            ZipEntry entry;
            boolean pdfFound = false;

            while ((entry = zis.getNextEntry()) != null){
                if (entry.getName().equals("Sample.pdf")){
                    pdfFound = true;
                    PDF pdfFile = new PDF(zis);
                    assertTrue(pdfFile.text.contains("Lorem ipsum"));
                    assertTrue(pdfFile.text.contains("In eleifend velit vitae libero sollicitudin euismod"));
                }
            }
            assertTrue(pdfFound, "Файл Sample.pdf не найден в архиве");
        }
    }


    @Test
    @DisplayName("Успешное чтение XLSX файла внутри Zip архива Homework.zip")
    public void successfullyReadingXlsxFileInsideZipArchive() throws Exception{
        try(ZipInputStream zis = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream("zip/Homework.zip")))) {

            ZipEntry entry;
            boolean xlsxFound = false;

            while ((entry = zis.getNextEntry()) != null){
                if (entry.getName().equals("Sample.xlsx")){
                    xlsxFound = true;
                    XLS xlsFile = new XLS(zis);
                    Assertions.assertEquals("France",
                            xlsFile.excel.getSheetAt(0)
                                    .getRow(69)
                                    .getCell(4)
                                    .getStringCellValue());
                }
            }
            assertTrue(xlsxFound, "Файл Sample.xlsx не найден в архиве");
        }
    }


    @Test
    @DisplayName("Успешное чтение CSV файла внутри Zip архива Homework.zip")
    public void successfullyReadingCSVFileInsideZipArchive() throws Exception{
        try(ZipInputStream zis = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream("zip/Homework.zip")))) {

            ZipEntry entry;
            boolean csvFound = false;

            while ((entry = zis.getNextEntry()) != null){
                if (entry.getName().equals("Sample.csv")){
                    csvFound = true;
                    CSVReader csvFile = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = csvFile.readAll();
                    assertEquals("rgb(50,0,50)", content.get(16)[2]);
                }
            }
            assertTrue(csvFound, "Файл Sample.csv не найден в архиве");
        }
    }
}
