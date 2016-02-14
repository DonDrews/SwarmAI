package simulate;

import java.util.Random;

import script.CodeFrame;
import script.PyInterpret;

public class SwarmMain {
	
	//constants
	public static final int UNIT_AMOUNT = 5;
	public static final int MAP_SIZE = 100;
	
	public static CodeFrame cFrame;
	public static PyInterpret pyBridge;
	public static Unit[] units = new Unit[UNIT_AMOUNT];
	
	//flags
	public static boolean compileFlag = false;
	
	public static void startSim()
	{
		pyBridge = new PyInterpret();
		cFrame = new CodeFrame();
		pyBridge.updateCode(cFrame.getCode());
		
		//make units
		Random rand = new Random();
		for(int i = 0; i < UNIT_AMOUNT; i++)
		{
			units[i] = new Unit(i, rand.nextFloat() * MAP_SIZE - (MAP_SIZE / 2), rand.nextFloat() * MAP_SIZE - (MAP_SIZE / 2), (float)(rand.nextFloat() * Math.PI * 2));
		}
	}
	
	public static void updateSim()
	{
		if(compileFlag)
		{
			compileFlag = false;
			pyBridge.updateCode(cFrame.getCode());
		}
		for(Unit u:units)
		{
			pyBridge.runCode(u);
		}
	}
	
	static void printCode()
	{
		System.out.println("--------------");
		System.out.println(pyBridge.getCode());
	}

}
