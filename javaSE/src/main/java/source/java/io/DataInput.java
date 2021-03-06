/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
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

import java.io.EOFException;
import java.io.IOException;
import java.io.UTFDataFormatException;

/**
 * The {@code DataInput} interface provides
 * for reading bytes from a binary stream and
 * reconstructing from them data in any of
 * the Java primitive types. There is also
 * a
 * facility for reconstructing a {@code String}
 * from data in
 * <a href="#modified-utf-8">modified UTF-8</a>
 * format.
 * <p>
 * It is generally true of all the reading
 * routines in this interface that if end of
 * file is reached before the desired number
 * of bytes has been read, an {@code EOFException}
 * (which is a kind of {@code IOException})
 * is thrown. If any byte cannot be read for
 * any reason other than end of file, an {@code IOException}
 * other than {@code EOFException} is
 * thrown. In particular, an {@code IOException}
 * may be thrown if the input stream has been
 * closed.
 *
 * <h3><a name="modified-utf-8">Modified UTF-8</a></h3>
 * <p>
 * Implementations of the DataInput and DataOutput interfaces represent
 * Unicode strings in a format that is a slight modification of UTF-8.
 * (For information regarding the standard UTF-8 format, see section
 * <i>3.9 Unicode Encoding Forms</i> of <i>The Unicode Standard, Version
 * 4.0</i>).
 * Note that in the following table, the most significant bit appears in the
 * far left-hand column.
 *
 * <blockquote>
 *   <table border="1" cellspacing="0" cellpadding="8"
 *          summary="Bit values and bytes">
 *     <tr>
 *       <th colspan="9"><span style="font-weight:normal">
 *         All characters in the range {@code '\u005Cu0001'} to
 *         {@code '\u005Cu007F'} are represented by a single byte:</span></th>
 *     </tr>
 *     <tr>
 *       <td></td>
 *       <th colspan="8" id="bit_a">Bit Values</th>
 *     </tr>
 *     <tr>
 *       <th id="byte1_a">Byte 1</th>
 *       <td><center>0</center>
 *       <td colspan="7"><center>bits 6-0</center>
 *     </tr>
 *     <tr>
 *       <th colspan="9"><span style="font-weight:normal">
 *         The null character {@code '\u005Cu0000'} and characters
 *         in the range {@code '\u005Cu0080'} to {@code '\u005Cu07FF'} are
 *         represented by a pair of bytes:</span></th>
 *     </tr>
 *     <tr>
 *       <td></td>
 *       <th colspan="8" id="bit_b">Bit Values</th>
 *     </tr>
 *     <tr>
 *       <th id="byte1_b">Byte 1</th>
 *       <td><center>1</center>
 *       <td><center>1</center>
 *       <td><center>0</center>
 *       <td colspan="5"><center>bits 10-6</center>
 *     </tr>
 *     <tr>
 *       <th id="byte2_a">Byte 2</th>
 *       <td><center>1</center>
 *       <td><center>0</center>
 *       <td colspan="6"><center>bits 5-0</center>
 *     </tr>
 *     <tr>
 *       <th colspan="9"><span style="font-weight:normal">
 *         {@code char} values in the range {@code '\u005Cu0800'}
 *         to {@code '\u005CuFFFF'} are represented by three bytes:</span></th>
 *     </tr>
 *     <tr>
 *       <td></td>
 *       <th colspan="8"id="bit_c">Bit Values</th>
 *     </tr>
 *     <tr>
 *       <th id="byte1_c">Byte 1</th>
 *       <td><center>1</center>
 *       <td><center>1</center>
 *       <td><center>1</center>
 *       <td><center>0</center>
 *       <td colspan="4"><center>bits 15-12</center>
 *     </tr>
 *     <tr>
 *       <th id="byte2_b">Byte 2</th>
 *       <td><center>1</center>
 *       <td><center>0</center>
 *       <td colspan="6"><center>bits 11-6</center>
 *     </tr>
 *     <tr>
 *       <th id="byte3">Byte 3</th>
 *       <td><center>1</center>
 *       <td><center>0</center>
 *       <td colspan="6"><center>bits 5-0</center>
 *     </tr>
 *   </table>
 * </blockquote>
 * <p>
 * The differences between this format and the
 * standard UTF-8 format are the following:
 * <ul>
 * <li>The null byte {@code '\u005Cu0000'} is encoded in 2-byte format
 *     rather than 1-byte, so that the encoded strings never have
 *     embedded nulls.
 * <li>Only the 1-byte, 2-byte, and 3-byte formats are used.
 * <li><a href="../lang/Character.html#unicode">Supplementary characters</a>
 *     are represented in the form of surrogate pairs.
 * </ul>
 * @author  Frank Yellin
 * @see     java.io.DataInputStream
 * @see     java.io.DataOutput
 * @since   JDK1.0
 *
 * DataInput接口提供了从二进制流中读取字节并从任何Java基本类型中重构这些数据的功能。
 * 还有一种工具可以从修改后的UTF-8格式的数据中重建字符串。
 * 这个接口中的所有读取例程通常都是这样的:如果在读取所需的字节数之前就到达了文件的末尾，就会抛出EOFException(它是IOException的一种)。
 * 如果由于文件结束以外的任何原因无法读取任何字节，则抛出EOFException以外的IOException。
 * 特别是，如果输入流已关闭，则可能引发IOException。
 *
 *
 * DataInput和DataOutput接口的实现以一种稍微修改了UTF-8的格式表示Unicode字符串。
 * (有关标准UTF-8格式的信息，请参阅Unicode标准4.0版本的3.9 Unicode编码格式)。
 * 请注意，在下表中，最重要的位出现在最左边的列中。
 *
 *
 */
