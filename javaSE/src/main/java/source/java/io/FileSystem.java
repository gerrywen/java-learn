/*
 * Copyright (c) 1998, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package source.java.io;

import java.io.IOException;
import java.lang.annotation.Native;

/**
 * Package-private abstract class for the local filesystem abstraction.
 * 包专用的抽象类，用于本地文件系统抽象。
 */

abstract class FileSystem {

    /* -- Normalization and construction -- */
    /* -- 规范化和构造 -- */

    /**
     * Return the local filesystem's name-separator character.
     * 返回本地文件系统的名称分隔符。
     */
    public abstract char getSeparator();

    /**
     * Return the local filesystem's path-separator character.
     * 返回本地文件系统的路径分隔符。
     */
    public abstract char getPathSeparator();

    /**
     * Convert the given pathname string to normal form.  If the string is
     * already in normal form then it is simply returned.
     *
     * 将给定的路径名字符串转换为普通形式。如果字符串已经处于正常形式，那么它将被简单地返回。
     */
    public abstract String normalize(String path);

    /**
     * Compute the length of this pathname string's prefix.  The pathname
     * string must be in normal form.
     *
     * 计算这个路径名 字符串前缀的长度。路径名 字符串必须是标准格式的。
     */
    public abstract int prefixLength(String path);

    /**
     * Resolve the child pathname string against the parent.
     * Both strings must be in normal form, and the result
     * will be in normal form.
     *
     * 根据父路径名解析子路径名字符串。两个字符串都必须是标准形式的，结果也必须是标准形式的。
     */
    public abstract String resolve(String parent, String child);

    /**
     * Return the parent pathname string to be used when the parent-directory
     * argument in one of the two-argument File constructors is the empty
     * pathname.
     *
     * 返回父路径名字符串，当两个参数的文件构造函数中的父目录参数为空路径名时使用该字符串。
     */
    public abstract String getDefaultParent();

    /**
     * Post-process the given URI path string if necessary.  This is used on
     * win32, e.g., to transform "/c:/foo" into "c:/foo".  The path string
     * still has slash separators; code in the File class will translate them
     * after this method returns.
     *
     * 如果需要，对给定的URI路径字符串进行后处理。这是在win32上使用的，
     * 例如，将“/c:/foo”转换成“c:/foo”。路径字符串仍然有斜杠分隔符;
     * File类中的代码将在此方法返回后转换它们。
     */
    public abstract String fromURIPath(String path);


    /* -- Path operations -- */
    /* -- 路径操作 -- */

    /**
     * Tell whether or not the given abstract pathname is absolute.
     * 判断给定的抽象路径名是否为绝对路径。
     */
    public abstract boolean isAbsolute(File f);

    /**
     * Resolve the given abstract pathname into absolute form.  Invoked by the
     * getAbsolutePath and getCanonicalPath methods in the File class.
     *
     * 将给定的抽象路径名解析为绝对形式。由文件类中的getAbsolutePath和getCanonicalPath方法调用。
     */
    public abstract String resolve(File f);

    public abstract String canonicalize(String path) throws IOException;


    /* -- Attribute accessors -- */
    /* -- 属性访问器 -- */

    /* Constants for simple boolean attributes */
    /* 用于简单布尔属性的常量 */
    @Native public static final int BA_EXISTS    = 0x01;
    @Native public static final int BA_REGULAR   = 0x02;
    @Native public static final int BA_DIRECTORY = 0x04;
    @Native public static final int BA_HIDDEN    = 0x08;

    /**
     * Return the simple boolean attributes for the file or directory denoted
     * by the given abstract pathname, or zero if it does not exist or some
     * other I/O error occurs.
     *
     * 为指定的抽象路径名表示的文件或目录返回简单的布尔属性，如果不存在或出现其他I/O错误，则返回0。
     */
    public abstract int getBooleanAttributes(File f);

    @Native public static final int ACCESS_READ    = 0x04;
    @Native public static final int ACCESS_WRITE   = 0x02;
    @Native public static final int ACCESS_EXECUTE = 0x01;

    /**
     * Check whether the file or directory denoted by the given abstract
     * pathname may be accessed by this process.  The second argument specifies
     * which access, ACCESS_READ, ACCESS_WRITE or ACCESS_EXECUTE, to check.
     * Return false if access is denied or an I/O error occurs
     *
     * 检查由给定摘要表示的文件或目录
     * 这个进程可以访问路径名。第二个参数指定
     * ACCESS_READ、ACCESS_WRITE或ACCESS_EXECUTE访问哪一个。
     * 如果访问被拒绝或出现I/O错误，则返回false
     */
    public abstract boolean checkAccess(File f, int access);
    /**
     * Set on or off the access permission (to owner only or to all) to the file
     * or directory denoted by the given abstract pathname, based on the parameters
     * enable, access and oweronly.
     *
     * 打开或关闭文件的访问权限(仅对所有者或对所有人)
     * 或基于参数用给定的抽象路径名表示的目录
     * 启用，访问和oweronly。
     */
    public abstract boolean setPermission(File f, int access, boolean enable, boolean owneronly);

