package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.service.util.ExceptionUtil;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

import Keywords.TestReport;


public class WrapperUtilities {
	
	public static ThreadLocal<ExtentTest> logger  =  new ThreadLocal<ExtentTest>();
	public static ExtentReports extent = null;
	public static ThreadLocal<Playwright> playwright =  new ThreadLocal<>();
	public static ThreadLocal<Browser> browser = new ThreadLocal<Browser>();;
	public static ThreadLocal<BrowserContext> browser_context  =  new ThreadLocal<>();
	public static ThreadLocal<Page> tab  =  new ThreadLocal<>();
	public static String TracesDirectory = System.getProperty("user.dir")+"/Traces";
	public static ThreadLocal<String> URL  = new ThreadLocal<String>();
	public static String pathInsideProject;
	public static ThreadLocal<String> threadDataSheetName  = new ThreadLocal<String>();
	
	//-------------------Browser Utilities------------------------
	
	public static Browser getBrowser(ThreadLocal<Browser> browser, ThreadLocal<ExtentTest> logger) {
		
		return browser.get();
		
	}
	
	public static BrowserContext getBrowserContext(ThreadLocal<BrowserContext> browser_context, ThreadLocal<ExtentTest> logger) {
		
		return browser_context.get();
		
	}
	
	 public static Page getTab(ThreadLocal<Page> tab, ThreadLocal<ExtentTest> logger) {
		
		return tab.get();
		
	}
	 
	 public static void OpenBrowser(ThreadLocal<Browser> browser, ThreadLocal<ExtentTest> logger) {
		 
		   browser.set(BrowserConfig.getBrowser(logger));
	 }
	 
	 public static void OpenBrowserContext(ThreadLocal<BrowserContext> browser_context, ThreadLocal<Browser> browser, ThreadLocal<ExtentTest> logger) {
		 
		   browser_context.set(getBrowser(browser, logger).newContext());
	 }
	 
	 public static void OpenTab(ThreadLocal<Page> tab, ThreadLocal<BrowserContext> browser_context, ThreadLocal<ExtentTest> logger) {
		 
		   tab.set(getBrowserContext(browser_context, logger).newPage());
	 }
	 
	 public static void CloseBrowser(ThreadLocal<Browser> browser, ThreadLocal<ExtentTest> logger) {
		 
		   browser.get().close();
	 }
	 
	 public static void CloseBrowserContext(ThreadLocal<BrowserContext> browser_context, ThreadLocal<ExtentTest> logger) {
		 
		   browser_context.get().close();
	 }
	 
	 public static void CloseTab(ThreadLocal<Page> tab, ThreadLocal<ExtentTest> logger) {
		 
		   tab.get().close();
	 }
	
	public String getURL(ThreadLocal<ExtentTest> logger) {
		
		try {
			
		TestReport.Log(logger, "URL is: "+URL.get());
		return URL.get();
		
		}catch(Exception e) {
			
			TestReport.Log(logger, "Cannot get URL"+" -"+tab.get());
			TestReport.Fail(logger, ExceptionUtil.getStackTrace(e));
			Assert.fail();
			return null;
		}
		
	}
	
	public void setURL(ThreadLocal<ExtentTest> logger) {
		
		try {
			
		
		URL.set(System.getProperty("URL"));
		
		}catch(Exception e) {
			
			TestReport.Fail(logger, ExceptionUtil.getStackTrace(e));
			Assert.fail();
		}
		
	}
	
	//---------------------Playwright Utilities-----------------
	
	public void closePlayright(ThreadLocal<ExtentTest> logger) {
		
		try {
			
			playwright.get().close();
			TestReport.Pass(logger, "Gracefully Exited Playwright Server");
			
		}catch(Exception e)
		{
			TestReport.Log(logger, "Problems in exiting Playwright server");
			TestReport.Fail(logger, ExceptionUtil.getStackTrace(e));
		}
		
	}
	
