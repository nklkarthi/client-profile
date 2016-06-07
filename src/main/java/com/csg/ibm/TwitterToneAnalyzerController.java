package com.csg.ibm;

import java.util.HashSet;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csg.ibm.util.Twitter4JHelper;
import com.ibm.watson.developer_cloud.tone_analyzer.v3_beta.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3_beta.model.ToneAnalysis;

import twitter4j.Status;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
public class TwitterToneAnalyzerController {

	String handle = "@realDonaldTrump";

	@RequestMapping("/twitterToneAnalysis")
	public ToneAnalysis getToneAnalyzer() {

		ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_02_11);
		service.setEndPoint("https://gateway.watsonplatform.net/tone-analyzer/api");
		service.setUsernameAndPassword("01e5d3ee-acc5-4311-982d-d491dfce8184", "5KaMKIAtOh82");

		Twitter4JHelper twitterHelper = null;
		try {
			twitterHelper = new Twitter4JHelper();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		HashSet<String> langs = new HashSet<>();
		langs.add("en");
		langs.add("es");

		List<Status> tweets = null;
		try {
			tweets = twitterHelper.getTweets(handle, langs, 200);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String contentItemsJson = null;
		try {
			contentItemsJson = twitterHelper.convertTweetsToPIContentItems(tweets);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return service.getTone(contentItemsJson).execute();

	}
}
