package script;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simulate.SwarmMain;
import simulate.Unit;

public class PyInterpret {
	
	private String code;
	private ProcessBuilder pb;
	private Process p;
	
	private static String startCode = 
			 "import sys\n" +
			 "class Unit:\n" +
			 "        def __init__(self, i, xpos, ypos, directions):\n" +
			 "                self.id = int(i)\n" +
			 "                self.x = float(xpos)\n" +
			 "                self.y = float(ypos)\n" +
			 "                self.d = float(directions)\n" +
			 "def move(spd):\n" +
			 "        print 'mv ' + str(spd)\n" +
			 "        return\n" +
			 "def turn(rad):\n" +
			 "        print 'tr ' + str(rad)\n" +
			 "        return\n" +
			 "def setDir(dir):\n" +
			 "        print 'sd ' + str(dir)\n" +
			 "        return\n" +
			 "unitAmount = (len(sys.argv) - 1) / 4\n" +
			 "units = []\n" +
			 "me = None\n" +
			 "for i in range(0, unitAmount):\n" +
			 "        if i == 0:\n" +
			 "                me = Unit(sys.argv[1 + (4 * i)], sys.argv[2 + (4 * i)], sys.argv[3 + (4 * i)], sys.argv[4 + (4 * i)])\n" +
			 "        else:\n" +
			 "                u = Unit(sys.argv[1 + (4 * i)], sys.argv[2 + (4 * i)], sys.argv[3 + (4 * i)], sys.argv[4 + (4 * i)])\n" +
			 "                units.append(u)\n";

	private static String endCode = "print 'exit'";
	
	public void updateCode(String s)
	{
		code = startCode + s + "\n" + endCode;
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("sim.py"));
			out.write(code);
			out.close();
		} catch (IOException e) {
			System.out.println("Problem with writing to code buffer file");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void runCode(Unit u)
	{
		String args[] = new String[(SwarmMain.UNIT_AMOUNT - 1) * 4];
		//make array of args
		int counter = 0;
		for(Unit unit:SwarmMain.units)
		{
			if(unit.id != u.id)
			{
				args[4 * counter] = str(unit.id);
				args[(4 * counter) + 1] = str(unit.position.x);
				args[(4 * counter) + 2] = str(unit.position.y);
				args[(4 * counter) + 3] = str(unit.direction);
				counter++;
			}
		}
		
		List<String> list = new ArrayList<String>(args.length + 6);
		list.add("python");
		list.add("sim.py");
		list.add(str(u.id));
		list.add(str(u.position.x));
		list.add(str(u.position.y));
		list.add(str(u.direction));
		list.addAll(Arrays.asList(args));
		pb = new ProcessBuilder(list);
		try {
			p = pb.start();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String ret;
			do
			{
				ret = in.readLine();
				if(ret != null)
				{
					decipher(ret, u);
				}
			}
			while(!ret.equals("exit"));
		} catch (Exception e) {
			System.out.println("Problem with running swarm code");
			e.printStackTrace();
		}
	}
	
	private String str(float f)
	{
		return Float.toString(f);
	}
	
	private String str(int i)
	{
		return Integer.toString(i);
	}
	
	public String getCode()
	{
		return code;
	}
	
	//decipher returned strings from python and update units
	private void decipher(String s, Unit u)
	{
		//move
		 if(s.startsWith("m"))
		 {
			 u.move(Float.parseFloat(s.split(" ")[1]));
		 }
		 //turn
		 else if(s.startsWith("t"))
		 {
			 u.turn(Float.parseFloat(s.split(" ")[1]));
		 }
		 //set direction
		 else if(s.startsWith("s"))
		 {
			 u.setDir(Float.parseFloat(s.split(" ")[1]));
		 }
	}
}
