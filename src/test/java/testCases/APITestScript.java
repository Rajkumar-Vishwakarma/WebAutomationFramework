package testCases;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import org.json.JSONArray;
import org.json.JSONObject;

import baseFramework.BaseClass;
import baseFramework.ConstantsFile;

public class APITestScript extends BaseClass {

	public static String formattedString = "According to %1$s, in %2$s years from 20%3$s to 20%4$s, "
			+ "peak population growth was %5$s%% in 20%6$s and " + "lowest population increase was %7$s%% in 20%8$s.";
	public static final String year = "Year";
	public static final String populationParameter = "Population";
	public static final String source = "source";
	public static final String annotations = "annotations";
	public static final String sourceName = "source_name";
	public static final String data = "data";

	public static void main(String[] args) {
		
		String url = "https://datausa.io/api/data?drilldowns=Nation&measures=Population";

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
		String dataSource = jsonOb.getJSONArray(source).getJSONObject(0).getJSONObject(annotations)
				.getString(sourceName);
		JSONArray nations = jsonOb.getJSONArray(data);

		int noOfYears = nations.length() - 1;
		String initialYr = null;
		String finalYr = null;
		float peakPopulationPrcnt = 0.0f;
		String peakPopulationYr;
		float lowestPopulationPrcnt = 0.0f;
		String lowestPopulationYr;
		Dictionary<String, Float> dict = new Hashtable<String, Float>();

		double previousPopulation = 0;

		try {
			for (int i = 0; i < nations.length(); i++) {

				JSONObject dataNation = nations.getJSONObject(i);

				double population = dataNation.getDouble(populationParameter);

				float prctChange = 0.0f;

				if (previousPopulation == 0) {
					previousPopulation = population;
				} else {
					prctChange = (float) (((previousPopulation - population) / population) * 100);
					dict.put(nations.getJSONObject(i - 1).getString(year), prctChange);
				}

				if (i == 0)
					finalYr = dataNation.getString(year).substring(2);

				if (i == nations.length() - 1)
					initialYr = dataNation.getString(year).substring(2);

			}
			peakPopulationPrcnt = getMaxPrcnt(dict.elements());
			lowestPopulationPrcnt = getMinPrcnt(dict.elements());

			peakPopulationYr = getYear(peakPopulationPrcnt, dict).substring(2);
			lowestPopulationYr = getYear(lowestPopulationPrcnt, dict).substring(2);

			System.out.println(String.format(formattedString, dataSource, String.valueOf(noOfYears), initialYr, finalYr,
					String.valueOf(peakPopulationPrcnt), peakPopulationYr, String.valueOf(lowestPopulationPrcnt),
					lowestPopulationYr));
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	public static String getYear(float populationPrcnt, Dictionary<String, Float> dict) {
		String year = null;
		Enumeration<Float> enValues = dict.elements();
		Enumeration<String> enKeys = dict.keys();
		float elementPrcnt = 0.0f;
		while (enValues.hasMoreElements()) {
			elementPrcnt = enValues.nextElement();
			if (populationPrcnt == elementPrcnt)
				break;
		}

		while (enKeys.hasMoreElements()) {
			String key = enKeys.nextElement();
			if (dict.get(key) == elementPrcnt) {
				year = key;
				break;
			}
		}
		return year;
	}

	public static float getMaxPrcnt(Enumeration<Float> elements) {
		float max = 0.0f, elementPrcnt = 0.0f;
		while (elements.hasMoreElements()) {
			elementPrcnt = elements.nextElement();
			if (max < elementPrcnt)
				max = elementPrcnt;
		}
		return max;
	}

	public static float getMinPrcnt(Enumeration<Float> elements) {
		float min = 0.0f, elementPrcnt = 0.0f;
		if (elements != null)
			min = elements.nextElement();

		while (elements.hasMoreElements()) {
			elementPrcnt = elements.nextElement();
			if (min > elementPrcnt)
				min = elementPrcnt;
		}
		return min;
	}

}
