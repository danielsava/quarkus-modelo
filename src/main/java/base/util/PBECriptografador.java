package base.util;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Tribunal de Contas dos Munic�pios do Estado de Goi�s - TCMGO
 *
 * @author cezar_a em 09/09/2016
 * @version 1.0.0
 */
public class PBECriptografador {

	private static int ITERATIONS = 1000;

	private byte[] salt = { 1, 5, 3, 4, 9, -1, -2, -3 };

	private SecretKey chave;

	private static final String ALGORITMO_CRIPTOGRAFIA = "PBEWithMD5AndDES";

	public PBECriptografador(String senha) {

		try {

			PBEKeySpec keySpec = new PBEKeySpec(senha.toCharArray());

			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITMO_CRIPTOGRAFIA);

			chave = keyFactory.generateSecret(keySpec);

		} catch (NoSuchAlgorithmException e) {

			throw new RuntimeException(e.getMessage());

		} catch (InvalidKeySpecException e) {

			throw new RuntimeException(e.getMessage());
		}
	}

	public byte[] criptografar(byte[] entrada) {

		PBEParameterSpec paramSpec = new PBEParameterSpec(salt, ITERATIONS);

		try {

			Cipher cipher = Cipher.getInstance(ALGORITMO_CRIPTOGRAFIA);

			cipher.init(1, chave, paramSpec);

			return cipher.doFinal(entrada);

		} catch (NoSuchAlgorithmException e) {

			throw new RuntimeException(e.getMessage());

		} catch (NoSuchPaddingException e) {

			throw new RuntimeException(e.getMessage());

		} catch (InvalidKeyException e) {

			throw new RuntimeException(e.getMessage());

		} catch (InvalidAlgorithmParameterException e) {

			throw new RuntimeException(e.getMessage());

		} catch (IllegalBlockSizeException e) {

			throw new RuntimeException(e.getMessage());

		} catch (BadPaddingException e) {

			throw new RuntimeException(e.getMessage());
		}
	}

	public byte[] descriptografar(byte[] entrada) {

		PBEParameterSpec paramSpec = new PBEParameterSpec(salt, ITERATIONS);

		try {

			Cipher cipher = Cipher.getInstance(ALGORITMO_CRIPTOGRAFIA);

			cipher.init(2, chave, paramSpec);

			return cipher.doFinal(entrada);

		} catch (NoSuchAlgorithmException e) {

			throw new RuntimeException(e.getMessage());

		} catch (NoSuchPaddingException e) {

			throw new RuntimeException(e.getMessage());

		} catch (InvalidKeyException e) {

			throw new RuntimeException(e.getMessage());

		} catch (InvalidAlgorithmParameterException e) {

			throw new RuntimeException(e.getMessage());

		} catch (IllegalBlockSizeException e) {

			throw new RuntimeException(e.getMessage());

		} catch (BadPaddingException e) {

			throw new RuntimeException(e.getMessage());

		}
	}
}