	public void StartRecording(ThreadLocal<BrowserContext> browser_context, ThreadLocal<ExtentTest> logger) {
		
		try {
			
			browser_context.get().tracing().start(new Tracing.StartOptions()
					  .setScreenshots(true)
					  .setSnapshots(true)
					  .setSources(true));
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void StopRecording(ThreadLocal<BrowserContext> browser_context, String TestName, ThreadLocal<ExtentTest> logger) {
			
			try {
				
				String timestamp = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss").format(new Date());
				
				browser_context.get().tracing().stop(new Tracing.StopOptions()
						  .setPath(Paths.get(TracesDirectory+"/"+TestName+"_"+timestamp)));
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
	}
	
	//-------------------I/O Utilities----------------------------
	
	public static String getPathCommon() throws URISyntaxException {
		pathInsideProject = new File("").getAbsolutePath();
		return pathInsideProject;
	}
	
	public static void setThreadDataSheetName(String DataSheetName) {
		threadDataSheetName.set(DataSheetName);
	}
	
	public static String getThreadDataSheetName() {
		return threadDataSheetName.get();
	}
	
	 public static String getParameterFromInputSheet(String sheetName, String parameter, int rowNum, int headerrow) {
			String value = null;
			
			String FileName = getThreadDataSheetName();
			try {
				String path = getPathCommon();
				ThreadLocal<FileInputStream> file = new ThreadLocal<FileInputStream>();
				file.set( new FileInputStream(new File(path + "\\Datafiles\\" + FileName)));

				ThreadLocal<XSSFWorkbook> workbook = new ThreadLocal<XSSFWorkbook>();
				workbook.set(new XSSFWorkbook(file.get()));
				ThreadLocal<XSSFSheet> sheet = new ThreadLocal<XSSFSheet>();
						sheet.set(workbook.get().getSheet(sheetName));
						
				int paramCol = -1;
				Iterator<Cell> cellIterator = sheet.get().getRow(headerrow).cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = (Cell) cellIterator.next();
					try {
						if (cell.getStringCellValue().equals(parameter))
							paramCol = cell.getColumnIndex();
					} catch (Exception e) {
					}
				}
				try {
					value = sheet.get().getRow(rowNum).getCell(paramCol).getStringCellValue();
				} catch (Exception e) {
				}
				file.get().close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Please verify the Data sheet, and the path where it is saved are correct");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return value;
		}
	
	 public static void SetParameterFromInputSheet(String sheetName, String parameter, int rowNum, int headerrow,
				String Value) {
			{
				try {
					String FileName = getThreadDataSheetName();

					String path = getPathCommon();
					ThreadLocal<FileInputStream> file = new ThreadLocal<FileInputStream>();
					file.set( new FileInputStream(new File(path + "\\Datafiles\\" + FileName)));

					ThreadLocal<XSSFWorkbook> workbook = new ThreadLocal<XSSFWorkbook>();
					workbook.set(new XSSFWorkbook(file.get()));
					ThreadLocal<XSSFSheet> sheet = new ThreadLocal<XSSFSheet>();
					sheet.set(workbook.get().getSheet(sheetName));

					int paramCol = -1;
					Iterator<Cell> cellIterator = sheet.get().getRow(headerrow).cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = (Cell) cellIterator.next();
						try {
							if (cell.getStringCellValue().equals(parameter))
								paramCol = cell.getColumnIndex();
						} catch (Exception e) {
						}
					}
					try {
						ThreadLocal<XSSFRow> row1 = new ThreadLocal<XSSFRow>();
						row1.set(sheet.get().getRow(rowNum));
						ThreadLocal<XSSFCell> cellA1 = new ThreadLocal<XSSFCell>();
						cellA1.set(row1.get().createCell(paramCol));
						cellA1.get().setCellValue(Value);
					} catch (Exception e) {
					}
					FileOutputStream out = new FileOutputStream(new File(path + "//Datafiles//" + FileName));

					workbook.get().write(out);
					out.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					System.out.println("Please verify the Data sheet, and the path where it is saved are correct");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		}

}
