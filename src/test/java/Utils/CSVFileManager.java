package Utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVFileManager {
    private CSVParser csvParser;
    private Iterable<CSVRecord> csvRecords;

    public CSVFileManager(String csvFilePath) {
        try {
            FileReader reader = new FileReader(csvFilePath);

            CSVFormat format = CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build();

            csvParser = format.parse(reader);
            csvRecords = csvParser.getRecords();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load CSV file: " + csvFilePath, e);
        }
    }

    public List<String> getColumnsName() {
        try {
            return new ArrayList<>(csvParser.getHeaderNames());
        } catch (Exception e) {
            throw new RuntimeException("Error reading headers", e);
        }
    }

    public List<String[]> getRows() {
        List<String[]> rows = new ArrayList<>();

        for (CSVRecord record : csvRecords) {
            String[] data = new String[record.size()];
            for (int i = 0; i < data.length; i++) {
                data[i] = record.get(i);
            }
            rows.add(data);
        }
        return rows;
    }

    public Object[][] getDataAsObjectArray() {
        List<String[]> rows = getRows();
        Object[][] data = new Object[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            data[i] = rows.get(i);
        }
        return data;
    }
}
