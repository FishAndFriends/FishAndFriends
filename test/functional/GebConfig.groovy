import org.openqa.selenium.firefox.FirefoxDriver

/*
	This is the Geb configuration file.

	See: http://www.gebish.org/manual/current/configuration.html
*/

driver = {
    def driverInstance = new FirefoxDriver()
    driverInstance.manage().window().maximize();

    driverInstance
}

waiting = {
    timeout = 120
    retryInterval = 1.0
}

baseNavigatorWaiting = true
atCheckWaiting = true