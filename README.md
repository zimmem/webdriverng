webdriverng
===========

*Integration webdriver and testng*

## Use with maven

	<dependency>
	  <groupId>com.zimmem</groupId>
	  <artifactId>webdriverng</artifactId>
	  <version>1.0.1</version>
	</dependency>

## WebDriver Manage
### Sample webdriver config: driver.yaml

	--- !com.zimmem.webdriverng.WebDriverConfig
	
	# broswer name. like "internet explorer", "firefox", "chrome"
	browser: internet explorer
	
	# run with selenium server. false for run on local.
	remote: false
	
	#selenium remote hub url. effective when remote is true.
	hubUrl: 
	
	#plateform. like "windows", "xp", "windows vista", "windows 7", "linux" and so on. leave empty for any.
	platform: windows
	
	# the broswer verson. leave empty for any version
	version: 
	
	# how many seconds for driver to find elements;
	wait: 10

### How webdriverng find driver.yaml?

- The path with java system property : webdriver.config , you can add `-Dwebdriver.config=/path/to/driver.yaml` when start jvm.
- if driver.yaml can't find with system property, webdriverng will use driver.yaml on root of classpath.

### How to get WebDriver Instance in java.

	import com.zimmem.webdriverng.WebDriverFactory;
	...
	WebDriver currentWebDriver = WebDriverFactory.getCurrentWebDriver();

## WebDriverNg Report
### Generate reports when running test in eclipse. 
No configuration
### Generate reports when running test with maven.
Add surefire plugin configure in pom.xml.

	<plugin>
		<groupId>org.apache.maven.plugins</groupId>
		<artifactId>maven-surefire-plugin</artifactId>
		<configuration>
			<junitArtifactName>com.alibaba.external:test.junit</junitArtifactName>
			<argLine>
				-Dwebdriver.config=${webdriver.config}
			</argLine>
			<parallel>methods</parallel>
			<threadCount>${thread}</threadCount>
			<properties>
				<property>
					<name>usedefaultlisteners</name>
					<value>false</value> <!-- disabling default listeners is optional -->
				</property>
				<property>
					<name>listener</name>
					<value>com.zimmem.webdriverng.WebDriverListener,com.zimmem.webdriverng.report.WebDriverReport,org.testng.reporters.XMLRep
	orter</value>
				</property>
			</properties>
		</configuration>
	</plugin>


## Something about WebDriverListener.

The WebDriverListener complete the following work.

- WebDriver life cycle management. You do not need to quit WebDriver yourself.
- Taking screenshot and collecting WebDriver runtime infomation (current url, page source) when test failed.	