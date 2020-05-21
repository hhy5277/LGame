/**
 * Copyright 2008 - 2016 The Loon Game Engine Authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * @project loon
 * @author cping
 * @email：javachenpeng@yahoo.com
 * @version 0.5
 */
package loon.utils;

import loon.LSystem;

/**
 * 一个基础的时间管理用类(基本没有使用java时间库(除了获得系统时间外),方便移植)
 */
public final class TimeUtils {

	private static final long nanosPerMilli = 1000000L;

	public static enum Unit {
		NANOS, MICROS, MILLIS, SECONDS
	}

	public enum TimeFormat {
		UNDEFINED, HH_MM_SS, MM_SS_000, MM_SS_0, HH_MM_SS_000, DD_HH_MM;

		public String getFormat() {
			switch (this) {
			default:
			case HH_MM_SS:
				return "{0}:{1}:{2}";
			case HH_MM_SS_000:
				return "{0}:{1}:{2}.{3}";
			case MM_SS_000:
				return "{0}:{1}.{2}";
			case MM_SS_0:
				return "{0}:{1}.{2}";
			}
		}
	}

	private TimeUtils() {
	}

	public static float currentNanos() {
		return currentMicros() * 1000f;
	}

	public static float currentMicros() {
		return currentMillis() * 1000f;
	}

	public static float currentMillis() {
		return currentSeconds() * 1000f;
	}

	public static float currentSeconds() {
		return getSeconds(millis());
	}

	public static float currentTime(Unit unit) {
		switch (unit) {
		case NANOS:
			return currentNanos();
		case MICROS:
			return currentMicros();
		case MILLIS:
			return currentMillis();
		default:
			return currentSeconds();
		}
	}

	public static float currentTime() {
		return currentTime(getDefaultTimeUnit());
	}

	public static float convert(float time, Unit source, Unit target) {
		if (source == target)
			return time;

		float factor = 1;

		if (source == Unit.SECONDS) {
			if (target == Unit.MILLIS)
				factor = 1000f;
			else if (target == Unit.MICROS)
				factor = 1000000f;
			else
				factor = 1000000000f;
		} else if (source == Unit.MILLIS) {
			if (target == Unit.SECONDS)
				factor = 1f / 1000f;
			else if (target == Unit.MICROS)
				factor = 1000f;
			else
				factor = 1000000f;
		} else if (source == Unit.MICROS) {
			if (target == Unit.SECONDS)
				factor = 1f / 1000000f;
			else if (target == Unit.MILLIS)
				factor = 1f / 1000f;
			else
				factor = 1000f;
		} else {
			if (target == Unit.SECONDS)
				factor = 1f / 1000000000f;
			else if (target == Unit.MILLIS)
				factor = 1f / 1000000f;
			else if (target == Unit.MICROS)
				factor = 1f / 1000f;
		}

		return time * factor;
	}

	public static Unit getDefaultTimeUnit() {
		return Unit.SECONDS;
	}

	public final static long nanoTime() {
		return System.currentTimeMillis() * nanosPerMilli;
	}

	public final static long millis() {
		return System.currentTimeMillis();
	}

	public final static long nanosToMillis(long nanos) {
		return nanos / nanosPerMilli;
	}

	public final static long millisToNanos(long millis) {
		return millis * nanosPerMilli;
	}

	public final static long timeSinceNanos(long prevTime) {
		return nanoTime() - prevTime;
	}

	public final static long timeSinceMillis(long prevTime) {
		return millis() - prevTime;
	}

	public final static String formatTime(long time) {
		int steps = 0;
		while (time >= 1000) {
			time /= 1000;
			steps++;
		}
		return time + getTimeUnit(steps);
	}

	private static String getTimeUnit(int steps) {
		switch (steps) {
		case 0:
			return "ns";
		case 1:
			return "us";
		case 2:
			return "ms";
		case 3:
			return "s";
		case 4:
			return "m";
		case 5:
			return "h";
		case 6:
			return "days";
		case 7:
			return "months";
		case 8:
			return "years";
		default:
			return "d (WTF dude check you calculation!)";
		}
	}

	public static long getUTC8Days() {
		return getDays(millis(), 8);
	}

	public static long getUTCDays() {
		return getDays(millis(), 0);
	}

	public static long getDays(final long ms, final long offsetHour) {
		return (ms + (offsetHour * LSystem.HOUR)) / 1000 / 60 / 60 / 24 % 365;
	}

	public static long getHours() {
		return getHours(millis());
	}

	public static long getHours(final long ms) {
		return ms / 1000 / 60 / 60 % 24;
	}

	public static long getMinutes() {
		return getMinutes(millis());
	}

	public static long getMinutes(final long ms) {
		return ms / 1000 / 60 % 60;
	}

	public static long getSeconds() {
		return getSeconds(millis());
	}

	public static long getSeconds(final long ms) {
		return ms / 1000 % 60;
	}

	public static long getMilliSeconds(final long ms) {
		return ms % 1000;
	}

	public static String formatMillis(long val) {
		StrBuilder sbr = new StrBuilder(20);
		String sgn = "";
		if (val < 0) {
			sgn = "-";
		}
		val = MathUtils.abs(val);
		formatTime(sbr, sgn, 0, (val / 3600000));
		val %= 3600000;
		formatTime(sbr, ":", 2, (val / 60000));
		val %= 60000;
		formatTime(sbr, ":", 2, (val / 1000));
		return sbr.toString();
	}

	private static void formatTime(StrBuilder tag, String pfx, int dgt, long val) {
		tag.append(pfx);
		if (dgt > 1) {
			int pad = (dgt - 1);
			for (long i = val; i > 9 && pad > 0; i /= 10) {
				pad--;
			}
			for (int j = 0; j < pad; j++) {
				tag.append('0');
			}
		}
		tag.append(val);
	}

	public static String millisTime() {
		return formatMillis(millis());
	}

	public static String getUTC8Time() {
		return getUTCTime(8);
	}

	public static String getUTCTime() {
		return getUTCTime(0);
	}

	public static String getUTCTime(long offsetHour) {
		return getUTCTime(millis(), offsetHour);
	}

	public static String getUTCTime(long duration, long offsetHour) {
		return getUTCTime(duration + (offsetHour * LSystem.HOUR), TimeFormat.HH_MM_SS);
	}

	private static String zero(long v) {
		return MathUtils.addZeros(v, 2);
	}

	public static String getUTCTime(long duration, TimeFormat format) {
		long h = getHours(duration);
		long m = getMinutes(duration);
		long s = getSeconds(duration);
		long ms = getMilliSeconds(duration);
		switch (format) {
		case HH_MM_SS:
			return StringUtils.format(format.getFormat(), zero(h), zero(m), zero(s));
		case HH_MM_SS_000:
			return StringUtils.format(format.getFormat(), zero(h), zero(m), zero(s), ms);
		case MM_SS_000:
			return StringUtils.format(format.getFormat(), zero(m), zero(s), ms);
		case MM_SS_0:
			return StringUtils.format(format.getFormat(), zero(m), zero(s), ms / 100);
		case UNDEFINED:
		default:
			return String.valueOf(ms);
		}
	}
}
