package io.emailaddress.undisguizer;

import java.util.ArrayList;
import java.util.List;

public class EmailAddressUndisguizerSubstituteConfiguration {

	private List<EmailAddressUndisguizerSubstituteMetadata> substitutes = new ArrayList<EmailAddressUndisguizerSubstituteMetadata>();
	
	private List<EmailAddressUndisguizerSubstituteMetadata> localpartSubstitutes = new ArrayList<EmailAddressUndisguizerSubstituteMetadata>();
	
	private List<EmailAddressUndisguizerSubstituteMetadata> domainSubstitutes = new ArrayList<EmailAddressUndisguizerSubstituteMetadata>();
	
	private EmailAddressUndisguizerSubstituteMetadata atSubstitute;
	private EmailAddressUndisguizerSubstituteMetadata dotSubstitute;
	private EmailAddressUndisguizerSubstituteMetadata plusSubstitute;
	private EmailAddressUndisguizerSubstituteMetadata hyphenSubstitute;
	private EmailAddressUndisguizerSubstituteMetadata dashSubstitute;
	private EmailAddressUndisguizerSubstituteMetadata underscoreSubstitute;
	
	public EmailAddressUndisguizerSubstituteConfiguration() {
		init();
	}
	
	protected void init() {
		EmailAddressUndisguizerSubstituteMetadataBuilder builder = new EmailAddressUndisguizerSubstituteMetadataBuilder();
		
		atSubstitute = builder.buildSubstituteMetadata("at", "@");
		dotSubstitute = builder.buildSubstituteMetadata("dot", ".");
		plusSubstitute = builder.buildSubstituteMetadata("plus", "+");
		hyphenSubstitute = builder.buildSubstituteMetadata("hyphen", "-");
		dashSubstitute = builder.buildSubstituteMetadata("dash", "-");
		underscoreSubstitute = builder.buildSubstituteMetadata("underscore", "_");
		
		registerSubstitutes();
		initLocalpartSubstitutes();
		initDomainSubstitutes();
	}
	
	/**
	 * Override for customization.
	 */
	protected void registerSubstitutes() {
		substitutes.add(atSubstitute);
		substitutes.add(dotSubstitute);
		substitutes.add(plusSubstitute);
		substitutes.add(hyphenSubstitute);
		substitutes.add(dashSubstitute);
		substitutes.add(underscoreSubstitute);
	}
	
	/**
	 * Override for customization.
	 */
	protected void initLocalpartSubstitutes() {
		localpartSubstitutes.add(dotSubstitute);
		localpartSubstitutes.add(plusSubstitute);
		localpartSubstitutes.add(underscoreSubstitute);
	}
	
	/**
	 * Override for customization.
	 */
	protected void initDomainSubstitutes() {
		domainSubstitutes.add(dotSubstitute);
		domainSubstitutes.add(hyphenSubstitute);
		domainSubstitutes.add(dashSubstitute);
	}

	public List<EmailAddressUndisguizerSubstituteMetadata> getSubstitutes() {
		return substitutes;
	}

	public List<EmailAddressUndisguizerSubstituteMetadata> getLocalpartSubstitutes() {
		return localpartSubstitutes;
	}

	public List<EmailAddressUndisguizerSubstituteMetadata> getDomainSubstitutes() {
		return domainSubstitutes;
	}

	public EmailAddressUndisguizerSubstituteMetadata getAtSubstitute() {
		return atSubstitute;
	}
}
