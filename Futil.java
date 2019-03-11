package zad2;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Futil {

	public static void processDir(String dirName, String resultFileName) {
		
	Predicate<Path> isfile = Files::isRegularFile;
	Predicate <Path> isTxt = p -> p.toString().endsWith(".txt");
	
	BufferedWriter writer = null;
	FileOutputStream fileStream = null;
	OutputStreamWriter out = null;
	
	try {
		fileStream = new FileOutputStream(resultFileName);
		out = new OutputStreamWriter(fileStream, "UTF-8");
		writer = new BufferedWriter(out);
		
		List<Path> list = Files.walk(Paths.get(dirName)).filter(isfile.and(isTxt)).collect(Collectors.toList());
		Stream<String> stream = null;
		List<String> l = new ArrayList<String>();
	
		for(int i = 0;i<list.size();i++) {
			stream = Files.lines(list.get(i));
			l = stream.collect(Collectors.toList());
			
			for(int j = 0;j<l.size();j++) {
				writer.write(l.get(j) + "\n");
				
			}
		}
	} catch (IOException e) {
		e.printStackTrace();
	}finally {
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
}
