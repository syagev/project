import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import com.sun.*;

public class analyse {
	public analyse()
	{
		int totalFramesRead = 0;
		File fileIn = new File("C:\\1235.wav");
		try {
	  		AudioInputStream audioInputStream = 
	  			AudioSystem.getAudioInputStream(fileIn);
	  		int bytesPerFrame = 
	    			audioInputStream.getFormat().getFrameSize();
    			if (bytesPerFrame == AudioSystem.NOT_SPECIFIED) {
			    // some audio formats may have unspecified frame size
			    // in that case we may read any amount of bytes
			    bytesPerFrame = 1;
	  		} 
	  	
	  	// Set an arbitrary buffer size of 1024 frames.
		int numBytes = 1024 * bytesPerFrame; 
		byte[] audioBytes = new byte[numBytes];
		try {
			int numBytesRead = 0;
			int numFramesRead = 0;
			
	    		// Try to read numBytes bytes from the file.
	    		while ((numBytesRead = 
	    				audioInputStream.read(audioBytes)) != -1) {
	    					
	      			// Calculate the number of frames actually read.
      				numFramesRead = numBytesRead / bytesPerFrame;
	      			totalFramesRead += numFramesRead;
	      
	      			// Here, do something useful with the audio data that's 
	      			// now in the audioBytes array...
	      			System.out.println(audioBytes);
	    		}
  		} catch (Exception ex) { 
	  	}
	}
	
	public static void main(String[] args) 
	{
		analyse pl = new analyse();
	}
}
