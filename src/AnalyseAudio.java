import java.io.File;
import java.nio.ByteBuffer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import com.sun.*;

import java.util.*;

public class AnalyseAudio
{	
	public static int NumOfDigits(int x)
	{
		int n = 0;
		while(x!=0)
		{
			x= x/10;
			n++;
		}
		return n;
	}
	public static int BiggestDigit(int x)
	{
		int n = 0;
		while(x > 0)
		{
			if(x<10)
				n = x;
			x= x/10;
		}
		return n;
	}
	public static boolean Similar(int a,int b)
	{
		if(a>900 && b>900)
		{	
			int aNum = NumOfDigits(a);
			int bNum = NumOfDigits(b);
			if(aNum == bNum)
			{
				int a1 = BiggestDigit(a);
				int b1 = BiggestDigit(b);
				if((a1 == b1 || a1 == (b1+1) || a1 == (b1-1)))
				{
					return true;
				}
				return false;
			}
			else if(aNum == (bNum+1))
			{
				if((aNum == 1) && (bNum ==9))
					return true;
				return false;
			}
			else if(aNum == (bNum-1))
			{
				if((aNum == 9) && (bNum ==1))
					return true;
				return false;
			}
			return false;	
		}
		return false;
	}
	public static void main(String[] args) 
	{
		GraphingAudio g = new GraphingAudio();
		ArrayList<int[]> lst = g.getList(); 
		int [] preArr;
		int [] arr;
		for(int i=0; i<lst.size() ; i++)
		{	
			arr= lst.get(i);
			System.out.println(arr[0]+ "\n"+arr[1]+ "\n"+arr[2]+ "\n"+arr[3]+ "\n"+arr[4]+ "\n"+arr[5]);
        }
		int [] arr2;
		int [] arr3;
		int [] arr4;
		for(int i=0; i<lst.size() ; i++)
        {	
        	arr= lst.get(i);
        	if(Similar(arr[0], arr[1])&& Similar(arr[0], arr[2])&&Similar(arr[0], arr[3])&&Similar(arr[0], arr[4])&&Similar(arr[0], arr[5]))
        	{
        		arr2= lst.get(i+1);
        		if(Similar(arr2[0], arr2[1])&& Similar(arr2[0], arr2[2])&&Similar(arr2[0], arr2[3])&&Similar(arr2[0], arr2[4])&&Similar(arr2[0], arr2[5]))
            	{
            		arr3= lst.get(i+2);
            		if(Similar(arr3[0], arr3[1])&& Similar(arr3[0], arr3[2])&&Similar(arr3[0], arr3[3])&&Similar(arr3[0], arr3[4])&&Similar(arr3[0], arr3[5]))
            		{
            			arr4= lst.get(i+3);
            			if(arr4[0]<(arr3[5]/2+100)&&arr4[1]<(arr3[5]/2+100)&&arr4[2]<(arr3[5]/2+100)&&arr4[3]<(arr3[5]/2+100)&&arr4[4]<(arr3[5]/2+100)&&arr4[5]<(arr3[5]/2+100))
            			{
            				preArr = lst.get(i-1);
                			if(!Similar(arr[0], preArr[0]) && !Similar(arr[0], preArr[1])&& !Similar(arr[0], preArr[2])&& !Similar(arr[0], preArr[3])&& !Similar(arr[0], preArr[4])&& !Similar(arr[0], preArr[5]))
                			{
                				i=i+2;
                				System.out.println("clap!");
                			}
            			}
            		}
            		else if(arr3[0]<(arr2[5]/2+100)&&arr3[1]<(arr2[5]/2+100)&&arr3[2]<(arr2[5]/2+100)&&arr3[3]<(arr2[5]/2+100)&&arr3[4]<(arr2[5]/2+100)&&arr3[5]<(arr2[5]/2+100))
                	{
            			preArr = lst.get(i-1);
            			if(!Similar(arr[0], preArr[0]) && !Similar(arr[0], preArr[1])&& !Similar(arr[0], preArr[2])&& !Similar(arr[0], preArr[3])&& !Similar(arr[0], preArr[4])&& !Similar(arr[0], preArr[5]))
            			{
            				i++;
            				System.out.println("clap!");
            			}
                	}
            		
            	}
        		else if(arr2[0]<(arr[5]/2+100)&&arr2[1]<(arr[5]/2+100)&&arr2[2]<(arr[5]/2+100)&&arr2[3]<(arr[5]/2+100)&&arr2[4]<(arr[5]/2+100)&&arr2[5]<(arr[5]/2+100))
        		{
        			preArr = lst.get(i-1);
        			if(!Similar(arr[0], preArr[0]) && !Similar(arr[0], preArr[1])&& !Similar(arr[0], preArr[2])&& !Similar(arr[0], preArr[3])&& !Similar(arr[0], preArr[4])&& !Similar(arr[0], preArr[5]))
        			{
        				System.out.println("clap!");
        			}
        		}
        	}
        }
	}
}

