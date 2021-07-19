import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ridge {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("ridge.in"));
		final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("ridge.out"));
		String line = bufferedReader.readLine();
		long N = Long.parseLong(line);
		long[] sizes = new long[Math.toIntExact(N)];
		long[] cost = new long[Math.toIntExact(N)];
		long[] dp_cost0 = new long[Math.toIntExact(N)];
		long[] dp_cost1 = new long[Math.toIntExact(N)];
		long[] dp_cost2 = new long[Math.toIntExact(N)];
		// read from input file
		for (int i = 0; i < N; i++) {
			String[] nums = bufferedReader.readLine().split(" ");
			sizes[i] = Integer.parseInt(nums[0]);
			cost[i] = Integer.parseInt(nums[1]);
			// initial values
			if (i == 0) {
				dp_cost0[0] = 0;
				dp_cost1[0] = cost[0];
				dp_cost2[0] = 2 * cost[0];
			}
		}
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				// calculating the minimum and updating every state
				long min = Long.MAX_VALUE;
				if (sizes[i - 1] >= 0 && sizes[i] - j != sizes[i - 1]) {
					if (min > dp_cost0[i - 1]) {
						min = dp_cost0[i - 1];
					}
				}
				if (sizes[i - 1] >= 1 && sizes[i] - j != sizes[i - 1] - 1) {
					if (min > dp_cost1[i - 1]) {
						min = dp_cost1[i - 1];
					}
				}
				if (sizes[i - 1] >= 2 && sizes[i] - j != sizes[i - 1] - 2) {
					if (min > dp_cost2[i - 1]) {
						min = dp_cost2[i - 1];
					}
				}
				switch (j) {
					case 0:
						dp_cost0[i] = min;
						break;
					case 1:
						dp_cost1[i] = cost[i] + min;
						break;
					case 2:
						dp_cost2[i] = cost[i] * 2 + min;
						break;
					default:
						throw new IllegalStateException("Unexpected value: " + j);
				}
			}
		}
		bufferedWriter.write(Math.min(dp_cost0[Math.toIntExact(N - 1)],
				Math.min(dp_cost1[Math.toIntExact(N - 1)],
						dp_cost2[Math.toIntExact(N - 1)])) + "\n");
		bufferedWriter.close();
	}

}