package base.util;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Tribunal de Contas dos Munic�pios do Estado de Goi�s - TCMGO
 *
 * @author cezar_a em 09/09/2016
 * @version 1.0.0
 */
public class DESCriptografador {

	private SecretKey chave;

	private static final String ALGORITMO_CRIPTOGRAFIA = "DES";

	public static byte[] gerarNovaChave() throws NoSuchAlgorithmException {

		KeyGenerator keygen = KeyGenerator.getInstance(ALGORITMO_CRIPTOGRAFIA);

		SecretKey desKey = keygen.generateKey();

		return desKey.getEncoded();
	}

	public DESCriptografador(byte[] chave) {

		try {

			DESKeySpec pass = new DESKeySpec(chave);

			SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITMO_CRIPTOGRAFIA);

			this.chave = skf.generateSecret(pass);

		} catch (InvalidKeyException e) {

			throw new RuntimeException(e.getMessage());

		} catch (NoSuchAlgorithmException e) {

			throw new RuntimeException(e.getMessage());

		} catch (InvalidKeySpecException e) {

			throw new RuntimeException(e.getMessage());
		}
	}

	public byte[] criptografar(byte[] entrada) {

		try {

			Cipher cifra = Cipher.getInstance(ALGORITMO_CRIPTOGRAFIA);

			cifra.init(1, chave);

			return cifra.doFinal(entrada);

		} catch (NoSuchAlgorithmException e) {

			throw new RuntimeException(e.getMessage());

		} catch (NoSuchPaddingException e) {

			throw new RuntimeException(e.getMessage());

		} catch (InvalidKeyException e) {

			throw new RuntimeException(e.getMessage());

		} catch (IllegalBlockSizeException e) {

			throw new RuntimeException(e.getMessage());

		} catch (BadPaddingException e) {

			throw new RuntimeException(e.getMessage());
		}
	}

	public byte[] descriptografar(byte[] entrada) {

		try {

			Cipher cifra = Cipher.getInstance(ALGORITMO_CRIPTOGRAFIA);

			cifra.init(2, chave);

			return cifra.doFinal(entrada);

		} catch (NoSuchAlgorithmException e) {

			throw new RuntimeException(e.getMessage());

		} catch (NoSuchPaddingException e) {

			throw new RuntimeException(e.getMessage());

		} catch (InvalidKeyException e) {

			throw new RuntimeException(e.getMessage());

		} catch (IllegalBlockSizeException e) {

			throw new RuntimeException(e.getMessage());

		} catch (BadPaddingException e) {

			throw new RuntimeException(e.getMessage());
		}
	}
}
