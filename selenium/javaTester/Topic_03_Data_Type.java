package javaTester;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_03_Data_Type {
	public static void main(String[] args) {
//	String projectPath = System.getProperty("user.dir");
//	System.out.println(projectPath);
//	
//	String osName = System.getProperty("os.name");
//	System.out.println(osName);
	
	// Thông tin của 1 nhân viên
	// Tên/ tuổi/ ngày tháng năm sinh/ giới tính/ quê quán/ lương
	
	// Ánh xạ cái thông tin này vào trong lập trình/ phần mềm
	byte bNumber = 5;
	
	short sNumber = 500;
	
	int iNumber = 6000;
	
	long lNumber = 1234931245;
	
	float salary = 15.5f;
	
	double point = 9.8d;
	// Kí tự: char
	// Dấu nháy đơn ''
	// Chưa duy nhất 1 kí tự
	char a = 'a';
	
	//Logic: boolean
	boolean marriedStatus = true;
	marriedStatus = false;
	
	// II - Kiểu dữ liệu tham chiếu (Reference)
	// Chuỗi: String (Chữ/ số/ kí tự đặc biệt/...)
	// Dấu nháy đôi
	String emailInvalid = "àc@345@gmail.com";
	
	// Class/ Interface (DateTime)
	Date date = new Date();
	
	WebDriver driver = new FirefoxDriver();
	
	// Đối tượng: Object
	Object students;
	
	// Array: Mảng (Khai báo số lượng dữ liệu trước) - Cố định số lượng
	int numbers[] = {15, 20, 45};
	String addresses[] = {"Da Nang", "Ha Noi", "HCM"};
	
	// List/ Set/ Queue (Collection) - Động
	List<Integer> studentNumber = new ArrayList<Integer>();
	List<String> studentAddress = new ArrayList<String>();
	
	Set<String> studentCity = new LinkedHashSet<String>();
			
			
			
			
			
			
			
	}
}