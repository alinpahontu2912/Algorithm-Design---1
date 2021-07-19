import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Stocks {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("stocks.in"));
		final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("stocks.out"));
		String[] line = bufferedReader.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int Budget = Integer.parseInt(line[1]);
		int Loss = Integer.parseInt(line[2]);
		int[] init_prices = new int[N];
		int[] low_prices = new int[N];
		int[] max_prices = new int[N];

		int[][][] dp = new int[N + 1][Budget + 1][Loss + 1];
		// read from input file
		for (int i = 0; i < N; i++) {
			String[] nums = bufferedReader.readLine().split(" ");
			init_prices[i] = Integer.parseInt(nums[0]);
			low_prices[i] = Integer.parseInt(nums[1]);
			max_prices[i] = Integer.parseInt(nums[2]);
		}

		// check result for every existing state
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= Budget; j++) {
				for (int k = 0; k <= Loss; k++) {
					dp[i][j][k] = dp[i - 1][j][k];

					if (k >= (init_prices[i - 1] - low_prices[i - 1])
							&& (j >= init_prices[i - 1])) {
						int sol = dp[i - 1][j
								- init_prices[i - 1]][k - (init_prices[i - 1] - low_prices[i - 1])]
								+ max_prices[i - 1] - init_prices[i - 1];
						dp[i][j][k] = Math.max(dp[i - 1][j][k], sol);
					}

				}
			}
		}

		bufferedWriter.write(dp[N][Budget][Loss] + "\n");
		bufferedWriter.close();


	}
}
