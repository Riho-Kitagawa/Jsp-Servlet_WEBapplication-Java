package jp.co.sss.crud.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Rihio
 * 入力チェック用のクラス
 *
 */
public class InputChecker {
	/** インスタンス化を禁止 */
	private InputChecker() {
	}

	/**
	 * 指定された範囲内の整数値であるかを判定する
	 *
	 * @param min
	 *            整数値の下限
	 * @param max
	 *            整数値の上限
	 * @param inputString
	 *            判定の対象となる文字列
	 * @return 入力値が規定の範囲外の場合はtrue、範囲内の場合はfalse
	 */
	public static boolean checkNumber(String inputString, int min, int max) {
		// 判定結果
		boolean checkErrorFlag = false;

		try {
			// 入力値をString型からint型に変換
			int inputNumber = Integer.parseInt(inputString);

			// 入力値が下限値と上限値の範囲内の値であるかを判定
			if (inputNumber < min || max < inputNumber) {
				checkErrorFlag = true;
			}
		} catch (NumberFormatException e) {
			// int型への変換に失敗した場合は判定エラーと判定
			checkErrorFlag = true;
		}

		if (checkErrorFlag) {
			// エラーメッセージを出力
			System.out.print(min + "以上" + max + "以下の整数を入力してください：");
		}

		return checkErrorFlag;
	}

	/**
	 * 指定された範囲内の文字列であるかを判定する
	 *
	 * @param min
	 *            文字数の下限
	 * @param max
	 *            文字数の上限
	 * @param inputString
	 *            判定の対象となる文字列
	 * @return 入力値が規定の範囲外の場合はtrue、範囲内の場合はfalse
	 */
	public static boolean checkString(String inputString, int min, int max) {
		// 判定結果
		boolean checkErrorFlag = false;

		// 入力値が下限値と上限値の範囲内の値であるかを判定
		if (inputString.length() < min || inputString.length() > max) {
			checkErrorFlag = true;
			// エラーメッセージを出力
			System.out.print(min + "文字以上" + max + "文字以下の文字列を入力してください：");
		}

		return checkErrorFlag;
	}

	/**
	 * 日付の書式を判定する
	 *
	 * @param inputString
	 *            判定の対象となる文字列
	 * @return 入力値が規定の範囲外の場合はtrue、範囲内の場合はfalse
	 */
	public static boolean checkDate(String inputString) {
		// 判定結果
		boolean checkErrorFlag = false;

		// 日付の形式の準備
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

		// 実在する日付であるかを判定するための設定
		dateFormat.setLenient(false);

		try {
			// 対象文字列が空文字かを判定
			if (inputString.length() == 0) {
				checkErrorFlag = true;
			} else {
				// 正規表現により入力値が正しい書式であるかを判定
				Pattern pattern = Pattern.compile("^[0-9]{1,4}/[0-9]{1,2}/[0-9]{1,2}$");
				Matcher matcher = pattern.matcher(inputString);

				if (matcher.find()) {
					// 入力値を指定した形式でDate型に変換
					dateFormat.parse(inputString);
				} else {
					checkErrorFlag = true;
				}
			}
		} catch (ParseException e) {
			// Date型への変換に失敗した場合は判定エラーと判定
			checkErrorFlag = true;
		}

		if (checkErrorFlag) {
			// エラーメッセージを出力
//			System.out.print("正しい形式(西暦年/月/日)で日付を入力してください：");
		}

		return checkErrorFlag;
	}
}
