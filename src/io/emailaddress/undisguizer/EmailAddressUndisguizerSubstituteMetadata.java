package io.emailaddress.undisguizer;

import java.util.ArrayList;
import java.util.List;

public class EmailAddressUndisguizerSubstituteMetadata {

	private String baseSubstitutionString;
	
	private List<String> substitutionStrings = new ArrayList<String>();
	
	private String replacementString;

	public String getBaseSubstitutionString() {
		return baseSubstitutionString;
	}

	public void setBaseSubstitutionString(String baseSubstitutionString) {
		this.baseSubstitutionString = baseSubstitutionString;
	}

	public List<String> getSubstitutionStrings() {
		return substitutionStrings;
	}

	public void setSubstitutionStrings(List<String> substitutionStrings) {
		this.substitutionStrings = substitutionStrings;
	}

	public String getReplacementString() {
		return replacementString;
	}

	public void setReplacementString(String replacementString) {
		this.replacementString = replacementString;
	}
}
