package cn.nela.tools;

import android.text.TextUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class NumberTool {

    public static final String DEFAULT_COUNTRY_CODE = "86";

    private static Set<String> sSetCountryPrefix = null;

    public static boolean isChinaPhone(String phone) {
        /**
         * 判断字符串是否符合手机号码格式
         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
         * 电信号段: 133,149,153,170,173,177,180,181,189
         * @param str
         * @return 待检测的字符串
         */
        String telRegex = "^((\\+86)|(0086)){0,1}((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        if (TextUtils.isEmpty(phone)) {
            return false;
        } else {
            return phone.matches(telRegex);
        }
    }

    public static boolean isAllowedPassword(String password){
        Pattern pattern = Pattern.compile("[a-zA-z0-9]{1,15}");
        if (TextUtils.isEmpty(password)) {
            return false;
        } else {
            return pattern.matcher(password).matches();
        }
    }

    public static String formatPhoneWithDefaultCountryPrefix(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (phone.startsWith("00")) {
            sb.append("+");
            i = 2;
        } else if (phone.startsWith("+")) {
        } else {
            sb.append("+").append(DEFAULT_COUNTRY_CODE);
        }
        for (; i < phone.length(); i++) {
            char c = phone.charAt(i);
            if (isDialable(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String formatPhoneNoCountryPrefix(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return phone;
        }
        phone = formatPhone(phone);
        boolean dealCountryPrefix = false;
        if (phone.startsWith("+")) {
            phone = phone.substring(1);
            dealCountryPrefix = true;
        } else if (phone.startsWith("00")) {
            phone = phone.substring(2);
            dealCountryPrefix = true;
        }
        // 去除国家码
        initCountryPrefix();
        // 国家码+号码>=9
        if (dealCountryPrefix && phone.length() >= 9) {
            for (int i = 4; i >= 1; i--) {
                String prefix = phone.substring(0, i);
                if (sSetCountryPrefix.contains(prefix)) {
                    phone = phone.substring(i);
                    break;
                }
            }
        }
        return phone;
    }

    public static String formatPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < phone.length(); i++) {
            char c = phone.charAt(i);
            if (isDialable(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    private static boolean isDialable(char c) {
        return c == '+' || (c >= '0' && c <= '9');
    }

    private static void initCountryPrefix() {
        if (sSetCountryPrefix != null) {
            return;
        }
        sSetCountryPrefix = new HashSet<>();
        sSetCountryPrefix.add("376");
        sSetCountryPrefix.add("971");
        sSetCountryPrefix.add("93");
        sSetCountryPrefix.add("1268");
        sSetCountryPrefix.add("1264");
        sSetCountryPrefix.add("355");
        sSetCountryPrefix.add("374");
        sSetCountryPrefix.add("599");
        sSetCountryPrefix.add("244");
        sSetCountryPrefix.add("54");
        sSetCountryPrefix.add("1684");
        sSetCountryPrefix.add("43");
        sSetCountryPrefix.add("297");
        sSetCountryPrefix.add("994");
        sSetCountryPrefix.add("387");
        sSetCountryPrefix.add("1246");
        sSetCountryPrefix.add("880");
        sSetCountryPrefix.add("32");
        sSetCountryPrefix.add("226");
        sSetCountryPrefix.add("359");
        sSetCountryPrefix.add("973");
        sSetCountryPrefix.add("257");
        sSetCountryPrefix.add("229");
        sSetCountryPrefix.add("1441");
        sSetCountryPrefix.add("673");
        sSetCountryPrefix.add("591");
        sSetCountryPrefix.add("55");
        sSetCountryPrefix.add("1242");
        sSetCountryPrefix.add("975");
        sSetCountryPrefix.add("267");
        sSetCountryPrefix.add("375");
        sSetCountryPrefix.add("501");
        sSetCountryPrefix.add("243");
        sSetCountryPrefix.add("236");
        sSetCountryPrefix.add("242");
        sSetCountryPrefix.add("41");
        sSetCountryPrefix.add("225");
        sSetCountryPrefix.add("682");
        sSetCountryPrefix.add("56");
        sSetCountryPrefix.add("237");
        sSetCountryPrefix.add("86");
        sSetCountryPrefix.add("57");
        sSetCountryPrefix.add("506");
        sSetCountryPrefix.add("53");
        sSetCountryPrefix.add("238");
        sSetCountryPrefix.add("61");
        sSetCountryPrefix.add("357");
        sSetCountryPrefix.add("420");
        sSetCountryPrefix.add("49");
        sSetCountryPrefix.add("253");
        sSetCountryPrefix.add("45");
        sSetCountryPrefix.add("1767");
        sSetCountryPrefix.add("1809");
        sSetCountryPrefix.add("213");
        sSetCountryPrefix.add("593");
        sSetCountryPrefix.add("372");
        sSetCountryPrefix.add("20");
        sSetCountryPrefix.add("291");
        sSetCountryPrefix.add("34");
        sSetCountryPrefix.add("251");
        sSetCountryPrefix.add("358");
        sSetCountryPrefix.add("679");
        sSetCountryPrefix.add("500");
        sSetCountryPrefix.add("691");
        sSetCountryPrefix.add("298");
        sSetCountryPrefix.add("33");
        sSetCountryPrefix.add("241");
        sSetCountryPrefix.add("44");
        sSetCountryPrefix.add("1473");
        sSetCountryPrefix.add("995");
        sSetCountryPrefix.add("594");
        sSetCountryPrefix.add("1481");
        sSetCountryPrefix.add("233");
        sSetCountryPrefix.add("350");
        sSetCountryPrefix.add("299");
        sSetCountryPrefix.add("220");
        sSetCountryPrefix.add("224");
        sSetCountryPrefix.add("590");
        sSetCountryPrefix.add("240");
        sSetCountryPrefix.add("30");
        sSetCountryPrefix.add("502");
        sSetCountryPrefix.add("1671");
        sSetCountryPrefix.add("245");
        sSetCountryPrefix.add("592");
        sSetCountryPrefix.add("852");
        sSetCountryPrefix.add("672");
        sSetCountryPrefix.add("504");
        sSetCountryPrefix.add("385");
        sSetCountryPrefix.add("509");
        sSetCountryPrefix.add("36");
        sSetCountryPrefix.add("62");
        sSetCountryPrefix.add("353");
        sSetCountryPrefix.add("972");
        sSetCountryPrefix.add("91");
        sSetCountryPrefix.add("246");
        sSetCountryPrefix.add("964");
        sSetCountryPrefix.add("98");
        sSetCountryPrefix.add("354");
        sSetCountryPrefix.add("39");
        sSetCountryPrefix.add("1876");
        sSetCountryPrefix.add("962");
        sSetCountryPrefix.add("81");
        sSetCountryPrefix.add("254");
        sSetCountryPrefix.add("996");
        sSetCountryPrefix.add("855");
        sSetCountryPrefix.add("686");
        sSetCountryPrefix.add("269");
        sSetCountryPrefix.add("1869");
        sSetCountryPrefix.add("850");
        sSetCountryPrefix.add("82");
        sSetCountryPrefix.add("965");
        sSetCountryPrefix.add("1345");
        sSetCountryPrefix.add("856");
        sSetCountryPrefix.add("961");
        sSetCountryPrefix.add("1758");
        sSetCountryPrefix.add("423");
        sSetCountryPrefix.add("94");
        sSetCountryPrefix.add("231");
        sSetCountryPrefix.add("266");
        sSetCountryPrefix.add("370");
        sSetCountryPrefix.add("352");
        sSetCountryPrefix.add("371");
        sSetCountryPrefix.add("218");
        sSetCountryPrefix.add("212");
        sSetCountryPrefix.add("377");
        sSetCountryPrefix.add("373");
        sSetCountryPrefix.add("382");
        sSetCountryPrefix.add("1599");
        sSetCountryPrefix.add("261");
        sSetCountryPrefix.add("692");
        sSetCountryPrefix.add("389");
        sSetCountryPrefix.add("223");
        sSetCountryPrefix.add("95");
        sSetCountryPrefix.add("976");
        sSetCountryPrefix.add("853");
        sSetCountryPrefix.add("1670");
        sSetCountryPrefix.add("596");
        sSetCountryPrefix.add("222");
        sSetCountryPrefix.add("1664");
        sSetCountryPrefix.add("356");
        sSetCountryPrefix.add("230");
        sSetCountryPrefix.add("960");
        sSetCountryPrefix.add("265");
        sSetCountryPrefix.add("52");
        sSetCountryPrefix.add("60");
        sSetCountryPrefix.add("258");
        sSetCountryPrefix.add("264");
        sSetCountryPrefix.add("687");
        sSetCountryPrefix.add("227");
        sSetCountryPrefix.add("6723");
        sSetCountryPrefix.add("234");
        sSetCountryPrefix.add("505");
        sSetCountryPrefix.add("31");
        sSetCountryPrefix.add("977");
        sSetCountryPrefix.add("674");
        sSetCountryPrefix.add("683");
        sSetCountryPrefix.add("64");
        sSetCountryPrefix.add("968");
        sSetCountryPrefix.add("507");
        sSetCountryPrefix.add("51");
        sSetCountryPrefix.add("689");
        sSetCountryPrefix.add("675");
        sSetCountryPrefix.add("63");
        sSetCountryPrefix.add("92");
        sSetCountryPrefix.add("48");
        sSetCountryPrefix.add("508");
        sSetCountryPrefix.add("872");
        sSetCountryPrefix.add("1787");
        sSetCountryPrefix.add("970");
        sSetCountryPrefix.add("351");
        sSetCountryPrefix.add("680");
        sSetCountryPrefix.add("595");
        sSetCountryPrefix.add("974");
        sSetCountryPrefix.add("262");
        sSetCountryPrefix.add("40");
        sSetCountryPrefix.add("381");
        sSetCountryPrefix.add("7");
        sSetCountryPrefix.add("250");
        sSetCountryPrefix.add("966");
        sSetCountryPrefix.add("677");
        sSetCountryPrefix.add("248");
        sSetCountryPrefix.add("249");
        sSetCountryPrefix.add("46");
        sSetCountryPrefix.add("65");
        sSetCountryPrefix.add("290");
        sSetCountryPrefix.add("386");
        sSetCountryPrefix.add("47");
        sSetCountryPrefix.add("421");
        sSetCountryPrefix.add("232");
        sSetCountryPrefix.add("378");
        sSetCountryPrefix.add("221");
        sSetCountryPrefix.add("252");
        sSetCountryPrefix.add("597");
        sSetCountryPrefix.add("239");
        sSetCountryPrefix.add("503");
        sSetCountryPrefix.add("963");
        sSetCountryPrefix.add("268");
        sSetCountryPrefix.add("1649");
        sSetCountryPrefix.add("235");
        sSetCountryPrefix.add("228");
        sSetCountryPrefix.add("66");
        sSetCountryPrefix.add("992");
        sSetCountryPrefix.add("690");
        sSetCountryPrefix.add("670");
        sSetCountryPrefix.add("993");
        sSetCountryPrefix.add("216");
        sSetCountryPrefix.add("676");
        sSetCountryPrefix.add("90");
        sSetCountryPrefix.add("1868");
        sSetCountryPrefix.add("688");
        sSetCountryPrefix.add("886");
        sSetCountryPrefix.add("255");
        sSetCountryPrefix.add("380");
        sSetCountryPrefix.add("256");
        sSetCountryPrefix.add("699");
        sSetCountryPrefix.add("1");
        sSetCountryPrefix.add("598");
        sSetCountryPrefix.add("998");
        sSetCountryPrefix.add("379");
        sSetCountryPrefix.add("1784");
        sSetCountryPrefix.add("58");
        sSetCountryPrefix.add("1284");
        sSetCountryPrefix.add("1340");
        sSetCountryPrefix.add("84");
        sSetCountryPrefix.add("678");
        sSetCountryPrefix.add("681");
        sSetCountryPrefix.add("685");
        sSetCountryPrefix.add("967");
        sSetCountryPrefix.add("27");
        sSetCountryPrefix.add("260");
        sSetCountryPrefix.add("263");
    }
}
