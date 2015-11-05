package fishandfriends

import org.openqa.selenium.firefox.FirefoxDriver

/*
	This is the Geb configuration file.

	See: http://www.gebish.org/manual/current/configuration.html
*/


driver = {
    //set the firefox locale to 'en-us' since the tests expect english
    //see http://stackoverflow.com/questions/9822717 for more details
    def driverInstance = new FirefoxDriver()
    driverInstance.manage().window().maximize()
    driverInstance
}

baseNavigatorWaiting = true
atCheckWaiting = true