public
interface DataInput {
    /**
     * Reads some bytes from an input
     * stream and stores them into the buffer
     * array {@code b}. The number of bytes
     * read is equal
     * to the length of {@code b}.
     * <p>
     * This method blocks until one of the
     * following conditions occurs:
     * <ul>
     * <li>{@code b.length}
     * bytes of input data are available, in which
     * case a normal return is made.
     *
     * <li>End of
     * file is detected, in which case an {@code EOFException}
     * is thrown.
     *
     * <li>An I/O error occurs, in
     * which case an {@code IOException} other
     * than {@code EOFException} is thrown.
     * </ul>
     * <p>
     * If {@code b} is {@code null},
     * a {@code NullPointerException} is thrown.
     * If {@code b.length} is zero, then
     * no bytes are read. Otherwise, the first
     * byte read is stored into element {@code b[0]},
     * the next one into {@code b[1]}, and
     * so on.
     * If an exception is thrown from
     * this method, then it may be that some but
     * not all bytes of {@code b} have been
     * updated with data from the input stream.
     *
     * @param     b   the buffer into which the data is read.
     * @exception  EOFException  if this stream reaches the end before reading
     *               all the bytes.
     * @exception  IOException   if an I/O error occurs.
     */
    /**
     * 从输入流中读取一些字节并存入缓冲字节数组b中,读取的字节数等于数组b的长度，
     * 此方法阻塞，直到下列情况之一发生：
     * b.length输入的字节是可用的，在这种情况下正常返回；
     * 文件结束检测，在这种情况下将会抛出EOFException异常；
     * I/O错误发生，在这种情况下将会抛出IOExcetpion而不会抛出EOFException
     */
    void readFully(byte b[]) throws IOException;

    /**
     *
     * Reads {@code len}
     * bytes from
     * an input stream.
     * <p>
     * This method
     * blocks until one of the following conditions
     * occurs:
     * <ul>
     * <li>{@code len} bytes
     * of input data are available, in which case
     * a normal return is made.
     *
     * <li>End of file
     * is detected, in which case an {@code EOFException}
     * is thrown.
     *
     * <li>An I/O error occurs, in
     * which case an {@code IOException} other
     * than {@code EOFException} is thrown.
     * </ul>
     * <p>
     * If {@code b} is {@code null},
     * a {@code NullPointerException} is thrown.
     * If {@code off} is negative, or {@code len}
     * is negative, or {@code off+len} is
     * greater than the length of the array {@code b},
     * then an {@code IndexOutOfBoundsException}
     * is thrown.
     * If {@code len} is zero,
     * then no bytes are read. Otherwise, the first
     * byte read is stored into element {@code b[off]},
     * the next one into {@code b[off+1]},
     * and so on. The number of bytes read is,
     * at most, equal to {@code len}.
     *
     * @param     b   the buffer into which the data is read.
     * @param off  an int specifying the offset into the data.
     * @param len  an int specifying the number of bytes to read.
     * @exception  EOFException  if this stream reaches the end before reading
     *               all the bytes.
     * @exception  IOException   if an I/O error occurs.
     */
    /**
     *  从输入流中读取len个字节
     *  此方法阻塞，知道下列情况之一发生：
     *  输入的len个字节数据是可用的，在这种情况下正常返回；
     *  文件结束检测，在这种情况下将会抛出EOFException异常；
     *  I/O错误发生，在这种情况下将会抛出IOExcetpion而不会抛出EOFException
     */
    void readFully(byte b[], int off, int len) throws IOException;

