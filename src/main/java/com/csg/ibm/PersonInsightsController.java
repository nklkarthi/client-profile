package com.csg.ibm;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.watson.developer_cloud.personality_insights.v2.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v2.model.Profile;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
public class PersonInsightsController {

	@RequestMapping("/personInsights")
	public Profile getPersonalityInsights() {
		PersonalityInsights service = new PersonalityInsights();
		service.setUsernameAndPassword("9249b4fe-208a-4f8a-8a0d-5982b520e9c2", "iFjAqauMvOe5");

		String text = "Call me Ishmael. Some years ago-never mind how long "
				+ "precisely-having little or no money in my purse, and nothing "
				+ "particular to interest me on shore, I thought I would sail about "
				+ "a little and see the watery part of the world. It is a way "
				+ "I have of driving off the spleen and regulating the circulation. "
				+ "Whenever I find myself growing grim about the mouth; whenever it "
				+ "is a damp, drizzly November in my soul; whenever I find myself "
				+ "involuntarily pausing before coffin warehouses, and bringing up "
				+ "the rear of every funeral I meet; and especially whenever my "
				+ "hypos get such an upper hand of me, that it requires a strong "
				+ "moral principle to prevent me from deliberately stepping into "
				+ "the street, and methodically knocking people's hats off-then, "
				+ "I account it high time to get to sea as soon as I can.";

		return service.getProfile(text).execute();

	}
}
