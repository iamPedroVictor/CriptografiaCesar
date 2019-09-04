package cesarCriptografia;

public class CriptografiaCesar {
	private int numero_casas = 0;
	
	public CriptografiaCesar(int numero_casas) {
		this.numero_casas = numero_casas;
	}
	
	public String Criptografar(String texto) {
		char[] textoArrayChar = texto.toLowerCase().toCharArray();
		char[] textoCriptografado = new char[textoArrayChar.length];
		StringBuilder resposta = new StringBuilder();
		for (int i = 0; i < textoArrayChar.length; i++) {
			if(textoArrayChar[i] < 96 || textoArrayChar[i] > 122) {
				textoCriptografado[i] = textoArrayChar[i];
				continue;
			}
			int codeASCII = textoArrayChar[i] + this.numero_casas;
			if(codeASCII >= 122) {
				int diferenca = codeASCII - 122;
				codeASCII = 96 + diferenca;
			}
			textoCriptografado[i] = (char)codeASCII;
		}
		for (int i = 0; i < textoCriptografado.length; i++) {
			resposta.append(textoCriptografado[i]);
		}
		return resposta.toString();
	}
	
	public String Decifrar(String texto) {
		char[] textoArrayChar = texto.toLowerCase().toCharArray();
		char[] textoDecifrado = new char[textoArrayChar.length];
		StringBuilder resposta = new StringBuilder();
		for (int i = 0; i < textoArrayChar.length; i++) {
			if(textoArrayChar[i] < 96 || textoArrayChar[i] > 122) {
				textoDecifrado[i] = textoArrayChar[i];
				continue;
			}
			int codeASCII = textoArrayChar[i] - this.numero_casas;
			if(codeASCII <= 96) {
				int diferenca = 96 - codeASCII;
				codeASCII = 122 - diferenca;
			}
			textoDecifrado[i] = (char)codeASCII;
		}
		for (int i = 0; i < textoDecifrado.length; i++) {
			resposta.append(textoDecifrado[i]);
		}
		return resposta.toString();
	}
}
