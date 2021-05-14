package cn.edu.zucc.utils;


import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: 复制集合和数组
 *
 * @author chenyun
 * @since 02.03.2021
 */
public class CopyUtils {

    /**
     * 复制对象
     * 调用BeanUtils工具类中的copyProperties(source, target)方法copy对象的对应属性
     */
    public static <T, K> T convert(K source, Class<T> clazz) {
        T target = BeanUtils.instantiate(clazz);
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * 复制集合
     * Lists.transform(sourceList, data -> convert(data, clazz))
     * 意思是将sourceList中的每个对象data进行convert()操作，最后返回一个新的List
     */
    public static <T, K> List<T> copyList(List<K> sourceList, Class<T> clazz) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return null;
        }
        return sourceList.stream().map(data -> convert(data, clazz)).collect(Collectors.toList());
    }
}
