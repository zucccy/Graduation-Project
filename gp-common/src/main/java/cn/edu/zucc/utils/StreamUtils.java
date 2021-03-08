package cn.edu.zucc.utils;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Description: 集合流操作工具类，保证函数式无状态且线程安全
 *
 * @author chenyun
 * @since 04.03.2021
 */
public final class StreamUtils {

    private StreamUtils() {

    }

    /**
     * 判断集合是否为空
     *
     * @param collection 集合
     * @return true表示集合为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return null == collection || collection.isEmpty();
    }

    /**
     * 将一个List切割成多个子List
     * 泛型方法，定义格式：修饰符public <代表泛型的变量><T> 返回值类型List<List<T>> 方法名split(参数){}
     * 使用格式：调用方法时，确定泛型的类型
     *
     * @param list
     * @param maxSize 子List最大容量
     * @return 分组后的子列表
     */
    public static <T> List<List<T>> split(final List<T> list, final int maxSize) {
        //判空校验
        if (isEmpty(list)) {
            return new ArrayList<>(0);
        }
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize必须大于0");
        }

        final int size = list.size();
        //Collections.singletonList()方法返回只含一个对象的List，且不可变
        if (size <= maxSize) {
            return Collections.singletonList(list);
        }
        //将原List切割成group组,若有101条数据,子list最大容量为20,则group=6
        final int group = (size + maxSize - 1) / maxSize;
        final List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < group; i++) {
            //0-20 20-40 40-60 60-80 80-100 100-101
            int fromIndex = i * maxSize;
            int toIndex = Math.min((i + 1) * maxSize, size);
            result.add(list.subList(fromIndex, toIndex));
        }
        return result;
    }
}
