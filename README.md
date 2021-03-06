
# email-address-undisguizer

This is a java micro library that takes a string of characters disguised as a email address for purposes of spam avoidance and converts it to a valid email address.  The actual reason I built this was for usage in a personal assistant application, where the personal assistant spells out the voice input of a email address.

For example, when prompted for a email address by Amazon Alex or Google Assistant the text generated by the virtual assistant will look like what the person speaks.  So when spoken, the email address john.doe@gmail.com could be translated to text as such:
* john dot doe at gmail dot com
* john_dot_doe_at_gmail_dot_com
* john _dot_ doe _at_ gmail _dot_ com

Text in this format stored in a column of a database table marked for email addresses is not clean data and will not be very helpful to the system.

The library follows the email address format laid out [here](https://en.wikipedia.org/wiki/Email_address).

The primary class is ```EmailAddressUndisguizerTransformer``` whose constructor accepts a configuration instance of ```EmailAddressUndisguizerSubstituteConfiguration```.  ```EmailAddressUndisguizerSubstituteConfiguration``` can be customized via method overrides but should come out of the box well configured based on [standard email address formats](https://en.wikipedia.org/wiki/Email_address).  ```EmailAddressUndisguizerCheck``` uses the same configuration class ```EmailAddressUndisguizerSubstituteConfiguration``` to indicate if the string is a disguised email address.

There is also has a junit test class ```EmailAddressUndisguizerTest```.
