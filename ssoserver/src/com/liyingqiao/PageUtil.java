package com.liyingqiao;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

public class PageUtil {
	private static Set<String> LOGIN_PAGE_SET= new HashSet<String>(); 

	static {
		LOGIN_PAGE_SET.add("demo1");
		LOGIN_PAGE_SET.add("demo2");
	}

	public static boolean contains(String key) {
		return LOGIN_PAGE_SET.contains(key);
	}

	public static String getPage() {
		HttpClient client = HttpClient.newBuilder()
				.connectTimeout(Duration.ofMillis(5000))
				.followRedirects(HttpClient.Redirect.NORMAL)
				.build();

		//2.set read timeout
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://www.baidu.com/"))
				.timeout(Duration.ofMillis(5009))
				.build();

		java.net.http.HttpResponse<String> response;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return response.body();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(getPage());
	}
}