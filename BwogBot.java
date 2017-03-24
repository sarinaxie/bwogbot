import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

//used SeparateChainingMap because the pairs don't need to be in any sort of order and inserting is faster with SCM
public class BwogBot {
	private SeparateChainingMap<String, Integer> map;

	public BwogBot() {
		map = new SeparateChainingMap<String, Integer>();
	}

	public void readFile(String fileName) throws IOException {
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String sentence= "";
		
		while ((sentence = br.readLine()) != null) {
			String[] array = sentence.split("\\s+");
			int num = 0;
			System.out.println(sentence);
			for (int i = 0; i < array.length; i++) {
				if (map.get(array[i]) == null) {
					map.put(array[i], 1);
				}
				else {
					num = map.get(array[i]);
					map.put(array[i], num + 1);
				}	
			}
		}
	}

	public int getCount(String word) {
		return map.get(word);
	}

	public List<String> getNMostPopularWords(int n) {
		return null;
	}

	public Map<String, Integer> getMap() {
		return map;
	}

	public static void main(String[] args) throws IOException {
		BwogBot bot = new BwogBot();
		bot.readFile("comments.txt");
		System.out.println(bot.getCount("hamdel"));
		System.out.println(bot.getNMostPopularWords(100));
	}
}
