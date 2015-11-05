import org.openqa.selenium.Capabilities
import org.openqa.selenium.Dimension
import org.openqa.selenium.Platform
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.phantomjs.PhantomJSDriverService
import org.openqa.selenium.remote.DesiredCapabilities

/*
	This is the Geb configuration file.

	See: http://www.gebish.org/manual/current/configuration.html
*/

String phantomJSVersion = '1.9.8'

String platform
String archiveExtension
String execFilePath

if (Platform.current.is(Platform.WINDOWS)) {
    if (phantomJSVersion.startsWith('1')) {
        execFilePath = 'phantomjs.exe'
    } else {
        execFilePath = 'bin/phantomjs.exe'
    }
    platform = 'windows'
    archiveExtension = 'zip'
}
else if (Platform.current.is(Platform.MAC)) {
    execFilePath = '/bin/phantomjs'
    platform = 'macosx'
    archiveExtension = 'zip'
} else if (Platform.current.is(Platform.LINUX)) {
    execFilePath = '/bin/phantomjs'
    platform = 'linux-i686'
    archiveExtension = 'tar.bz2'
} else {
    throw new RuntimeException("Unsupported operating system [${Platform.current}]")
}

String phantomjsExecPath = "phantomjs-${phantomJSVersion}-${platform}/${execFilePath}"

String phantomJsFullDownloadPath = "https://bitbucket.org/ariya/phantomjs/downloads/phantomjs-${phantomJSVersion}-${platform}.${archiveExtension}"

File phantomJSDriverLocalFile = downloadDriver(phantomJsFullDownloadPath, phantomjsExecPath, archiveExtension)

System.setProperty('phantomjs.binary.path', phantomJSDriverLocalFile.absolutePath)
if (Platform.current.is(Platform.LINUX)) {
    System.setProperty('phantomjs.binary.path', '')
}

driver = {
    Capabilities caps = DesiredCapabilities.phantomjs()
    def phantomJsDriver = new PhantomJSDriver(PhantomJSDriverService.createDefaultService(caps), caps)
    phantomJsDriver.manage().window().setSize(new Dimension(1920, 1080))

    return phantomJsDriver
}

private File downloadDriver(String driverDownloadFullPath, String driverFilePath, String archiveFileExtension) {
    File destinationDirectory = new File("target/drivers")
    if (!destinationDirectory.exists()) {
        destinationDirectory.mkdirs()
    }

    File driverFile = new File("${destinationDirectory.absolutePath}/${driverFilePath}")
    println driverFilePath
    String localArchivePath = "target/driver.${archiveFileExtension}"
    new File(localArchivePath).listFiles().each {
        println it.name
    }

    if (!driverFile.exists()) {
        def ant = new AntBuilder()
        ant.get(src: driverDownloadFullPath, dest: localArchivePath)

        if (archiveFileExtension == "zip") {
            ant.unzip(src: localArchivePath, dest: destinationDirectory)
        } else {
            ant.untar(src: localArchivePath, dest: destinationDirectory, compression: 'bzip2')
        }

        ant.delete(file: localArchivePath)
        ant.chmod(file: driverFile, perm: '700')
    }

    return driverFile
}

waiting = {
    timeout = 120
    retryInterval = 1.0
}

baseNavigatorWaiting = true
atCheckWaiting = true