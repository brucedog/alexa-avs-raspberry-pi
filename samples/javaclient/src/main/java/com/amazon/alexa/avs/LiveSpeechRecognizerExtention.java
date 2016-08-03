package com.amazon.alexa.avs;

import edu.cmu.sphinx.api.AbstractSpeechRecognizer;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.frontend.util.StreamDataSource;

import java.io.IOException;

/**
 * Created by bruceog on 8/2/2016.
 */
public class LiveSpeechRecognizerExtention extends AbstractSpeechRecognizer {
    private final MicrophoneExtention microphone;

    public LiveSpeechRecognizerExtention(Configuration configuration) throws IOException {
        super(configuration);
        this.microphone = new MicrophoneExtention(16000, 16, true, false);
        ((StreamDataSource) this.context.getInstance(StreamDataSource.class)).setInputStream(this.microphone.getStream());
    }

    public void startRecognition(boolean clear) {
        this.recognizer.allocate();
        this.microphone.startRecording();
    }

    public void stopRecognition() {
        this.microphone.stopRecording();
        this.recognizer.deallocate();
        this.closeRecognitionLine();
    }

    public void closeRecognitionLine(){
        microphone.closeLine();
    }
}
