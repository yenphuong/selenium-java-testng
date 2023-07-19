package webApp;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Customer {

	@Test(groups = "web")
	public void Customer_01_New_Customer() {
	}

	@Test(groups = "web")
	public void Customer_02_View_Customer() {
	}

	@Test(groups = "web")
	public void Customer_03_Edit_Customer() {
		Assert.assertFalse(true);
	}

	@Test(groups = "web")
	public void Customer_04_Delete_Customer() {
	}

}