package io.gitee.zhousiwei;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 * FastJson版本工具类
 *
 * @author Created by 思伟 on 2020/4/26
 * @see #getInstance()
 */
public class FastJsonVersion {

    @Deprecated
    public static final String FASTJSON_VERSION_KEY = "FastJson-Version";

    /**
     * 外部不提供实例化方法
     */
    private FastJsonVersion() {
    }

    /**
     * 自身实例对象
     */
    private static FastJsonVersion fastJsonVersion;

    /**
     * 获取唯一实例对象
     */
    public static FastJsonVersion getInstance() {
        if (null == fastJsonVersion) {
            synchronized (FastJsonVersion.class) {
                fastJsonVersion = new FastJsonVersion();
            }
        }
        return fastJsonVersion;
    }

    /**
     * 获取FastJson版本号
     *
     * @return String
     */
    public String getVersion() {
        Package pkg = FastJsonVersion.class.getPackage();
        String version = pkg != null ? pkg.getImplementationVersion() : null;
        return isEmpty(version) ? null : version;
    }

    /**
     * 从 META-INF/MANIFEST.MF 读取版本号
     *
     * @return String
     * @throws IOException
     * @see #getManifestByKey(String)
     */
    @Deprecated
    public String getVersionByKey() throws IOException {
        return getManifestByKey(FASTJSON_VERSION_KEY);
    }

    /**
     * 从 META-INF/MANIFEST.MF 读取指定的Key
     * TODO 无法正常获取
     *
     * @param key String
     * @return String
     * @throws IOException
     */
    public String getManifestByKey(String key) throws IOException {
        URLClassLoader cl = (URLClassLoader) this.getClass().getClassLoader();
        try {
            String name = "META-INF/MANIFEST.MF";
            URL url = cl.findResource(name);
            Manifest manifest = new Manifest(url.openStream());
            Attributes mainAttributes = manifest.getMainAttributes();
            String value = mainAttributes != null ? mainAttributes.getValue(key) : null;
            return value;
        } catch (IOException e) {
            // handle
            throw e;
        }
    }

    /**
     * 判断是否为null或空白字符串
     *
     * @param cs CharSequence
     * @return boolean
     */
    protected boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

}
