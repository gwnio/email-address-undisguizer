package io.emailaddress.undisguizer;

import java.util.List;

public class EmailAddressUndisguizerSubstituteMetadataBuilder {

	public EmailAddressUndisguizerSubstituteMetadata buildSubstituteMetadata(String baseSubstitution, String replacement) {
		EmailAddressUndisguizerSubstituteMetadata metadata = new EmailAddressUndisguizerSubstituteMetadata();
		metadata.setBaseSubstitutionString(baseSubstitution);
		addSubstitutes(metadata.getSubstitutionStrings(), baseSubstitution);
		metadata.setReplacementString(replacement);
		return metadata;
	}
	
	/**
	 * Override this to add more substitute options for the base substitute.
	 * 
	 * @param substitutes
	 * @param baseSubstitution
	 */
	protected void addSubstitutes(List<String> substitutes, String baseSubstitution) {
		substitutes.add(spaceBuffer(baseSubstitution));
		substitutes.add(underscoreBuffer(baseSubstitution));
		substitutes.add(parenthesesBuffer(baseSubstitution));
	}
	
	protected String spaceBuffer(String text) {
		return " " + text + " ";
	}
	
	protected String underscoreBuffer(String text) {
		return "_" + text + "_";
	}
	
	protected String parenthesesBuffer(String text) {
		return "(" + text + ")";
	}
}
