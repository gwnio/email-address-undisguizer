package io.emailaddress.undisguizer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class EmailAddressUndisguizerTest {

	private EmailAddressUndisguizerTransformer emailAddressTransformer = new EmailAddressUndisguizerTransformer(new EmailAddressUndisguizerSubstituteConfiguration());
	
	private Map<String, String> testCases = new HashMap<String, String>();
	
	protected void loadTestCases() {
		testCases.put("john.doe@gmail.com", "john.doe@gmail.com");
		
		testCases.put("john(dot)doe(at)gmail(dot)com", "john.doe@gmail.com");
		testCases.put("john(dot)doe at gmail.com", "john.doe@gmail.com");
		testCases.put("john.doe@gmail(dot)com", "john.doe@gmail.com");
		testCases.put("john(dot)doe@gmail.com", "john.doe@gmail.com");
		testCases.put("john(dot)doe@gmail_dot_com", "john.doe@gmail.com");
		testCases.put("john dot doe at gmail dot com", "john.doe@gmail.com");
		testCases.put("john_dot_doe_at_gmail_dot_com", "john.doe@gmail.com");
		testCases.put("john _dot_ doe _at_ gmail _dot_ com", "john.doe@gmail.com");
		
		testCases.put("john dot doe plus sorting at gmail dot com", "john.doe+sorting@gmail.com");
		
		testCases.put("jsmith@[192.168.2.1]", "jsmith@[192.168.2.1]");
		testCases.put("jsmith at [192 dot 168 dot 2.1]", "jsmith@[192.168.2.1]");
		
		testCases.put("jsmith_at_[IPv6:2001:db8::1]", "jsmith@[IPv6:2001:db8::1]");
	}
	
	@Test
	public void runTestCases() {
		loadTestCases();
		
		Iterator<Entry<String, String>> it = testCases.entrySet().iterator();
		
		boolean pass = true;
		
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			String value = entry.getKey();
			String expectedValue = entry.getValue();
			try {
				String transformedValue = emailAddressTransformer.transform(value);
				if (transformedValue.equals(expectedValue)) {
					System.out.println("Passed");
					System.out.println("Input: " + value);
					System.out.println("Output: " + transformedValue);
				} else {
					pass = false;
					
					System.out.println("Failed");
					System.out.println("Input: " + value);
					System.out.println("Output: " + transformedValue);
				}
			} catch (EmailAddressUndisguizerException e) {
				pass = false;
				
				System.out.println("Failed");
				System.out.println("Input: " + value);
			}
			System.out.println("----------------------------");
		}
		
		if (pass) {
			System.out.println("All test passed!");
		} else {
			System.out.println("Some test failed.");
		}
	}
}
