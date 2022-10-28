package testCases;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import baseFramework.BaseClass;

public class APITestScript extends BaseClass {

	public static void main(String[] args) {

		String url = "https://datausa.io/api/data?drilldowns=Nation&measures=Population";

		String formattedString = "According to _____, in _ years from 20__ to 20__, "
				+ "peak population growth was __% in 20__ and " + "lowest population increase was __% in 20__.";

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

		try {
			client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body)
					.thenApply(APITestScript::parse).join();
		} catch (Exception e) {
			System.out.println("ERROR!");
			System.out.println(e);
		}
	}

	public static String parse(String responseBody) {
		JSONObject jsonOb = new JSONObject(responseBody);
		JSONArray nations = jsonOb.getJSONArray("data");

		try {
			for (int i = 0; i < nations.length(); i++) {

				JSONObject dataNation = nations.getJSONObject(i);
				String idNation = dataNation.getString("ID Nation");
				String nation = dataNation.getString("Nation");
				int idYear = dataNation.getInt("ID Year");
				String year = dataNation.getString("Year");
				int population = dataNation.getInt("Population");
				String slugNation = dataNation.getString("Slug Nation");
				System.out.println("ID Nation: " + idNation);
				System.out.println("Nation: " + nation);
				System.out.println("ID Year: " + idYear);
				System.out.println("Year: " + year);
				System.out.println("Population: " + population);
				System.out.println("Slug Nation: " + slugNation);
				System.out.println("---");

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
