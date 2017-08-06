package base.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;







import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelGenerator {
	private static  final String FILEPATH="D:\\FILES"; 
	static WritableCellFormat companyHeaderFormat = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 12));
	static WritableCellFormat companyAddressFormat = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 9));
	static WritableCellFormat reportNameCellFormat = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10));
	static WritableCellFormat headerCellFormat = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10));
	static WritableCellFormat headerCellFormatR = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10));
	static WritableCellFormat headerCellFormatC = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10));
	static WritableCellFormat stringCellFormatt = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10));
	static WritableCellFormat stringCellFormattR = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10));
	static WritableCellFormat stringTotCellFormatt = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10));
	static WritableCellFormat intValueCellFormat = new WritableCellFormat(new NumberFormat("#0"));
	static WritableCellFormat intTotValueCellFormat = new WritableCellFormat(new NumberFormat("#0"));
	static WritableCellFormat decimalValueCellFormat = new WritableCellFormat(new NumberFormat("#,##0.00"));
	static WritableCellFormat decimalAllTotalCellFormat = new WritableCellFormat(new NumberFormat("#,##0.00"));
	
	public static SimpleDateFormat simpdate=new SimpleDateFormat("dd/MM/yyyy");
	
	public static void initFormats() throws Exception {
		stringCellFormattR = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10));
		companyHeaderFormat = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 12));
		companyAddressFormat = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 9));
		reportNameCellFormat = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10));
		headerCellFormat = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10));
		headerCellFormatR = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10));
		headerCellFormatC = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10));

		stringCellFormatt = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10));
		intValueCellFormat = new WritableCellFormat(new NumberFormat("#0"));
		decimalValueCellFormat = new WritableCellFormat(new NumberFormat("#,##0.00"));
		decimalAllTotalCellFormat = new WritableCellFormat(new NumberFormat("#,##0.00"));
		intTotValueCellFormat = new WritableCellFormat(new NumberFormat("#0"));
		stringTotCellFormatt = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10));

		decimalAllTotalCellFormat.setFont(new WritableFont(WritableFont.ARIAL, 10));
		decimalAllTotalCellFormat.setBackground(Colour.GREY_25_PERCENT);
		decimalAllTotalCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		decimalAllTotalCellFormat.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
		decimalAllTotalCellFormat.setAlignment(Alignment.RIGHT);

		decimalValueCellFormat.setFont(new WritableFont(WritableFont.ARIAL, 10));
		decimalValueCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		decimalValueCellFormat.setAlignment(Alignment.RIGHT);

		intValueCellFormat.setFont(new WritableFont(WritableFont.ARIAL, 10));
		intValueCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		intValueCellFormat.setAlignment(Alignment.RIGHT);

		intTotValueCellFormat.setFont(new WritableFont(WritableFont.ARIAL, 10));
		intTotValueCellFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		intTotValueCellFormat.setBackground(Colour.GREY_25_PERCENT);
		intTotValueCellFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		intTotValueCellFormat.setBorder(Border.TOP, BorderLineStyle.THIN);
		intTotValueCellFormat.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
		intTotValueCellFormat.setAlignment(Alignment.RIGHT);

		stringCellFormatt.setAlignment(Alignment.LEFT);
		stringCellFormatt.setBorder(Border.ALL, BorderLineStyle.THIN);

		stringCellFormattR.setAlignment(Alignment.CENTRE);
		stringCellFormattR.setBorder(Border.ALL, BorderLineStyle.THIN);

		stringTotCellFormatt.setAlignment(Alignment.LEFT);
		stringTotCellFormatt.setBorder(Border.LEFT, BorderLineStyle.THIN);
		stringTotCellFormatt.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		stringTotCellFormatt.setBorder(Border.TOP, BorderLineStyle.THIN);
		stringTotCellFormatt.setBackground(Colour.GREY_25_PERCENT);
		stringTotCellFormatt.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);

		headerCellFormatR.setBorder(Border.ALL, BorderLineStyle.THIN);
		headerCellFormatR.setAlignment(Alignment.RIGHT);

		headerCellFormat.setBackground(Colour.GREY_25_PERCENT);
		headerCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		headerCellFormatC.setBackground(Colour.GREY_25_PERCENT);
		headerCellFormatC.setBorder(Border.ALL, BorderLineStyle.THIN);
		headerCellFormatC.setAlignment(Alignment.CENTRE);

		reportNameCellFormat.setBackground(Colour.GREY_25_PERCENT);
		reportNameCellFormat.setAlignment(Alignment.CENTRE);

		companyAddressFormat.setAlignment(Alignment.CENTRE);
		companyHeaderFormat.setAlignment(Alignment.CENTRE);

	}
	
	public static HttpServletResponse printReport2(HttpServletResponse res, Map<Integer, List<Object>> map1, String[] headarray1 ,String reportName1,String reportDescription, String reportDate) throws Exception {
		initFormats();
		WorkbookSettings settings = new WorkbookSettings();
		settings.setInitialFileSize(1);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		WritableWorkbook workBook = Workbook.createWorkbook(outputStream, settings);
		WritableSheet sheet = workBook.createSheet("SCALP", 0);
		int headerWidth = headarray1.length;
		int sheetNumber = 1;
		sheet.addCell(new Label(0, 1, "Scalp Hair And Beauty Salon", companyHeaderFormat));
		sheet.mergeCells(0, 1, headerWidth - 1, 1);

		sheet.addCell(new Label(0, 2, reportName1.toUpperCase(), reportNameCellFormat));
		sheet.mergeCells(0, 2, headerWidth - 1, 2);
		
		sheet.addCell(new Label(0, 4, reportDescription.toUpperCase(), reportNameCellFormat));
		sheet.mergeCells(0, 4, headerWidth - 1, 4);

		sheet.addCell(new Label(0, 6, "Report Date : ".toUpperCase() + reportDate, headerCellFormat));
		sheet.mergeCells(0, 6, headerWidth - 1, 6);

		for (int j = 0; j < headarray1.length; j++) {
			sheet.addCell(new Label(j, 8, headarray1[j].toUpperCase(), headerCellFormat));
		}
		SheetSettings sheetSettings = sheet.getSettings();
		sheetSettings.setVerticalFreeze(9);
		boolean isTotal = false;
		int line = 9;
		for (Integer i : map1.keySet()) {
			line++;
			if (line > 65000) {
				sheetNumber++;
				sheet = workBook.createSheet("SCALP" + sheetNumber, 0);
				line = 1;
			}
			List<Object> o = map1.get(i);
			int x = 0;
			for (Object object : o) {
				if (object instanceof String) {
					String str = (String) object;
					if (str.equals("Total:")) {
						isTotal = true;
					//Add New Data For BOC Reports
					}else if(str.equals("Total (Excluding Gov. & Gov. Cop)")){
						isTotal = true;
					}else if(str.equals("GRAND TOTAL")){
						isTotal = true;
					}
					sheet.addCell(new Label(x, i + 9, str, "-".equals(str) ? stringCellFormattR : stringCellFormatt));
				} else if (object instanceof Double) {
					Double str = (Double) object;
					sheet.addCell(new jxl.write.Number(x, i + 9, str, (isTotal ? decimalAllTotalCellFormat : decimalValueCellFormat)));
				} else if (object instanceof Integer) {
					Integer str = (Integer) object;
					sheet.addCell(new jxl.write.Number(x, i + 9, str, isTotal ? intTotValueCellFormat : intValueCellFormat));

				} else if (object instanceof Date) {
					Date str = (Date) object;						
					
					sheet.addCell(new Label(x, i + 9, simpdate.format(str), isTotal ? stringTotCellFormatt : stringCellFormatt));
				} else {
					sheet.addCell(new Label(x, i + 9, "", stringCellFormatt));
				}
				x++;
			}
			isTotal = false;
		}
		workBook.write();
		workBook.close();

		res.setContentType("application/vnd.ms-excel");
		res.setHeader("Content-disposition", "inline; filename=" + reportName1.toUpperCase() + ".xls");
		res.setContentLength(outputStream.size());
		res.setHeader("Expires", "0");
		res.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		res.setHeader("Pragma", "public");
		ServletOutputStream stream = res.getOutputStream();
		outputStream.writeTo(stream);
		stream.flush();
		outputStream.close();
		
		return res;
	}
	
	
}
