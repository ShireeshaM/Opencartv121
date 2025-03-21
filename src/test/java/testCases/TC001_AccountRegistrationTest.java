package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups = {"Regression", "Master"})
	public void verify_account_registration() {

		try 
		{
		logger.info("**** Starting TC001_AccountRegistrationTest *****");

		HomePage hp = new HomePage(driver);

		hp.clickMyAccount();
		logger.info("Clicked on my account link..");

		hp.clickRegister();
		logger.info("Clicked on register link");

		AccountRegistrationPage regPage = new AccountRegistrationPage(driver);

		logger.info("Providing customer deatils...");
		regPage.setFirstName(randomeString().toUpperCase());
		regPage.setLastName(randomeString().toUpperCase());
		regPage.setEmail(randomeString() + "@gmail.com");
		regPage.setTelephone(randomeNumber());

		String password = randomeAlphaNumeric();

		regPage.setPassword(password);
		regPage.setConfirmPassword(password);

		regPage.setPrivacyPolicy();
		regPage.clickContinue();

		logger.info("Validating Expected message...");
		String confmsg = regPage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test Failed...");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
			
		}
//		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e) {
			
			Assert.fail();
		}
		logger.info("**** Finished TC001_AccountRegistrationTest *****");

	}

}
