import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Crypto {

	public static void main(String[] args) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new FileReader("crypto.in"));
		final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("crypto.out"));
		String[] line = bufferedReader.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		long B = Long.parseLong(line[1]);
		int[][] pc = new int[N][2];
		// read from file
		for (int i = 0; i < N; i++) {
			String[] nums = bufferedReader.readLine().split(" ");
			pc[i][0] = Integer.parseInt(nums[0]);
			pc[i][1] = Integer.parseInt(nums[1]);
		}
		Arrays.sort(pc, Comparator.comparingInt(o -> o[0]));
		int min = pc[0][0];
		while (B >= 0) {
			min = pc[0][0];
			for (int i = 0; i < N; i++) {
				if (pc[i][0] == min) {
					pc[i][0]++;
					B -= pc[i][1];
				} else {
					break;
				}
			}
		}
		bufferedWriter.write(min + "\n");
		bufferedWriter.flush();
		bufferedWriter.close();
	}
}
