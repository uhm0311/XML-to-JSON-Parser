package xmlToJsonParser.utils;

public class BOMRemover {	
	public static final String UTF8 = "EFBBBF";
	public static final String UTF16BE = "FEFF";
	public static final String UTF16LE = "FFFE";
	public static final String UTF32BE = "0000FEFF";
	public static final String UTF32LE = "FFFE0000";
	public static final String UTF7Base = "2B2F76";
	public static final String UTF7_1 = UTF7Base + "38";
	public static final String UTF7_2 = UTF7Base + "39";
	public static final String UTF7_3 = UTF7Base + "2B";
	public static final String UTF7_4 = UTF7Base + "2F";
	public static final String UTF7_5 = UTF7Base + "382D";
	public static final String UTF1 = "F7644C";
	public static final String UTFEBCDIC = "DD736673";
	public static final String SCSU = "0EFEFF";
	public static final String BOCU1 = "FBEE28";
	public static final String GB18030 = "84319533";
	
	private static String[] bomList = null;
	
	private BOMRemover() { }
	
	private static String[] getBOMList() {
		if (bomList == null) {
			bomList = new String[] {
				UTF8,
				UTF16BE,
				UTF16LE,
				UTF32BE,
				UTF32LE,
				UTF7Base,
				UTF7_1,
				UTF7_2,
				UTF7_3,
				UTF7_4,
				UTF7_5,
				UTF1,
				UTFEBCDIC,
				SCSU,
				BOCU1,
				GB18030
			};
		}
		
		return bomList;
	}

	public static byte[] removeBOM(byte[] inputStream) {
		int startIndex = -1;
		String[] bom = getBOMList();

		String hexData = ByteConverter.byteArrayToHex(inputStream);

		for (int i = 0; i < bom.length; i++) {
			if (hexData.startsWith(bom[i])) {
				startIndex = bom[i].length();
				break;
			}
		}

		if (startIndex > 0) {
			hexData = hexData.substring(startIndex, hexData.length());
		}

		return ByteConverter.hexToByteArray(hexData);
	}
}
