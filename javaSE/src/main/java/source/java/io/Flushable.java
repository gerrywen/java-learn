/*
 * Copyright (c) 2004, 2013, Oracle and/or its affiliates. All rights reserved.
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

/**
 * A <tt>Flushable</tt> is a destination of data that can be flushed.  The
 * flush method is invoked to write any buffered output to the underlying
 * stream.
 *
 * @since 1.5
 */
public interface Flushable {

    /**
     * Flushes this stream by writing any buffered output to the underlying
     * stream.
     *
     * 通过将任何缓冲输出写入底层流来清除此流。
     *
     * @throws IOException If an I/O error occurs
     */
    void flush() throws IOException;
}
