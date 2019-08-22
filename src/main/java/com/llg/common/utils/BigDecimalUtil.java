package com.llg.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal算法
 * 
 * @author <a href="mailto:Administrator@abc.com">Administrator</a>
 * @version 1.0
 * @since 2013-7-26
 */
public class BigDecimalUtil {

	private static int DEF_DIV_SCALE = 10; // 默认精确的小数位

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		double count = b1.add(b2).doubleValue();
		BigDecimal bigDecimal = new BigDecimal(count);

		return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}


	/**
	 * 提供精确的加法运算。
	 *
	 * @param v1	被加数
	 * @param v2	加数
	 * @param v3	加数
	 * @return 三个参数的和
	 */
	public static double addThree(double v1, double v2, double v3) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		BigDecimal b3 = new BigDecimal(Double.toString(v3));

		double count = b3.add(b1.add(b2)).doubleValue();
		BigDecimal bigDecimal = new BigDecimal(count);

		return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}


	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	public static BigDecimal mulBigDecimal(BigDecimal v1, BigDecimal v2) {
		return v1.multiply(v2);
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}

	/**
	 * 提供精确的小数位处理，去掉保留位数后的数字
	 * 
	 * @param v
	 *            需要处理的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 去掉保留位数后的结果
	 */
	public static double decimal(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}

	/**
	 * 提供精确的小数位处理，去掉保留位数后的数字
	 * 
	 * @param v
	 *            需要处理的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 去掉保留位数后的结果
	 */
	public static double decimalDown(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 计算利息
	 * 
	 * @param apr
	 *            利率（x％）
	 * @param days
	 *            期限（天）
	 * @param money
	 *            本金
	 * @return
	 */
	public static double calInterest(double apr, int days, double money) {
		return BigDecimalUtil.div(BigDecimalUtil.mul(BigDecimalUtil.mul(apr, days), money), 36000, 2);
	}

	/**
	 * double转bigdecimal
	 * 
	 * @auth zyx@panguyr.com
	 * @date 2018年4月20日
	 * @version 1.0
	 * @param d
	 * @return
	 */
	public static BigDecimal doubleToBigDecimal(double d) {
		String doubleStr = String.valueOf(d);
		if (doubleStr.indexOf(".") != -1) {
			int pointLen = doubleStr.replaceAll("\\d+\\.", "").length(); // 取得小数点后的数字的位数
			pointLen = pointLen > 16 ? 16 : pointLen; // double最大有效小数点后的位数为16
			double pow = Math.pow(10, pointLen);
			long tmp = (long) (d * pow);
			return new BigDecimal(tmp).divide(new BigDecimal(pow));
		}
		return new BigDecimal(d);
	}

	public static Double formatDouble(Double d) {
		// 旧方法，已经不再推荐使用
		// BigDecimal bg = new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP);

		// 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
		BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);
		return bg.doubleValue();
	}

	@SuppressWarnings("unused")
	public static void main(String args[]) {
		System.out.println(doubleToBigDecimal(275.753d));
		BigDecimal b = new BigDecimal("2.357");
		b.setScale(2,BigDecimal.ROUND_HALF_DOWN);
		System.out.println(decimalDown(2.357,2));
	}

}
