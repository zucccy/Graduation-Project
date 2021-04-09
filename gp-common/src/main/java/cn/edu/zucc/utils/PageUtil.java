package cn.edu.zucc.utils;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 09.04.2021
 */
public class PageUtil {

    public static <T> Page<T> restPage(List<T> list) {
        Page<T> pageInfo = (Page<T>) list;
        return pageInfo;
    }
}
