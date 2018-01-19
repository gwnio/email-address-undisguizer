package io.emailaddress.undisguizer;

import java.util.ArrayList;
import java.util.List;

/**
 * https://en.wikipedia.org/wiki/Email_address
 */
public class EmailAddressUndisguizerTransformer {

//	public static void main(String[] args) throws InvalidSpamSafeEmailAddressException {
//		new SpamSafeEmailAddressTransformer().transform("john.doe@gmail(dot)com");
//	}
	
	final int expectedPartsLength = 2;
	
	private EmailAddressUndisguizerSubstituteConfiguration configuration;
	
	public EmailAddressUndisguizerTransformer(EmailAddressUndisguizerSubstituteConfiguration configuration) {
		this.configuration = configuration;
	}
	
	public String transform(String emailAddress) throws EmailAddressUndisguizerException {
		if (new EmailAddressUndisguizerCheck(configuration).isSpamSafeEmailAddress(emailAddress)) {
			String[] parts = splitParts(emailAddress);
			if (parts == null || parts.length < expectedPartsLength) {
				throw new EmailAddressUndisguizerException("Could not split the email address into a local-part and domain.");
			}
			String localpart = transformLocalpart(parts[0]);
			String domain = transformDomain(parts[1]);
			
			String transformedEmailAddress = squeeze(localpart) + configuration.getAtSubstitute().getReplacementString() + squeeze(domain);
			return transformedEmailAddress;
		}
		return emailAddress;
	}
	
	protected String transformLocalpart(String spamSafeLocalpart) {
		for (EmailAddressUndisguizerSubstituteMetadata substitute : configuration.getLocalpartSubstitutes()) {
			for (String substituteString : substitute.getSubstitutionStrings()) {
				if (spamSafeLocalpart.contains(substituteString)) {
					spamSafeLocalpart = replace(spamSafeLocalpart, substituteString, substitute.getReplacementString());
				}
			}
		}
		return spamSafeLocalpart;
	}
	
	protected String transformDomain(String spamSafeDomain) {
		for (EmailAddressUndisguizerSubstituteMetadata substitute : configuration.getDomainSubstitutes()) {
			for (String substituteString : substitute.getSubstitutionStrings()) {
				if (spamSafeDomain.contains(substituteString)) {
					spamSafeDomain = replace(spamSafeDomain, substituteString, substitute.getReplacementString());
				}
			}
		}
		return spamSafeDomain;
	}

	protected String replace(String text, String repl, String with) {
		String[] frags = split(text, repl);
		int fragsLength = frags.length;
		if (fragsLength > 1) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < fragsLength; i++) {
				sb.append(frags[i].trim());
				if (i != (fragsLength - 1)) {
					sb.append(with);
				}
			}
			return sb.toString();
		} else {
			return text;
		}
	}
	
	protected String[] splitParts(String spamSafeEmailAddress) {
		List<String> ats = new ArrayList<String>();
		ats.addAll(configuration.getAtSubstitute().getSubstitutionStrings());
		ats.add(configuration.getAtSubstitute().getReplacementString());
		for (String at : ats) {
			String[] parts = split(spamSafeEmailAddress, at);
			if (parts.length == expectedPartsLength) {
				parts[0] = parts[0].trim();
				parts[1] = parts[1].trim();
				return parts;
			}
		}
		return null;
	}
	
	protected String[] split(String text, String regex) {
		if (regex.contains("(")) {
			regex = regex.replace("(", "\\(");
		}
		if (regex.contains(")")) {
			regex = regex.replace(")", "\\)");
		}
		String[] result = text.split(regex);
		return result;
	}
	
	protected String squeeze(String text) {
		return text.replaceAll(" ", "");
	}
}
