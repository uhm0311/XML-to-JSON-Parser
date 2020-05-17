package xmlToJsonParser.utils;

public class ByteConverter {

	private ByteConverter() {
	}

	public static String byteArrayToHex(byte[] bytes) {
		if (bytes == null || bytes.length == 0)
			return null;

		StringBuffer sb = new StringBuffer(bytes.length * 2);
		String hexNumber;
		for (int x = 0; x < bytes.length; x++) {
			hexNumber = "0" + Integer.toHexString(0xff & bytes[x]);
			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}
		return sb.toString().toUpperCase();
	}

	public static byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() == 0)
			return null;

		byte[] ba = new byte[hex.length() / 2];
		for (int i = 0; i < ba.length; i++)
			ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);

		return ba;
	}
}
