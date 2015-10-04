import java.io.File;
import java.nio.ByteBuffer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import com.sun.*;

import java.util.*;




public class AnalyseAudio
{
	protected static int getSixteenBitSample(int high, int low)
	{
	    return (high << 8) + (low & 0x00ff);
	} 

	public static ArrayList<int[]> Analyse()
	{
		int totalFramesRead = 0;
		File fileIn = new File("C:\\clap.wav");
		try
		{
			AudioInputStream audioInputStream = 
					AudioSystem.getAudioInputStream(fileIn);
			int bytesPerFrame = 
					audioInputStream.getFormat().getFrameSize();
			int frameLength = (int) audioInputStream.getFrameLength();
			int channels = audioInputStream.getFormat().getChannels();
			int sampleSize = audioInputStream.getFormat().getSampleSizeInBits();
			boolean bigEndian = audioInputStream.getFormat().isBigEndian();
			if (bytesPerFrame == AudioSystem.NOT_SPECIFIED) 
			{
				// some audio formats may have unspecified frame size
				// in that case we may read any amount of bytes
				bytesPerFrame = 1;
			} 
			// Set an arbitrary buffer size of 1024 frames.
			int numBytes = 1024 * bytesPerFrame; 
			byte[] audioBytes = new byte[numBytes];
			try 
			{
				int numBytesRead = 0;
				int numFramesRead = 0;
				int last = 0;
				ArrayList<int[]> lst = new ArrayList<int[]>();
				// Try to read numBytes bytes from the file.
				while ((numBytesRead = 
						audioInputStream.read(audioBytes)) != -1) 
				{
					// Calculate the number of frames actually read.
					numFramesRead = numBytesRead / bytesPerFrame;
					totalFramesRead += numFramesRead;
					// Here, do something useful with the audio data that's 
					// now in the audioBytes array...
					int[][] samples = new int[channels][frameLength];

			        int sampleIndex = 0;
			        for (int t = 0; t < audioBytes.length;)
			        {
			            for (int channel = 0; channel < channels; channel++) 
			            {
			                int low = (int) audioBytes[t];
			                t++;
			                int high = (int) audioBytes[t];
			                t++;
			                int sample = getSixteenBitSample(high, low);
			                // Sample is a 16 bit consists of the 8-bit of the high and low bytes
			                samples[channel][sampleIndex] = sample;
			            }
			            sampleIndex++;
			        }
			        int[] sample = new int[samples[0].length];
			        for(int i=0; i<samples[0].length; i++)
			        {
			        	sample[i]=samples[0][i];
			        }
			        	lst.add(last, sample);
			        	last++;
			        	//System.out.println(b==0?"First channel:\n\n":"Second channel:\n\n")
				}
				return lst;  
			
			}
			catch (Exception e) 
			{ 
				System.out.println(e);
			}
	  } 
		catch (Exception ex)
		{ 
			System.out.println(ex);
		}
		return null;		
}
	public static void main(String[] args) 
	{
		ArrayList<int[]> lst = Analyse();
		int [] arr;
		for(int i=0; i<lst.size() ; i++)
        {	
        	arr= lst.get(i);
        	for(int b=0; b<arr.length; b++)
        	{
        		System.out.println(arr[b]);
        	}
        }
	}
}

