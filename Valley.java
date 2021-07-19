import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Valley {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("valley.in"));
		final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("valley.out"));
		String line = bufferedReader.readLine();
		int N = Integer.parseInt(line);
		Integer[] arr = new Integer[N];
		long res = 0;
		String[] nums = bufferedReader.readLine().split(" ");
		int min = Integer.MAX_VALUE;
		int pos = -1;
		// read from file and find the position of minimum
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(nums[i]);
			if (min > arr[i]) {
				pos = i;
				min = arr[i];
			}
		}
		// extreme cases when minimumm is an extremity
		if (pos == N - 1) {
			res += Math.abs(arr[N - 2] - arr[N - 1]);
			arr[N - 2] = arr[N - 1];
			pos = N - 2;
		} else if (pos == 0) {
			res += Math.abs(arr[0] - arr[1]);
			arr[1] = arr[0];
			pos = 1;
		}
		// check if ordered, otherwise add the cost of ordering them
		int min2 = arr[0];
		for (int i = 1; i <= pos; i++) {
			if (min2 < arr[i]) {
				res += arr[i] - min2;
				arr[i] = min2;
			} else {
				min2 = arr[i];
			}
		}
		// check if ordered, otherwise add the cost of ordering them
		min2 = arr[N - 1];
		for (int i = N - 1; i >= pos; i--) {
			if (min2 < arr[i]) {
				res += arr[i] - min2;
				arr[i] = min2;
			} else {
				min2 = arr[i];
			}
		}

		bufferedWriter.write(res + "\n");
		bufferedWriter.close();

	}

}
