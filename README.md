
# email-address-undisguizer

This is a java micro library that that takes a string of characters that are disguised as a email address for purposes of spam avoidance and converts it to a valid email address.  The actual reason I build this was for usage in a personal assistant application, where the personal assistant spells out the voice input of a email address.

The library follows the email address format laid out [here](https://en.wikipedia.org/wiki/Email_address){:target="_blank"}.

The primary class is ```EmailAddressUndisguizerTransformer``` whose constructor accepts a configuration instance of ```EmailAddressUndisguizerSubstituteConfiguration```.  ```EmailAddressUndisguizerSubstituteConfiguration``` can be customized via method overrides but should come out of the box well configured based on [email address formats](https://en.wikipedia.org/wiki/Email_address){:target="_blank"}.  ```EmailAddressUndisguizerCheck``` uses the same configuration class ```EmailAddressUndisguizerSubstituteConfiguration``` to indicate if the string is a disguised email address.

There is also has a junit test class ```EmailAddressUndisguizerTest```.