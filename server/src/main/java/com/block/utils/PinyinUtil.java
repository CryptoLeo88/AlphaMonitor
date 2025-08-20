package com.block.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang.StringUtils;

public class PinyinUtil {
    /**
     * 将字符串中的中文转化为拼音,英文字符不变
     *
     * @param inputString
     *            汉字
     * @return
     */
    public static String getPingyin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        StringBuilder output = new StringBuilder();
        if (StringUtils.isNotBlank(inputString)) {
            char[] input = inputString.trim().toCharArray();
            try {
                String notChsTmp = "";
                for (int i = 0; i < input.length; i++) {
                    if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                        if (StringUtils.isNotBlank(notChsTmp)){
                            output.append(notChsTmp);
                            output.append(" ");
                            notChsTmp = "";
                        }
                        String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                        output.append(temp[0]);
                        if (i < input.length - 1){
                            output.append(" ");
                        }
                    } else {
                        notChsTmp += java.lang.Character.toString(input[i]);
                    }
                }
                //循环外面如果最后一个不是中文，最后补加上非中文字符串
                if (!StringUtils.isNotBlank(notChsTmp)){
                    output.append(notChsTmp);
                    notChsTmp = "";
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }
        return output.toString();
    }

    /**
     * 汉字转换位汉语拼音首字母，英文字符不变
     *
     * @param chines
     *            汉字
     * @return 拼音
     */
    public String getShoupin(String chines) {
        StringBuilder pinyinName = new StringBuilder();
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char c : nameChar) {
            if (c > 128) {
                try {
                    pinyinName.append(PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat)[0].charAt(0));
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName.append(c);
            }
        }
        return pinyinName.toString().toUpperCase();
    }
}
