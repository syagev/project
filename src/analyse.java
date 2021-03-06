import java.io.File;
import java.nio.ByteBuffer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import com.sun.*;

public class analyse 
{
	protected int getSixteenBitSample(int high, int low) {
	    return (high << 8) + (low & 0x00ff);
	} 

	public analyse()
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
			 
			        	//System.out.println(b==0?"First channel:\n\n":"Second channel:\n\n");
			        	for(int i = 0 ; i<samples[0].length ; i++)
			        	{
			        		System.out.println(samples[1][i]);
			        	}
			        
				/*
					short[] shorts = new short[audioBytes.length/2];
					// to turn bytes to shorts as either big endian or little endian. 
					ByteBuffer.wrap(audioBytes).order(java.nio.ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts);
					
					for(int i = 0; i<shorts.length; i=i+2)
					{
						System.out.println(shorts[i]);
					}
				*/
				
				/*
					byte[] a = new byte[2];
					 ByteBuffer bb;
					  	Short n;
					for(int i = 0; i<audioBytes.length; i=i+4)
					{
						a[0] = (byte)audioBytes[i];
						//a[1] = audioBytes[i+1];
						a[1] = (byte)audioBytes[i+1];
						//a[3] = audioBytes[i+3];
						bb = ByteBuffer.wrap(a).order(java.nio.ByteOrder.LITTLE_ENDIAN);
						n = bb.getShort();
						System.out.println(n);
					}
					*/
				}
				/*
				//check volume
				long sum = 0;
			    int len = audioBytes.length;
			    for (int i=0; i<len; i++) {
			        sum += audioBytes[i];
			    }
			    double average = (double)sum/len;

			    double sumMeanSquare = 0;;
			    for (int i=0; i<len; i++) {
			        double f = audioBytes[i] - average;
			        sumMeanSquare += f * f;
			    }
			    double averageMeanSquare = sumMeanSquare/len;
			    double rootMeanSquare = Math.sqrt(averageMeanSquare);

			    System.out.println(rootMeanSquare);
			    */
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
	  
}
	public static void main(String[] args) 
	{
		analyse pl = new analyse();
	}
}
