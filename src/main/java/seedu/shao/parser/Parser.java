package seedu.shao.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.stream.Stream;

import seedu.shao.ui.Ui;

public class Parser {

	public LocalDateTime getBy(String[] inputArr, Ui ui) {
		int l = inputArr.length;
		for (int i = 0; i < l; i++) {
			if (i < l - 1 && inputArr[i].equals("/by")) {
				return parseDateTimeStr(sliceArrAndConcate(inputArr, i + 1, l));
			}
		}
		return null;
	}

	public LocalDateTime parseDateTimeStr(String dateTimeStr) {
		if (dateTimeStr.isBlank()) {
			return null;
		}
		String[] dateTimeArr = dateTimeStr.split(" ");
		LocalTime time = LocalTime.MIN;
		Integer[] dateArr;
		if (dateTimeArr.length > 1) {
			try {
				int hr = Integer.parseInt(dateTimeArr[1].substring(0, 2));
				int min = Integer.parseInt(dateTimeArr[1].substring(2, 4));
				time = LocalTime.of(hr, min);
			} catch (NumberFormatException ex) {
				Ui.printError("Oops! Time format needs to be specified in proper form.");
				return null;
			}
		}

		try {
			if (dateTimeStr.contains("/")) { // Input Format: dd/MM/YYYY
				dateArr = Stream.of(dateTimeArr[0].split("/")).map(Integer::valueOf).toArray(Integer[]::new);
				return LocalDateTime.of(LocalDate.of(dateArr[2], dateArr[1], dateArr[0]), time);
			}

			// Input Format: YYYY-MM-dd
			dateArr = Stream.of(dateTimeArr[0].split("-")).map(Integer::valueOf).toArray(Integer[]::new);
			return LocalDateTime.of(LocalDate.of(dateArr[0], dateArr[1], dateArr[2]), time);
		} catch (ArrayIndexOutOfBoundsException ex) {
			Ui.printError("Oops! Time format needs to be specified in proper form.");
			return null;
		}

	}

	public LocalDateTime[] getFromTo(String[] inputArr, Ui ui) {
		int l = inputArr.length;
		int fromStartIdx = -1, fromEndIdx = l, toStartIdx = -1;
		LocalDateTime from = LocalDateTime.MIN;
		LocalDateTime to = LocalDateTime.MIN;
		for (int i = 0; i < l; i++) {
			if (i < l - 1) {
				if (inputArr[i].equals("/from")) {
					fromStartIdx = i + 1;
				}
				if (inputArr[i].equals("/to")) {
					fromEndIdx = i;
					toStartIdx = i + 1;
				}
			}
		}
		if (fromStartIdx > -1) {
			from = parseDateTimeStr(sliceArrAndConcate(inputArr, fromStartIdx, fromEndIdx));
		}
		if (toStartIdx > -1) {
			to = parseDateTimeStr(sliceArrAndConcate(inputArr, toStartIdx, l));
		}
		return new LocalDateTime[] { from, to };
	}

	public String trimDate(String input) {
		String[] inputArr = input.split(" ");
		StringBuilder sb = new StringBuilder();
		for (String s : inputArr) {
			if (s.contains("/")) {
				return sb.toString().trim();
			}
			sb.append(s + " ");
		}
		return sb.toString().trim();
	}

	public String sliceArrAndConcate(String[] arr, int startIdx, int endIdx) {
		return String.join(" ", Arrays.copyOfRange(arr, startIdx, endIdx));
	}
}
