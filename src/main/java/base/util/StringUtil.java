package base.util;

import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Collection;
import java.util.UUID;

public class StringUtil implements Serializable {



	public static final String CPF_INTEGER_REGEX = "[0-9]{11}";

	public static final String CNPJ_INTEGER_REGEX = "[0-9]{14}";

	public static final String CEP_INTEGER_REGEX = "[0-9]{8}";


    public static String hashMD5(String s) throws Exception {
        return DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5").digest(s.getBytes()));
	}

	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String uuidRaw() {
		return UUID.randomUUID().toString();
	}

	public static String preencherTextoComZeros(String palavra, Integer tamanho) {
		int delta = tamanho - palavra.length();
		if (delta > 0) {
			for (int i = 0; i < delta; ++i)
				palavra = "0" + palavra;
		}
		return palavra;
	}

	public static String primeiraLetraMaiuscula(String string) {
		return StringUtils.capitalize(string);
	}
	
	public static String primeiraLetraMinuscula(String string) {
		return StringUtils.uncapitalize(string);
	}
	
	public static boolean vazia(String string) {
		return string == null || string.trim().isEmpty();
	}


	public static String obterPrimeiroEUltimoNome(String nome) {
		String[] partes = nome.split(" ");
		return primeiraLetraMaiuscula(partes[0]) + " " + primeiraLetraMaiuscula(partes[partes.length - 1]);
	}

	public static String retirarAspasSimlesEDuplas(String texto) {
		if (texto != null)
			texto = texto.replace("\'", "").replace("\"", "");
		return texto;
	}

	public static BigDecimal subtrairPositivo(BigDecimal a, BigDecimal b) {
		if (a != null && b != null) {
			if (a.compareTo(b) == 0) { 
				return new BigDecimal(0);	
			}
			if (a.compareTo(b) == 1) {
				return a.subtract(b);
			} else {
				return b.subtract(a);
			}
		} 
		return null;
	}
	
	public static boolean somenteInteger(String string) {
		return (string != null && !string.isEmpty()) && string.trim().matches("\\d+");
	}
	
	public static String retirarCaracteres(String string) {
		if (StringUtil.isNotEmpty(string))
			return string.replaceAll("\\D+", "");
		return string;
	}

    public static String retirarCaracteresEspeciais(String string) {
        return string.replaceAll("[^\\p{IsAlphabetic}^\\p{IsDigit}]", "");
    }
	
	public static String retirarInteiros(String string) {
		return string.replaceAll("\\d+", "");
	}

    public static byte [] converterEmBytes(String converter) {
        return Base64.getDecoder().decode(converter);
    }

	public static boolean isEmpty(String string) {
		return string == null || string.trim().isEmpty();
	}

	public static boolean isNotEmpty(String string) {
		return !isEmpty(string);
	}
	
	public static boolean diferenteDeZero(Integer inteiro) {
		return inteiro != null && inteiro != 0;
	}
	
	public static boolean diferenteDeZero(Long inteiro) {
		return inteiro != null && inteiro != 0;
	}
	
	public static boolean isNotEmpty(Character character) {
		return character != null && !character.toString().isEmpty();
	}
	
	public static boolean isNotEmpty(Collection<?> lista) {
		return lista != null && lista.size() > 0; 
	}


	public static String formatarCPF(String cpf) {
		if (StringUtil.isNotEmpty(cpf) && cpf.matches(StringUtil.CPF_INTEGER_REGEX))
			return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
		return cpf;
	}

	public static String formatarCNPJ(String cnpj) {
		if (StringUtil.isNotEmpty(cnpj) && cnpj.matches(StringUtil.CNPJ_INTEGER_REGEX))
			return cnpj.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
		return cnpj;
	}

	public static String formatarCEP(String cep) {
		if (StringUtil.isNotEmpty(cep) && cep.matches(StringUtil.CEP_INTEGER_REGEX))
			return cep.replaceFirst("(\\d{2})(\\d{3})(\\d{3})", "$1.$2-$3");
		return cep;
	}


}
