package servicos;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;

import models.Answer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CodenationAPI {
	private static final String URL_Submit = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=%s";
	private final String BaseURL = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=%s";
	private final MediaType MEDIA_TYPE_JSON = MediaType.parse("file");
	private String userToken = "";
	private final OkHttpClient client = new OkHttpClient();
	
	public CodenationAPI(String token) {
		this.userToken = token;
	}
	
	public Answer GetCodenationData() {
		Answer answer = new Answer();
		Gson gson = new Gson();
		try {
			Request request = new Request.Builder()
					.url(String.format(this.BaseURL, this.userToken))
					.build();
			try(Response response = client.newCall(request).execute()){
				if(!response.isSuccessful()) throw new IOException("Codigo inesperado: " + response);
				answer = gson.fromJson(response.body().string(), Answer.class);				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	public void Submit(String userToken, String answerJson) {
		try {
			RequestBody requestBody = new MultipartBody.Builder()
					.setType(MultipartBody.FORM)	
					.addFormDataPart("answer", "answer.json",
							RequestBody.create(MEDIA_TYPE_JSON, new File(answerJson)))
					.build();
			
			Request request = new Request.Builder()
					.url(String.format(URL_Submit, userToken))
					.post(requestBody)
					.build();
			
			try(Response response = client.newCall(request).execute()){
				if(!response.isSuccessful()) throw new IOException("Codigo inesperado: " + response);
				System.out.println(response.body().string());
			}catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
