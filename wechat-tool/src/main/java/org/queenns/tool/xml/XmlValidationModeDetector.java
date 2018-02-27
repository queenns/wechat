package org.queenns.tool.xml;

import org.queenns.tool.util.StringUtil;

import java.io.*;

/**
 * Created by lxj on 18-2-23
 * <p>
 * XML验证模式检测器
 * <p>
 * <reference source Spring>
 */
public class XmlValidationModeDetector {

    /**
     * 禁用验证
     */
    public static final int VALIDATION_NONE = 0;

    /**
     * 自动检测模式,原因是找不到明确的指示(可能是被某些特殊字符阻塞了,或者其它原因)
     */
    public static final int VALIDATION_AUTO = 1;

    /**
     * DTD验证模式(找到'DOCTYPE'声明)
     */
    public static final int VALIDATION_DTD = 2;

    /**
     * XSD验证模式(没有找到'DOCTYPE"声明).
     */
    public static final int VALIDATION_XSD = 3;

    /**
     * XML文档中声明DTD用于验证的标记
     */
    private static final String DOCTYPE = "DOCTYPE";

    /**
     * XML注释开始的标记
     */
    private static final String START_COMMENT = "<!--";

    /**
     * XML注释结束的标记
     */
    private static final String END_COMMENT = "-->";

    /**
     * XML开头标记
     */
    private static final int START_TAG = '<';

    /**
     * 指示当前解析位置是否在XML注释中.
     */
    private boolean inComment;

    /**
     * 检测XML验证模式
     * <p>
     * try语句块写法,jdk1.7及更高版本,try-with-resource异常处理和资源关闭新特性{@link AutoCloseable autoCloseable}
     *
     * @param inputStream 　文档源
     * @return VALIDATION_TYPE
     * @throws IOException throws Exception
     */
    public int detectValidationMode(InputStream inputStream) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            boolean dtdValidated = false;

            String content;

            while ((content = reader.readLine()) != null) {

                content = consumeCommentTokens(content);

                if (this.inComment || !StringUtil.hasText(content)) continue;

                if (hasDoctype(content)) {

                    dtdValidated = true;

                    break;

                }

                if (hasOpeningTag(content)) break;

            }

            return (dtdValidated ? VALIDATION_DTD : VALIDATION_XSD);

        } catch (CharConversionException ex) {

            return VALIDATION_AUTO;

        }

    }

    /**
     * 在给定的字符串中使用所有主要的注释数据并返回剩余的内容,这些内容可能是空的,因为提供的内容可能是所有的注释数据.
     * 就我们的目的而言，从第一个非注释内容的第一部分将是DOCTYPE声明或文档的根元素之后，在一行中去掉主要的注释内容是很重要的.
     */
    private String consumeCommentTokens(String line) {

        if (!line.contains(START_COMMENT) && !line.contains(END_COMMENT)) return line;

        while ((line = consume(line)) != null) {

            if (!this.inComment && !line.trim().startsWith(START_COMMENT)) return line;

        }

        return line;

    }

    /**
     * 使用下一个注释标记,更新'inComment'标志并返回剩余的内容.
     */
    private String consume(String line) {

        int index = (this.inComment ? endComment(line) : startComment(line));

        return (index == -1 ? null : line.substring(index));

    }

    private int startComment(String line) {

        return commentToken(line, START_COMMENT, true);

    }

    private int endComment(String line) {

        return commentToken(line, END_COMMENT, false);

    }

    /**
     * 尝试使用所提供的令牌对提供的内容进行消费,并将注释解析状态更新为所提供的值.
     * 如果没有找到令牌,则将索引返回到标记或-1之后的内容.
     */
    private int commentToken(String line, String token, boolean inCommentIfPresent) {

        int index = line.indexOf(token);

        if (index > -1) this.inComment = inCommentIfPresent;

        return (index == -1 ? index : index + token.length());

    }

    /**
     * 内容是否包含DTD DOCTYPE声明
     */
    private boolean hasDoctype(String content) {

        return content.contains(DOCTYPE);

    }

    /**
     * 内容是否包含XML开头标记,如果解析状态当前处于XML注释中,总是返回false.
     * 将剩余的内容传递给该方法之前,预期所有的注释标记都将用于提供的内容.
     */
    private boolean hasOpeningTag(String content) {

        if (this.inComment) return false;

        int index = content.indexOf(START_TAG);

        return (index > -1 && (content.length() > index + 1) && Character.isLetter(content.charAt(index + 1)));

    }

}