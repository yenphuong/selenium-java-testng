package webApp;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listener.ReportNGListener;

@Listeners(ReportNGListener.class)
public class Administrator {

	@Test(groups = "web")
	public void Admin_01_New_Author() {
	}

	@Test(groups = "web", dependsOnMethods = "Admin_01_New_Author")
	public void Admin_02_View_Author() {
	}

	@Test(groups = "web", dependsOnMethods = {"Admin_01_New_Author", "Admin_02_View_Author"})
	public void Admin_03_Edit_Author() {
		Assert.assertFalse(true);
	}

	@Test(groups = "web", dependsOnMethods = "Admin_03_Edit_Author")
	public void Admin_04_Delete_Author() {
	}

}