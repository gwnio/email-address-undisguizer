package io.emailaddress.undisguizer;

public class EmailAddressUndisguizerCheck {

	private EmailAddressUndisguizerSubstituteConfiguration configuration;
	
	public EmailAddressUndisguizerCheck(EmailAddressUndisguizerSubstituteConfiguration configuration) {
		this.configuration = configuration;
	}
	
	public boolean isDisguisedEmailAddress(String emailAddress) {
		for (EmailAddressUndisguizerSubstituteMetadata substitute : configuration.getSubstitutes()) {
			if (doesContainSubstitutes(emailAddress, substitute)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean doesContainSubstitutes(String emailAddress, EmailAddressUndisguizerSubstituteMetadata substitute) {
		for (String substituteString : substitute.getSubstitutionStrings()) {
			if (emailAddress.contains(substituteString)) {
				return true;
			}
		}
		return false;
	}
}
