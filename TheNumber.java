import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.apfloat.ApfloatRuntimeException;
import org.apfloat.Apint;
import org.apfloat.ApintMath;

public class TheNumber {

	public static void main(String[] args) throws ApfloatRuntimeException, IOException {
		Apint a = new Apint(2);
		Apint swap = new Apint(0);
		a = ApintMath.pow(a, 8589934591l);
		a = a.subtract(new Apint(1));
		System.out.println("Calculations done");
		PrintWriter pls = null;
		long size = a.size();
		System.out.println(size);
		System.out.println(size / 1000000);
		//this records the last 1,000,000 digits to a txt and then cuts them off the end of the number
		for (long i = 0; i <= size / 1000000; i++) {
			pls = new PrintWriter("theNumber" + i + ".txt");
			Apint modFactor = ApintMath.pow(new Apint(10), 1000000);
			swap = a.mod(modFactor);
			swap.writeTo(pls);
			pls.flush();
			System.out.println("swap " + i + " done");
			a = a.divide(modFactor);
			System.out.println("divide " + i + " done");
		}
		pls.close();
		System.out.println("Temp files done");
		PrintWriter pls2 = new PrintWriter("theNumber.txt");
		//goes through all the temp txt's writes them to one final txt then deletes them
		for (long i = size / 1000000; i >= 0; i--) {
			File file = new File("theNumber" + i + ".txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			pls2.write(reader.readLine());
			reader.close();
			pls2.flush();
			file.deleteOnExit();
			System.out.println("File " + i + " deleted");
		}
		pls2.close();
		System.out.println("Done");
	}

}
