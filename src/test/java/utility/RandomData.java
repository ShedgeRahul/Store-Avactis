package utility;

import java.util.Random;

public class RandomData 
{
	public static int randomNumber()
	{
		Random random = new Random();
		int randomNum = random.nextInt(1000);
		//System.out.println(randomNum);
		return randomNum;
	}

}
