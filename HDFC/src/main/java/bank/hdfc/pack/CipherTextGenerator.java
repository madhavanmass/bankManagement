package bank.hdfc.pack;

public class CipherTextGenerator {
	public final String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^&*()_+-={}[]:;'<>,.?/|";

	public String encrypt(String message, int shiftKey) {
		String cipherText = "";
		for (int var = 0; var < message.length(); var++) {
			int charPosition = str.indexOf(message.charAt(var));
			int keyVal = (shiftKey + charPosition) % 62;
			char replaceVal = str.charAt(keyVal);
			cipherText += replaceVal;
		}
		return cipherText;
	}

	public String decrypt(String cipherText, int shiftKey) {
		cipherText = cipherText.toLowerCase();
		String message = "";
		for (int var = 0; var < cipherText.length(); var++) {
			int charPosition = str.indexOf(cipherText.charAt(var));
			int keyVal = (charPosition - shiftKey) % 62;
			if (keyVal < 0) {
				keyVal = str.length() + keyVal;
			}
			char replaceVal = str.charAt(keyVal);
			message += replaceVal;
		}
		return message;
	}

}
