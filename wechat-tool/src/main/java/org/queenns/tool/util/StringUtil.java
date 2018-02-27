package org.queenns.tool.util;

/**
 * Created by lxj on 17-12-27
 */
public abstract class StringUtil {

    public static boolean isEmpty(String text) {

        return (text == null || "".equals(text));

    }

    public static boolean hasText(String text) {

        return hasText((CharSequence) text);

    }

    public static boolean hasText(CharSequence text) {

        if (!hasLength(text)) return false;

        int length = text.length();

        for (int i = 0; i < length; i++)

            if (!Character.isWhitespace(text.charAt(i))) return true;

        return false;

    }

    public static boolean hasLength(CharSequence text) {

        return (text != null && text.length() > 0);

    }

    public static String replace(String source, String appoint, String replace) {

        if (!hasLength(source) || !hasLength(appoint) || replace == null) return source;

        StringBuilder stringBuilder = new StringBuilder();

        int position = 0;

        int index = source.indexOf(appoint);

        int length = appoint.length();

        while (index >= 0) {

            stringBuilder.append(source.substring(position, index));

            stringBuilder.append(replace);

            position = index + length;

            index = source.indexOf(appoint, position);

        }

        stringBuilder.append(source.substring(position));

        return stringBuilder.toString();

    }

}
