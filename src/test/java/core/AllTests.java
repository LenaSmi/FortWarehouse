package core;
//comment: I tested a new changes

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddOrderPageTest.class, HomePageTest.class,
		LoginPageTest.class, OrderSearchPageTest.class,
		OrderUploadPageTest.class, ShippingOrderSearchPageTest.class })
public class AllTests {

}