    /**
     * Makes an attempt to skip over
     * {@code n} bytes
     * of data from the input
     * stream, discarding the skipped bytes. However,
     * it may skip
     * over some smaller number of
     * bytes, possibly zero. This may result from
     * any of a
     * number of conditions; reaching
     * end of file before {@code n} bytes
     * have been skipped is
     * only one possibility.
     * This method never throws an {@code EOFException}.
     * The actual
     * number of bytes skipped is returned.
     *
     * @param      n   the number of bytes to be skipped.
     * @return     the number of bytes actually skipped.
     * @exception  IOException   if an I/O error occurs.
     */
    /**
     * 尝试跳过来自输入流的n个字节数据，丢弃跳过的字节，但是，他可能跳过一些较小的字节数，可能为0个，
     * 这可能是由许多条件引起的；在跳过n个字节之前到达文件的结尾只有一种可能性，这个方法不会抛出EOFException异常，
     * 将会返回实际跳过的字节数。
     */
    int skipBytes(int n) throws IOException;

    /**
     * Reads one input byte and returns
     * {@code true} if that byte is nonzero,
     * {@code false} if that byte is zero.
     * This method is suitable for reading
     * the byte written by the {@code writeBoolean}
     * method of interface {@code DataOutput}.
     *
     * @return     the {@code boolean} value read.
     * @exception  EOFException  if this stream reaches the end before reading
     *               all the bytes.
     * @exception  IOException   if an I/O error occurs.
     */
    /**
     * 读取一个输入字节，如果这个字节非0，就返回true,如果是0返回false,
     * 这种方法适用于读取字节的数据输出的接口writeboolean写的方法
     */
    boolean readBoolean() throws IOException;

    /**
     * Reads and returns one input byte.
     * The byte is treated as a signed value in
     * the range {@code -128} through {@code 127},
     * inclusive.
     * This method is suitable for
     * reading the byte written by the {@code writeByte}
     * method of interface {@code DataOutput}.
     *
     * @return     the 8-bit value read.
     * @exception  EOFException  if this stream reaches the end before reading
     *               all the bytes.
     * @exception  IOException   if an I/O error occurs.
     */
    /**
     * 读取并返回一个输入字节，在-128到127之间，字节被视为有符号值，包括，
     * 这种方法适用于读取接口DataOutput的writeByte方法写入的字节；
     */
    byte readByte() throws IOException;

    /**
     * Reads one input byte, zero-extends
     * it to type {@code int}, and returns
     * the result, which is therefore in the range
     * {@code 0}
     * through {@code 255}.
     * This method is suitable for reading
     * the byte written by the {@code writeByte}
     * method of interface {@code DataOutput}
     * if the argument to {@code writeByte}
     * was intended to be a value in the range
     * {@code 0} through {@code 255}.
     *
     * @return     the unsigned 8-bit value read.
     * @exception  EOFException  if this stream reaches the end before reading
     *               all the bytes.
     * @exception  IOException   if an I/O error occurs.
     */
    /**
     * 读取一个输入字节，0将其扩展到int,并返回结果，因此在0到255的范围内，
     * 这个方法适用于读取接口DataOutput的writeByte方法写入的字节；
     */
    int readUnsignedByte() throws IOException;

    /**
     * Reads two input bytes and returns
     * a {@code short} value. Let {@code a}
     * be the first byte read and {@code b}
     * be the second byte. The value
     * returned
     * is:
     * <pre>{@code (short)((a << 8) | (b & 0xff))
     * }</pre>
     * This method
     * is suitable for reading the bytes written
     * by the {@code writeShort} method of
     * interface {@code DataOutput}.
     *
     * @return     the 16-bit value read.
     * @exception  EOFException  if this stream reaches the end before reading
     *               all the bytes.
     * @exception  IOException   if an I/O error occurs.
     */
    /**
     * 读取两个输入字节，并返回一个short类型值，如果第一个读取字节是a,第二个读取字节是b,
     * 那么返回的值是(short)((a << 8) | (b & 0xff))；
     * 这个方法适合读取DataOutput接口的writeShort方法写入的数据；
     */
    short readShort() throws IOException;