    /**
     * Return the time at which the file or directory denoted by the given
     * abstract pathname was last modified, or zero if it does not exist or
     * some other I/O error occurs.
     *
     * 返回给定文件或目录表示的时间
     * 最后修改的是抽象路径名，如果不存在，则为0
     * 出现了其他一些I/O错误。
     */
    public abstract long getLastModifiedTime(File f);

    /**
     * Return the length in bytes of the file denoted by the given abstract
     * pathname, or zero if it does not exist, is a directory, or some other
     * I/O error occurs.
     *
     * 返回指定的抽象路径名所表示的文件长度(以字节为单位)，
     * 如果不存在，则返回0，因为是一个目录，或者发生了其他I/O错误。
     */
    public abstract long getLength(File f);


    /* -- File operations -- */
    /* -- 文件操作 -- */

    /**
     * Create a new empty file with the given pathname.  Return
     * <code>true</code> if the file was created and <code>false</code> if a
     * file or directory with the given pathname already exists.  Throw an
     * IOException if an I/O error occurs.
     *
     * 用给定的路径名创建一个新的空文件。如果文件已经创建，
     * 则返回<code>true</code>;
     * 如果已经存在具有给定路径名的文件或目录，则返回<code>false</code>。
     * 如果发生I/O错误，则抛出IOException。
     */
    public abstract boolean createFileExclusively(String pathname)
        throws IOException;

    /**
     * Delete the file or directory denoted by the given abstract pathname,
     * returning <code>true</code> if and only if the operation succeeds.
     *
     * 删除指定的抽象路径名所表示的文件或目录，当且仅当操作成功时才返回<code>true</code>。
     */
    public abstract boolean delete(File f);

    /**
     * List the elements of the directory denoted by the given abstract
     * pathname.  Return an array of strings naming the elements of the
     * directory if successful; otherwise, return <code>null</code>.
     *
     * 列出由给定的抽象路径名表示的目录的元素。如果成功，返回命名目录元素的字符串数组;
     * 否则,返回<code>null</code>。
     */
    public abstract String[] list(File f);

    /**
     * Create a new directory denoted by the given abstract pathname,
     * returning <code>true</code> if and only if the operation succeeds.
     *
     * 创建一个用给定的抽象路径名表示的新目录，当且仅当操作成功时返回<code>true</code>。
     */
    public abstract boolean createDirectory(File f);

    /**
     * Rename the file or directory denoted by the first abstract pathname to
     * the second abstract pathname, returning <code>true</code> if and only if
     * the operation succeeds.
     *
     * 将第一个抽象路径名表示的文件或目录重命名为第二个抽象路径名，当且仅当操作成功时返回<code>true</code>。
     */
    public abstract boolean rename(File f1, File f2);

    /**
     * Set the last-modified time of the file or directory denoted by the
     * given abstract pathname, returning <code>true</code> if and only if the
     * operation succeeds.
     *
     * 设置指定抽象路径名表示的文件或目录的最后修改时间，当且仅当操作成功时返回<code>true</code>。
     */
    public abstract boolean setLastModifiedTime(File f, long time);

    /**
     * Mark the file or directory denoted by the given abstract pathname as
     * read-only, returning <code>true</code> if and only if the operation
     * succeeds.
     *
     * 将指定的抽象路径名所表示的文件或目录标记为只读，当且仅当操作成功时才返回<code>true</code>。
     */
    public abstract boolean setReadOnly(File f);


    /* -- Filesystem interface -- */
    /* -- 文件系统接口 -- */

    /**
     * List the available filesystem roots.
     *
     * 列出可用的文件系统根。
     */
    public abstract File[] listRoots();

    /* -- Disk usage -- */
    /* -- 磁盘使用情况 -- */
    @Native public static final int SPACE_TOTAL  = 0;
    @Native public static final int SPACE_FREE   = 1;
    @Native public static final int SPACE_USABLE = 2;

    public abstract long getSpace(File f, int t);

    /* -- Basic infrastructure -- */
    /* -- 基础设施 -- */

    /**
     * Compare two abstract pathnames lexicographically.
     *
     * 按字典顺序比较两个抽象路径名。
     */
    public abstract int compare(File f1, File f2);

    /**
     * Compute the hash code of an abstract pathname.
     *
     * 计算抽象路径名的哈希码。
     */
    public abstract int hashCode(File f);

    // Flags for enabling/disabling performance optimizations for file
    // name canonicalization
    // 启用/禁用文件名规范化的性能优化的标志
    static boolean useCanonCaches      = true;
    static boolean useCanonPrefixCache = true;

    private static boolean getBooleanProperty(String prop, boolean defaultVal) {
        String val = System.getProperty(prop);
        if (val == null) return defaultVal;
        if (val.equalsIgnoreCase("true")) {
            return true;
        } else {
            return false;
        }
    }

    static {
        useCanonCaches      = getBooleanProperty("sun.io.useCanonCaches",
                                                 useCanonCaches);
        useCanonPrefixCache = getBooleanProperty("sun.io.useCanonPrefixCache",
                                                 useCanonPrefixCache);
    }
}
