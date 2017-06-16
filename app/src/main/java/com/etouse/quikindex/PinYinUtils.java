package com.etouse.quikindex;

import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by 高赛 on 2016/9/21.
 */
public class PinYinUtils {
    /**
     * 返回文本对应的拼音,比较耗时
     * @param text
     * @return
     */
    public static String getPinYinString(String text) {
        if(TextUtils.isEmpty(text)) return null;
        // 不支持多个汉字，只能单个
//        PinyinHelper.toHanyuPinyinStringArray()
        HanyuPinyinOutputFormat formart = new HanyuPinyinOutputFormat();
        //WITH_TONE_MARK 声调加在字母上， WITH_TONE_NUMBER YANG2LIU3
//        formart.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);// 声调加在字母上
        formart.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        formart.setCaseType(HanyuPinyinCaseType.UPPERCASE);

        StringBuilder builder = new StringBuilder();
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // 对文本做一个简单的区分
            // -128 - 127
            if(chars[i] > 127) {
                // 可能是汉字
                try {
                    // 多音字，所以是array
                    String[] pinYinArray = PinyinHelper.toHanyuPinyinStringArray(chars[i], formart);
                    // 多音字没法选择，只能选0，后台数据支持
                    if(pinYinArray == null || pinYinArray.length == 0) continue;
                    builder.append(pinYinArray[0]);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            } else {
                // 肯定不是汉字，A B C S
                builder.append(chars[i]);
            }
        }
        return builder.toString();
    }
}
