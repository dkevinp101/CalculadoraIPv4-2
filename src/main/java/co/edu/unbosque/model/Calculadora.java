package co.edu.unbosque.model;

import java.math.BigInteger;

public class Calculadora {

	private OperacionAnd and;
	private Convertidor con;
	private String ipOriginalBin;
	private String ipRedBin;
	private String ipRedDec;
	private String ipBroadcastDec;
	private int bitsHost;
	private String mascaraBin;
	private String ipOriginal;
	private String porcionRed;
	private String porcionHost;

	public Calculadora(String ipOriginal, int mascara) {
		this.and = new OperacionAnd();
		this.con = new Convertidor();
		
		this.bitsHost = 32 - mascara;
		this.ipOriginalBin = con.ipOriginalABin(ipOriginal);
		this.mascaraBin = con.mascaraABin(mascara, bitsHost);
		this.ipOriginal = ipOriginal;
		porcionRedHost();

	}

	public long cantidadHost(int mascara) {
		return (long) ((Math.pow(2, 32 - mascara)) - 2);
	}

	public String ipRed() {

		this.ipRedDec = "";
		this.ipRedBin = this.and.and(this.ipOriginalBin, this.mascaraBin);
		this.ipRedDec = this.con.binADec(this.ipRedBin);
		return ipRedDec;

	}

	public String ipBroadcast() {

		int aux = 32 - bitsHost;
		String porcionRed = this.ipRedBin.substring(0, aux);
		String porcionHost = this.ipRedBin.substring(aux, 32);
		String aux2 = "";

		for (int i = 0; i < porcionHost.length(); i++) {
			aux2 += 1;
		}
		String ipBroadcastBin = porcionRed + aux2;
		ipBroadcastDec = this.con.binADec(porcionRed + aux2);

		return ipBroadcastDec;
	}

	public String primeraIpUtil() {
		
		String[] octeto = ipRedDec.split("\\.");
		for (int i = octeto.length - 1; i >= 0; i--) {
			int num = Integer.parseInt(octeto[i]) + 1;
			if(num <= 255) {
				octeto[i] = String.valueOf(num);
				return octeto[0] + "." + octeto[1] + "." + octeto[2] + "." + octeto[3];
			}
		}

	    return "Algo salio mal";
	}

	public String ultimaIpUtil() {
		String[] octeto = ipBroadcastDec.split("\\.");
		for (int i = octeto.length - 1; i >= 0; i--) {
			int num = Integer.parseInt(octeto[i]) - 1;
			if(num >= 0) {
				octeto[i] = String.valueOf(num);
				return octeto[0] + "." + octeto[1] + "." + octeto[2] + "." + octeto[3];
			}
		}

	    return "Algo salio mal";
	}

	public String clase() {
		if (this.ipOriginalBin.substring(0, 1).equals("0")) {
			return "Clase A";
		} else if (this.ipOriginalBin.substring(0, 2).equals("10")) {
			return "Clase B";
		} else if (this.ipOriginalBin.substring(0, 3).equals("110")) {
			return "Clase C";
		} else if (this.ipOriginalBin.substring(0, 4).equals("1110")) {
			return "Clase D";
		} else if (this.ipOriginalBin.substring(0, 5).equals("11110")) {
			return "Clase E";
		} else {

			return "Clase E";
			
		}
	}

	public String clasificacionPrivadaPublica() {
		String[] separar = this.ipOriginal.split("\\.");

		if (separar[0].equals("10")) {
			return "Privada";
		} else if (separar[0].equals("172") && comparar(separar[1], 16, 31)) {
			return "Privada";
		} else if (separar[0].equals("192") && separar[1].equals("168")) {
			return "Privada";
		} else {
			return "Publica";
		}

	}

	private boolean comparar(String num, int min, int max) {
		int aux = Integer.parseInt(num);
		return aux >= min && aux <= max;
	}

	private void porcionRedHost() {
		int aux = 32 - bitsHost;
		this.porcionRed = this.ipOriginalBin.substring(0, aux);
		this.porcionHost = this.ipOriginalBin.substring(aux, 32);
	}

	public String getPorcionRed() {
		return porcionRed;
	}

	public void setPorcionRed(String porcionRed) {
		this.porcionRed = porcionRed;
	}

	public String getPorcionHost() {
		return porcionHost;
	}

	public void setPorcionHost(String porcionHost) {
		this.porcionHost = porcionHost;
	}
	

}
