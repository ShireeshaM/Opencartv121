package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven") // getting data from different class
	public void verify_loginDDT(String email, String pwd, String exp) {

		logger.info("*** starting TC_003_LoginDDT ****");

		try {
			// Home page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// Login page
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();

			// My Account
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPage = macc.isMyAccountPageExists();

			if (exp.equalsIgnoreCase("valid"))
			{
				if (targetPage == true) 
				{
					macc.clickMyAccountLogout();
					Assert.assertTrue(true);
				} 
				else
				{
					Assert.assertTrue(false);
				}
			}

			if (exp.equalsIgnoreCase("Invalid")) 
			{
				if (targetPage == true) 
				{
					macc.clickMyAccountLogout();
					Assert.assertTrue(false);
				} 
				else 
				{
					Assert.assertTrue(true);
				}
			}

		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("*** Finished TC_003_LoginDDT ****");

	}

}
