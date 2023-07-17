package annotation;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Annotation {
  @Test
  public void f() {
  }
  @BeforeMethod
  public void beforeMethod() {
  }
  // method là 1 testcase

  @AfterMethod
  public void afterMethod() {
  }

  @BeforeClass
  public void beforeClass() {
  }
  // class là theo tên class
  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }
  // test, ví dụ test trên firefox, test trên chrome...
  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }
  // suit thì kiểu như tên dự án
  @AfterSuite
  public void afterSuite() {
  }

}
