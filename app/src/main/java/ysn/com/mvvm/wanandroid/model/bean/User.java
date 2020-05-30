package ysn.com.mvvm.wanandroid.model.bean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @Author yangsanning
 * @ClassName User
 * @Description 一句话概括作用
 * @Date 2020/5/23
 */
@Entity(tableName = "User")
public class User {

    /**
     * admin : false
     * chapterTops : []
     * collectIds : []
     * email :
     * icon :
     * id : 6479
     * nickname : Jack-R
     * password :
     * publicName : Jack-R
     * token :
     * type : 0
     * username : 582959883
     */

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    public String id;

    @ColumnInfo(name = "admin")
    public boolean admin;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "icon")
    public String icon;

    @ColumnInfo(name = "nickname")
    public String nickname;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "publicName")
    public String publicName;

    @ColumnInfo(name = "token")
    public String token;

    @ColumnInfo(name = "type")
    public int type;

    @ColumnInfo(name = "username")
    public String username;
}
