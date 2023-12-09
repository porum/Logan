package com.meituan;

/**
 * 功能描述:  <p></p>
 *
 *
 * @version 1.0 2019-10-07
 * @since logan-web 1.0
 */
public class Tuple<K, V> {
    K first;

    V second;

    public static <K, V> Tuple<K, V> create(K first, V second) {
        Tuple<K, V> tuple = new Tuple<>();
        tuple.first = first;
        tuple.second = second;
        return tuple;
    }
}