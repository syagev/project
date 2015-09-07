import java.io.File;
import java.nio.ByteBuffer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import com.sun.*;

public class analyse 
{
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
					
				
					short[] shorts = new short[audioBytes.length/2];
					// to turn bytes to shorts as either big endian or little endian. 
					ByteBuffer.wrap(audioBytes).order(java.nio.ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts);
					
					for(int i = 0; i<shorts.length; i=i+2)
					{
						System.out.println(shorts[i]);
					}
				
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
			catch (Exception e) { 
				System.out.println(e);
			}
	  } 
		catch (Exception ex) { 
			System.out.println(ex);
	  }
	  
}
	public static void main(String[] args) 
	{
		analyse pl = new analyse();
	}
}
