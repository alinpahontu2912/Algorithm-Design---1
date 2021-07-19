import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Trigigel {
	
	public static long[][] multiply_matrix(long[][] M1, long[][] M2) {
		long[][] res = new long[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				long sum = 0;
				for (int k = 0; k < 4; k++) {
					sum += M1[i][k] * M2[k][j];

				}
				res[i][j] = sum % 1000000007;
			}
		}
		return res;
	}
	// get final result
	public static long[] get_result(long[] dp, long[][] M) {
		long[] res = new long[4];
		for (int i = 0; i < 4; i++) {
			long sum = 0;
			for (int k = 0; k < 4; k++) {
				sum += dp[k] * M[k][i];
			}
			res[i] = sum % 1000000007;
		}
		return res;
	}
	// calculate the power of matrix
	public static long[][] power_matrix(long[][] M, long p) {
		long[][] identity = new long[4][4];
		for (int i = 0; i < 4; i++) {
			identity[i][i] = 1;
		}

		while (p != 1) {
			if (p % 2 == 0) {
				M = multiply_matrix(M, M);
				p /= 2;
			} else {
				identity = multiply_matrix(M, identity);
				p--;
			}
		}
		return multiply_matrix(M, identity);
	}


	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("trigigel.in"));
		final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("trigigel.out"));
		String line = bufferedReader.readLine();
		final long N = Long.parseLong(line);
		long[] dp = new long[4];
		// initial state
		dp[0] = 1;
		dp[1] = 0;
		dp[2] = 0;
		dp[3] = 0;
		// initializing the three matrixes
		long[][] Mod0 = new long[4][4];
		long[][] Mod1 = new long[4][4];
		long[][] Mod2 = new long[4][4];
		for (int i = 0; i < 4; i++) {
			Mod0[i][i] = 1;
			Mod1[i][i] = 1;
			Mod2[i][i] = 1;
		}
		Mod0[0][1] = 1;
		Mod0[3][1] = 1;

		Mod1[0][2] = 1;
		Mod1[1][2] = 1;

		Mod2[0][3] = 1;
		Mod2[2][3] = 1;
		
		
		long[][] result;
		result = multiply_matrix(Mod0, Mod1);
		result = multiply_matrix(result, Mod2);
		result = power_matrix(result, N / 3);
		if (N % 3 == 1) {
			result = multiply_matrix(result, Mod0);
		} else if (N % 3 == 2) {
			result = multiply_matrix(result, Mod0);
			result = multiply_matrix(result, Mod1);
		}
		dp = get_result(dp, result);
		
		long res = dp[1] % 1000000007 + dp[2] % 1000000007 + dp[3] % 1000000007;
		bufferedWriter.write(res % 1000000007 + "\n");
		bufferedWriter.close();
	}

}
