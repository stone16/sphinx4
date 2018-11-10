
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

public class TranscriberDemo {

    public static void main(String[] args) throws Exception {

        Configuration configuration = new Configuration();

        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

        // use microphone as the speech source
//        LiveSpeechRecognizer liveSpeechRecognizer = new LiveSpeechRecognizer(configuration);
//        liveSpeechRecognizer.startRecognition(true);
//        SpeechResult liveResult = liveSpeechRecognizer.getResult();
//        liveSpeechRecognizer.stopRecognition();


        // RIFF (little-endian) data, WAVE audio, Microsoft PCM, 16 bit, mono 16000 Hz
        // RIFF (little-endian) data, WAVE audio, Microsoft PCM, 16 bit, mono 8000 Hz
        StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);
        InputStream stream = new FileInputStream(new File("you-can-do-it.wav"));

        recognizer.startRecognition(stream);
        SpeechResult result;
        while ((result = recognizer.getResult()) != null) {
            System.out.format("Hypothesis: %s\n", result.getHypothesis());
        }
        recognizer.stopRecognition();
    }
}