    /**
     * Reads two input bytes and returns
     * an {@code int} value in the range {@code 0}
     * through {@code 65535}. Let {@code a}
     * be the first byte read and
     * {@code b}
     * be the second byte. The value returned is:
     * <pre>{@code (((a & 0xff) << 8) | (b & 0xff))
     * }</pre>
     * This method is suitable for reading the bytes
     * written by the {@code writeShort} method
     * of interface {@code DataOutput}  if
     * the argument to {@code writeShort}
     * was intended to be a value in the range
     * {@code 0} through {@code 65535}.
     *
     * @return     the unsigned 16-bit value read.
     * @exception  EOFException  if this stream reaches the end before reading
     *               all the bytes.
     * @exception  IOException   if an I/O error occurs.
     */
    /**
     * 读取两个输入字节，并在0到65535范围内返回int值;让第一个读取的字节是a,第二个读取的字节是b,返回的值是：
     * (((a & 0xff) << 8) | (b & 0xff))
     * 这个方法适合读取接口DataOutput的writeShort方法写入的数据，如果方法writeShort写入的数据是在0到65535之间，
     * 返回一个未签名的16位值；
     */
    int readUnsignedShort() throws IOException;

    /**
     * Reads two input bytes and returns a {@code char} value.
     * Let {@code a}
     * be the first byte read and {@code b}
     * be the second byte. The value
     * returned is:
     * <pre>{@code (char)((a << 8) | (b & 0xff))
     * }</pre>
     * This method
     * is suitable for reading bytes written by
     * the {@code writeChar} method of interface
     * {@code DataOutput}.
     *
     * @return     the {@code char} value read.
     * @exception  EOFException  if this stream reaches the end before reading
     *               all the bytes.
     * @exception  IOException   if an I/O error occurs.
     */
    /**
     * 读取两个字节并返回一个char类型值，a做为第一个读取的字节，b做为第二个读取字节，结果将会返回
     * (char)((a << 8) | (b & 0xff))
     * 该方法将会适合读取DataOutput接口的writeChar方法写入的数据，返回一个char类型数据；
     */
    char readChar() throws IOException;

    /**
     * Reads four input bytes and returns an
     * {@code int} value. Let {@code a-d}
     * be the first through fourth bytes read. The value returned is:
     * <pre>{@code
     * (((a & 0xff) << 24) | ((b & 0xff) << 16) |
     *  ((c & 0xff) <<  8) | (d & 0xff))
     * }</pre>
     * This method is suitable
     * for reading bytes written by the {@code writeInt}
     * method of interface {@code DataOutput}.
     *
     * @return     the {@code int} value read.
     * @exception  EOFException  if this stream reaches the end before reading
     *               all the bytes.
     * @exception  IOException   if an I/O error occurs.
     */
    /**
     * 读取四个输入字节并返回一个int值，让a-d做为读取的字节，将会返回
     *  (((a & 0xff) << 24) | ((b & 0xff) << 16) | ((c & 0xff) << 8) | (d & 0xff))
     * 该方法适合读取接口DataOutput的writeInt方法写入的数据；
     * 返回读取到的int值；
     */
    int readInt() throws IOException;

