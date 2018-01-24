package test.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private Iterator<Row> rows = null;

	public ExcelReader(String file) {
		Workbook workbook = null;

		try {
			FileInputStream excelFile = new FileInputStream(new File(file));
			workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			rows = datatypeSheet.iterator();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<String> readRow() {

		if (rows == null)
			return Collections.emptyList();

		Row currentRow = rows.next();
		List<String> values = new ArrayList<String>();

		currentRow.cellIterator();
		Iterator<Cell> cells = currentRow.iterator();

		while (cells.hasNext()) {
			values.add(cells.next().getStringCellValue());
		}

		return values;
	}

	public boolean endFile() {
		if (rows == null)
			return false;
		return !rows.hasNext();
	}

}
