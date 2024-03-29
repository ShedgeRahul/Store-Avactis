package utility;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utility.Initialisation;

public class CustomListener extends Initialisation implements ITestListener 
{
	

	@Override
	public void onTestStart(ITestResult result) 
	{
		System.out.println("");
		Log.info("****************************************************************************************************");
		Log.info("                                                                                                    ");
		Log.info("                       "+result.getMethod().getMethodName()+" Started 							  ");
		Log.info("                                                                                                    ");
		Log.info("****************************************************************************************************");
		System.out.println("");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Log.info("Test case failed ");
		failed(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