    /**
     * Reads eight input bytes and returns
     * a {@code long} value. Let {@code a-h}
     * be the first through eighth bytes read.
     * The value returned is:
     * <pre>{@code
     * (((long)(a & 0xff) << 56) |
     *  ((long)(b & 0xff) << 48) |
     *  ((long)(c & 0xff) << 40) |
     *  ((long)(d & 0xff) << 32) |
     *  ((long)(e & 0xff) << 24) |
     *  ((long)(f & 0xff) << 16) |
     *  ((long)(g & 0xff) <<  8) |
     *  ((long)(h & 0xff)))
     * }</pre>
     * <p>
     * This method is suitable
     * for reading bytes written by the {@code writeLong}
     * method of interface {@code DataOutput}.
     *
     * @return     the {@code long} value read.
     * @exception  EOFException  if this stream reaches the end before reading
     *               all the bytes.
     * @exception  IOException   if an I/O error occurs.
     */
    /**
     * 读取八个输入字节并返回一个long数据，让a-h做为读取到的八个字节，结果将会返回
     *  (((long)(a & 0xff) << 56) |
     *  ((long)(b & 0xff) << 48) |
     *  ((long)(c & 0xff) << 40) |
     *  ((long)(d & 0xff) << 32) |
     *  ((long)(e & 0xff) << 24) |
     *  ((long)(f & 0xff) << 16) |
     *  ((long)(g & 0xff) <<  8) |
     *  ((long)(h & 0xff)))
     * 该方法适合读取接口DataOutput的writeLong方法；
     * 返回一个long类型数据；
     */
    long readLong() throws IOException;

    /**
     * Reads four input bytes and returns
     * a {@code float} value. It does this
     * by first constructing an {@code int}
     * value in exactly the manner
     * of the {@code readInt}
     * method, then converting this {@code int}
     * value to a {@code float} in
     * exactly the manner of the method {@code Float.intBitsToFloat}.
     * This method is suitable for reading
     * bytes written by the {@code writeFloat}
     * method of interface {@code DataOutput}.
     *
     * @return     the {@code float} value read.
     * @exception  EOFException  if this stream reaches the end before reading
     *               all the bytes.
     * @exception  IOException   if an I/O error occurs.
     */
    /**
     * 读取四个输入字节并返回一个float类型数据，这是通过首先在完全的readInt方法构建一个int值的方式，
     * 然后以该方法Float.intBitsToFloat将这个int值转换为浮点数；
     * 这个方式适合读取接口DataOutput的writeFloat方法
     * 写入的数据，最后返回一个float值；
     */
    float readFloat() throws IOException;

    /**
     * Reads eight input bytes and returns
     * a {@code double} value. It does this
     * by first constructing a {@code long}
     * value in exactly the manner
     * of the {@code readLong}
     * method, then converting this {@code long}
     * value to a {@code double} in exactly
     * the manner of the method {@code Double.longBitsToDouble}.
     * This method is suitable for reading
     * bytes written by the {@code writeDouble}
     * method of interface {@code DataOutput}.
     *
     * @return     the {@code double} value read.
     * @exception  EOFException  if this stream reaches the end before reading
     *               all the bytes.
     * @exception  IOException   if an I/O error occurs.
     */
    /**
     * 读取八个输入字节并返回一个double数据类型，首先会构建一个long类型的数据，确切的说就是使用readLong方法，
     * 然后将这个long数据转换为double类型使用Double.longBitsToDouble方法；
     * 这个方法适合读取接口DataOutput的writeDouble方法写入的数据；
     * 返回一个double类型数据
     */
    double readDouble() throws IOException;

    /**
     * Reads the next line of text from the input stream.
     * It reads successive bytes, converting
     * each byte separately into a character,
     * until it encounters a line terminator or
     * end of
     * file; the characters read are then
     * returned as a {@code String}. Note
     * that because this
     * method processes bytes,
     * it does not support input of the full Unicode
     * character set.
     * <p>
     * If end of file is encountered
     * before even one byte can be read, then {@code null}
     * is returned. Otherwise, each byte that is
     * read is converted to type {@code char}
     * by zero-extension. If the character {@code '\n'}
     * is encountered, it is discarded and reading
     * ceases. If the character {@code '\r'}
     * is encountered, it is discarded and, if
     * the following byte converts &#32;to the
     * character {@code '\n'}, then that is
     * discarded also; reading then ceases. If
     * end of file is encountered before either
     * of the characters {@code '\n'} and
     * {@code '\r'} is encountered, reading
     * ceases. Once reading has ceased, a {@code String}
     * is returned that contains all the characters
     * read and not discarded, taken in order.
     * Note that every character in this string
     * will have a value less than {@code \u005Cu0100},
     * that is, {@code (char)256}.
     *
     * @return the next line of text from the input stream,
     *         or {@code null} if the end of file is
     *         encountered before a byte can be read.
     * @exception  IOException  if an I/O error occurs.
     */
    /**
     * 从输入流读取下一行文本，它读取连续字节，将每个字节分别转换为字符，直到遇到行终止符或文件结束符为止；
     * 然后将读取到的字符作为字符串返回。注意，因为该方法处理的是字节，所以它不支持完整的unicode字符集的输入。
     * 如果在读取一个字节之前遇到文件结尾，将会返回null，否则，每个读取到的字节被转化成字符char。如果遇到字符‘\n’,
     * 这个将会被丢弃，并停止读取；如果遇到字符‘\r’,它将会被丢弃，如果读取到的字节转换为字符‘\n’,
     * 这样将会被丢弃，并继续读取；如果在遇到字符‘\n’和‘\r’之前遇到文件结尾，继续读取，一旦阅读停止，
     * 将会返回所有读取到未被丢弃的字符作为字符串返回，依次采取。请注意，此字符串中的每个字符都有一个值小于\ u0100，
     * 即（炭）256。
     */
    String readLine() throws IOException;

