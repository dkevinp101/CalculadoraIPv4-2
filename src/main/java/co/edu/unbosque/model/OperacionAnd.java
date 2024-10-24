package co.edu.unbosque.model;

public class OperacionAnd {
	
	public String and(String ip, String mascara) {
		String ipResultante = "";

		for (int i = 0; i < ip.length(); i++) {
			int aux1 = ip.charAt(i) - '0';
			int aux2 = mascara.charAt(i) - '0';
			
			ipResultante += (aux1 * aux2);

		}
		
		return ipResultante;
	}

}
