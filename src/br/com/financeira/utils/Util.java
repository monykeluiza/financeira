package br.com.financeira.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

public class Util {
	
	public static final Integer QTD_REGISTROS_POR_LIVRO = 300;
	
	public static String toCammelCase(String nome) {
		try {
			nome = nome.toLowerCase();
			String[] semEspaco = nome.split(" ");
			String retorno = "";
			for (String str : semEspaco) {
				String primeiraLetra = str.substring(0, 1);
				String restoDaPalavra = str.substring(1);
				retorno += primeiraLetra.toUpperCase() + restoDaPalavra + " ";
			}
			String[] retirarUltimoEspaco = retorno.split(" ");
			String ultimaPalavra = retirarUltimoEspaco[retirarUltimoEspaco.length - 1];
			retorno = "";
			for (String string : retirarUltimoEspaco) {
				retorno += string;
				if (string.equals(ultimaPalavra)) {
					string.trim();
				} else {
					retorno += " ";
				}
			}
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			return nome;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Util.toCammelCase("MONIQUE luiza santana rego DantAs "));
	}
	
	public static String getNumeroFormatado(int numero, String formato) {
		DecimalFormat df = new DecimalFormat(formato);
    	return df.format(numero);
	}
	
	public static String tratarOrdinal(Integer NumeroVia) {
		if (NumeroVia.equals(2)) {
			return "segunda";
		}
		if (NumeroVia.equals(3)) {
			return "terceira";
		}
		if (NumeroVia.equals(4)) {
			return "quarta";
		}
		if (NumeroVia.equals(5)) {
			return "quinta";
		}
		if (NumeroVia.equals(6)) {
			return "sexta";
		}
		if (NumeroVia.equals(7)) {
			return "sétima";
		}
		if (NumeroVia.equals(8)) {
			return "oitava";
		}
		if (NumeroVia.equals(9)) {
			return "nona";
		}
		if (NumeroVia.equals(10)) {
			return "décima";
		}
		if (NumeroVia.equals(11)) {
			return "décima primeira";
		}
		if (NumeroVia.equals(12)) {
			return "décima segunda";
		}
		if (NumeroVia.equals(13)) {
			return "décima terceira";
		}
		if (NumeroVia.equals(14)) {
			return "décima quarta";
		}
		if (NumeroVia.equals(15)) {
			return "décima quinta";
		}
		if (NumeroVia.equals(16)) {
			return "décima sexta";
		}
		if (NumeroVia.equals(17)) {
			return "décima sétima";
		}
		if (NumeroVia.equals(18)) {
			return "décima oitava";
		}
		if (NumeroVia.equals(19)) {
			return "décima nona";
		}
		if (NumeroVia.equals(20)) {
			return "vigésima";
		}
		return "vigésima primeira";
	}
	
	public static String getMD5String(String value) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(value.getBytes(),0,value.length());
		return (new BigInteger(1,m.digest()).toString(16));
	}

}
