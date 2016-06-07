package com.csg.ibm;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.watson.developer_cloud.tone_analyzer.v3_beta.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3_beta.model.ToneAnalysis;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
public class ToneAnalyzerController {

	String handle = "@realDonaldTrump";

	@RequestMapping("/toneAnalysis")
	public ToneAnalysis getToneAnalyzer() {

		ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_02_11);
		service.setEndPoint("https://gateway.watsonplatform.net/tone-analyzer/api");
		service.setUsernameAndPassword("01e5d3ee-acc5-4311-982d-d491dfce8184", "5KaMKIAtOh82");

		String text = "I know the times are difficult! Our sales have been "
				+ "disappointing for the past three quarters for our data analytics "
				+ "product suite. We have a competitive data analytics product "
				+ "suite in the industry. But we need to do our job selling it! "
				+ "We need to acknowledge and fix our sales challenges. "
				+ "We can’t blame the economy for our lack of execution! "
				+ "We are missing critical sales opportunities. "
				+ "Our product is in no way inferior to the competitor products. "
				+ "Our clients are hungry for analytical tools to improve their "
				+ "business outcomes. Economy has nothing to do with it.";

		// Call the service and get the tone
		return service.getTone(text).execute();

	}
}
