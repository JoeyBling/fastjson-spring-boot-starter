package io.gitee.zhousiwei;

/**
 * 获取FastJson版本
 *
 * @author Created by 思伟 on 2020/4/26
 */
public class FastJsonVersion {

    @Deprecated
    public static final String FASTJSON_VERSION_KEY = "FastJson-Version";

    /**
     * 获取FastJson版本号
     *
     * @return String
     */
    public static String getVersion() {
        Package pkg = FastJsonVersion.class.getPackage();
        String version = pkg != null ? pkg.getImplementationVersion() : null;
        return isEmpty(version) ? null : version;
    }

    protected static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

}
