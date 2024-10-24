package co.edu.unbosque.model;

public class Convertidor {
	
	public String ipOriginalABin(String ipOriginal) {
		String ipOriginalBin = "";
		
		String[] separar = ipOriginal.split("\\.");
		
		for (String string : separar) {
			int num = Integer.parseInt(string);
			
			String bin = String.format("%08d", Integer.parseInt(Integer.toBinaryString(num)));
			
			ipOriginalBin += bin;
		}
		return ipOriginalBin;
	}
	
	public String mascaraABin(int mascara, int bitsHost) {
		String mascaraBin = "";
		int aux = 32 - bitsHost;
		for (int i = 0; i < 32; i++) {
			if(mascaraBin.length() < aux) {
				mascaraBin += 1;
			}else {
				mascaraBin += 0;
			}
		}
		return mascaraBin;
		
	}
	
	public String binADec(String ipRedBin) {
		
		String octeto1 = String.valueOf(Integer.parseInt(ipRedBin.substring(0, 8),2));
		String octeto2 = String.valueOf(Integer.parseInt(ipRedBin.substring(8, 16),2));
		String octeto3 = String.valueOf(Integer.parseInt(ipRedBin.substring(16, 24),2));
		String octeto4 = String.valueOf(Integer.parseInt(ipRedBin.substring(24, 32),2));
		
		String dec = octeto1 + "." + octeto2 + "." + octeto3 + "." + octeto4;

		return dec;
	}


}
