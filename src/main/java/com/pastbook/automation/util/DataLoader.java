package com.pastbook.automation.util;
import com.pastbook.automation.pojo.SignIn;


public class DataLoader {

	public SignIn[][] getSignInData(String[][] excelData) {

		SignIn[][] signInDataObjList = new SignIn[excelData.length][1];

		for (int i = 0; i < excelData.length; i++) {
			String[] tempRowdata = excelData[i];

			SignIn tempSignInObj = new SignIn();

			tempSignInObj.setTestcaseId(tempRowdata[0]);
			tempSignInObj.setTestScenario(tempRowdata[1]);
			tempSignInObj.setUserName(tempRowdata[2]);
			tempSignInObj.setPassword(tempRowdata[3]);

			signInDataObjList[i][0] = tempSignInObj;

		}

		return signInDataObjList;
	}

}
