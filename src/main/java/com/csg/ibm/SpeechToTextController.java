package com.csg.ibm;

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
public class SpeechToTextController {

	@RequestMapping("/speechToText")
	public SpeechResults getPersonalityInsights() {
		SpeechToText service = new SpeechToText();
		service.setUsernameAndPassword("9d68ce5f-644e-4c47-b579-4b1a9d608a95", "Ow5GLPM2Eb7n");
		service.setEndPoint("https://stream.watsonplatform.net/speech-to-text/api");
		SpeechResults sr = null;
		try {
			File audio = new File("src/main/resources/trumpspeech1.wav");
			sr = service.recognize(audio).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sr;
	}
}
