package ysn.com.adapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yangsanning
 * @ClassName MultiItemTypeManager
 * @Description ItemView 类型管理器
 * @Date 2020/6/5
 */
public class MultiItemTypeManager {

    /**
     * item 类型的名称 集合
     */
    protected List<String> itemTypeNameList = new ArrayList<>();

    /**
     * ItemView 的资源管理器 集合
     */
    protected List<ItemViewManager> itemViewManagerList = new ArrayList<>();

    /**
     * 通过 itemTypeNameList 和 itemViewManagerList 两组集合对类型进行管理
     *
     * @param cls             数据源 class
     * @param itemViewManager ItemView 的资源管理器
     */
    public void register(Class<?> cls, ItemViewManager itemViewManager) {
        register(cls.getName(), itemViewManager);
    }

    private void register(String itemTypeName, ItemViewManager manager) {
        if (itemTypeNameList.contains(itemTypeName)) {
            itemViewManagerList.set(itemTypeNameList.indexOf(itemTypeName), manager);
        } else {
            itemTypeNameList.add(itemTypeName);
            itemViewManagerList.add(manager);
        }
    }

    /**
     * 通过数据获取对应的 ItemViewManager
     *
     * @param data 数据
     * @return ItemView 的资源管理器
     */
    public ItemViewManager getItemViewManager(Object data) {
        return getItemViewManager(getItemType(data));
    }

    /**
     * 根据数据获取 ItemType
     *
     * @param data 数据
     * @return -1 如果没找到；
     */
    public int getItemType(@NonNull Object data) {
        return itemTypeNameList.indexOf(data.getClass().getName());
    }

    /**
     * 通过 item 类型获取对应的 ItemViewManager
     *
     * @param itemType item 类型
     * @return ItemView 的资源管理器
     */
    public ItemViewManager getItemViewManager(int itemType) {
        if (itemType < 0 || itemType > itemViewManagerList.size() - 1) {
            return null;
        }
        return itemViewManagerList.get(itemType);
    }

    public List<ItemViewManager> getItemViewManagerList() {
        return itemViewManagerList;
    }

    public List<String> getItemTypeNameList() {
        return itemTypeNameList;
    }
}