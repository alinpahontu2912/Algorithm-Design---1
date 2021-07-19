build: 
	javac -d . Crypto.java | javac -d . Stocks.java | javac -d . Valley.java | javac -d . Ridge.java | javac -d . Trigigel.java

run-p1: 
	java Crypto

run-p2: 
	java Stocks

run-p3: 
	java Valley

run-p4:
	java Ridge

run-p5:
	java Trigigel

clean:
	rm -f *.class

