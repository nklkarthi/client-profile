package com.csg.ibm;

import java.util.HashSet;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csg.ibm.util.Twitter4JHelper;
import com.ibm.watson.developer_cloud.personality_insights.v2.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v2.model.Profile;

import twitter4j.Status;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
public class TwitterAnalysisController {

	@RequestMapping("/twitterAnalysis")
	public Profile getTwitterAnalysis() {

		String handle = "@realDonaldTrump";

		PersonalityInsights service = new PersonalityInsights();
		service.setUsernameAndPassword("9249b4fe-208a-4f8a-8a0d-5982b520e9c2", "iFjAqauMvOe5");

		Twitter4JHelper twitterHelper = null;
		try {
			twitterHelper = new Twitter4JHelper();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		HashSet<String> langs = new HashSet<String>();
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

		return service.getProfile(contentItemsJson).execute();

	}
}