    /**
     * Reads in a string that has been encoded using a
     * <a href="#modified-utf-8">modified UTF-8</a>
     * format.
     * The general contract of {@code readUTF}
     * is that it reads a representation of a Unicode
     * character string encoded in modified
     * UTF-8 format; this string of characters
     * is then returned as a {@code String}.
     * <p>
     * First, two bytes are read and used to
     * construct an unsigned 16-bit integer in
     * exactly the manner of the {@code readUnsignedShort}
     * method . This integer value is called the
     * <i>UTF length</i> and specifies the number
     * of additional bytes to be read. These bytes
     * are then converted to characters by considering
     * them in groups. The length of each group
     * is computed from the value of the first
     * byte of the group. The byte following a
     * group, if any, is the first byte of the
     * next group.
     * <p>
     * If the first byte of a group
     * matches the bit pattern {@code 0xxxxxxx}
     * (where {@code x} means "may be {@code 0}
     * or {@code 1}"), then the group consists
     * of just that byte. The byte is zero-extended
     * to form a character.
     * <p>
     * If the first byte
     * of a group matches the bit pattern {@code 110xxxxx},
     * then the group consists of that byte {@code a}
     * and a second byte {@code b}. If there
     * is no byte {@code b} (because byte
     * {@code a} was the last of the bytes
     * to be read), or if byte {@code b} does
     * not match the bit pattern {@code 10xxxxxx},
     * then a {@code UTFDataFormatException}
     * is thrown. Otherwise, the group is converted
     * to the character:
     * <pre>{@code (char)(((a & 0x1F) << 6) | (b & 0x3F))
     * }</pre>
     * If the first byte of a group
     * matches the bit pattern {@code 1110xxxx},
     * then the group consists of that byte {@code a}
     * and two more bytes {@code b} and {@code c}.
     * If there is no byte {@code c} (because
     * byte {@code a} was one of the last
     * two of the bytes to be read), or either
     * byte {@code b} or byte {@code c}
     * does not match the bit pattern {@code 10xxxxxx},
     * then a {@code UTFDataFormatException}
     * is thrown. Otherwise, the group is converted
     * to the character:
     * <pre>{@code
     * (char)(((a & 0x0F) << 12) | ((b & 0x3F) << 6) | (c & 0x3F))
     * }</pre>
     * If the first byte of a group matches the
     * pattern {@code 1111xxxx} or the pattern
     * {@code 10xxxxxx}, then a {@code UTFDataFormatException}
     * is thrown.
     * <p>
     * If end of file is encountered
     * at any time during this entire process,
     * then an {@code EOFException} is thrown.
     * <p>
     * After every group has been converted to
     * a character by this process, the characters
     * are gathered, in the same order in which
     * their corresponding groups were read from
     * the input stream, to form a {@code String},
     * which is returned.
     * <p>
     * The {@code writeUTF}
     * method of interface {@code DataOutput}
     * may be used to write data that is suitable
     * for reading by this method.
     * @return     a Unicode string.
     * @exception  EOFException            if this stream reaches the end
     *               before reading all the bytes.
     * @exception  IOException             if an I/O error occurs.
     * @exception  UTFDataFormatException  if the bytes do not represent a
     *               valid modified UTF-8 encoding of a string.
     */
    /**
     * 读取一个字符串，已使用修改后的UTF-8编码格式。然后这些字符将会作为字符串返回，
     */
    String readUTF() throws IOException;
}
