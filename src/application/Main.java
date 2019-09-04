package application;

import com.google.gson.Gson;

import cesarCriptografia.CriptografiaCesar;
import models.Answer;
import servicos.CodenationAPI;
import servicos.FileIO;
import servicos.Sha1;

public class Main {
	private static final String AnswerJsonPath = "Resources/answer.json";

	public static void main(String[] args) {
		String tokenUser = "38e760fae7439502289dea1e1b5b6cde8fb768c6";
		CodenationAPI codenation = new CodenationAPI(tokenUser);
		Answer dados = codenation.GetCodenationData();
		CriptografiaCesar decode = new CriptografiaCesar(dados.getNumero_casas());
		dados.setDecifrado(decode.Decifrar(dados.getCifrado()));
		dados.setResumo_criptografico(Sha1.EncodeSha1(dados.getDecifrado()));
		FileIO.Save(AnswerJsonPath, new Gson().toJson(dados));
		codenation.Submit(tokenUser, AnswerJsonPath);
	}

}
