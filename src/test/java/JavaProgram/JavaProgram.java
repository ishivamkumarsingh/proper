package JavaProgram;

import java.util.Arrays;

public class JavaProgram {
	    public static void main(String[] args) {
	        int[] numbers = {21, 4, 67, 41, 9, 57, 88};
	        Arrays.sort(numbers);
	        System.out.println("2nd Min Number: " + numbers[1]);
	        System.out.println("2nd Max Number: " + numbers[numbers.length - 2]);
	    }
	}

