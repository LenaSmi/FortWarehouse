package core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddOrderPageTest.class, HomePageTest.class,
		LoginPageTest.class, OrderSearchPageTest.class,
		OrderUploadPageTest.class })
public class AllTests {

}
