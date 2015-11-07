grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.war.resources = { stagingDir ->
    delete(file:"${stagingDir}/WEB-INF/lib/DisableOptimizationsTransformation-0.1-SNAPSHOT.jar")
}

//grails.project.fork = [
//    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
//    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
//
//    // configure settings for the test-app JVM, uses the daemon by default
//    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
//    // configure settings for the run-app JVM
//    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
//    // configure settings for the run-war JVM
//    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
//    // configure settings for the Console UI JVM
//    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
//]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }

    dependencies {
        // Latest httpcore and httpmime for Coveralls plugin
        build 'org.apache.httpcomponents:httpcore:4.3.2'
        build 'org.apache.httpcomponents:httpclient:4.3.2'
        build 'org.apache.httpcomponents:httpmime:4.3.3'
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        // runtime 'mysql:mysql-connector-java:5.1.27'
        // runtime 'org.postgresql:postgresql:9.3-1100-jdbc41'
        test "org.grails:grails-datastore-test-support:1.0-grails-2.3"
        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
        runtime 'postgresql:postgresql:8.4-702.jdbc3'

        test "org.gebish:geb-spock:0.12.2"
        test "org.seleniumhq.selenium:selenium-remote-driver:2.45.0"
        test "org.seleniumhq.selenium:selenium-firefox-driver:2.45.0"
//        test( "com.github.detro.ghostdriver:phantomjsdriver:1.0.1" ) {
//            transitive = false
//        }

        compile 'org.codehaus.groovy:groovy-backports-compat23:2.4.5'
    }

    plugins {
        // plugins for the build system only
        build ":tomcat:7.0.54"

        // plugins for the compile step
        compile ":scaffolding:2.0.3"
        compile ':cache:1.1.7'
        compile ":codenarc:0.24.1"
        compile ":coveralls:0.1.3"
        compile ":rest-client-builder:2.1.1"
        compile ":recaptcha:1.6.0"

        // plugins needed at runtime but not for compilation
        runtime ":hibernate:3.6.10.16" // or ":hibernate4:4.3.5.4"
        runtime ":database-migration:1.4.0"
        runtime ":jquery:1.11.1"
        runtime ":resources:1.2.8"
        runtime ':twitter-bootstrap:3.3.5'


        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0.1"
        //runtime ":cached-resources:1.1"
        //runtime ":yui-minify-resources:0.1.5"

        // An alternative to the default resources plugin is the asset-pipeline plugin
        //compile ":asset-pipeline:1.6.1"

        // Uncomment these to enable additional asset-pipeline capabilities
        //compile ":sass-asset-pipeline:1.5.5"
        //compile ":less-asset-pipeline:1.5.3"
        //compile ":coffee-asset-pipeline:1.5.0"
        //compile ":handlebars-asset-pipeline:1.3.0.1"

        test ":code-coverage:2.0.3-3"
        test(":spock:0.7") {
            exclude "spock-grails-support"
        }

        test("org.grails.plugins:geb:0.12.2")
    }

}
// Coverage
coverage {
    exclusions = ['**/test/**']
}

// Codenarc
codenarc.reports = {
    MyXmlReport('xml') {                       // The report name "MyXmlReport" is user-defined; Report type is 'xml'
        outputFile = 'target/CodeNarcReport.xml'      // Set the 'outputFile' property of the (XML) Report
        title = 'CodeNarc report'  // Set the 'title' property of the (XML) Report
    }

    MyHtmlReport('html') {                     // Report type is 'html'
        outputFile = 'target/CodeNarcReport.html'
        title = 'CodeNarc report'
    }
}