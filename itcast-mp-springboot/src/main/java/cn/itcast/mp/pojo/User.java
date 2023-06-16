package cn.itcast.mp.pojo;

import cn.itcast.mp.enums.SexEnum;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@TableName("tb_user")
public class User extends Model<User> {
    //    @TableId(type= IdType.AUTO)
    private Long id;
    private String userName;
    //插入数据时自动填充
    @TableField(select = false, fill = FieldFill.INSERT)     //查询时不显示该字段的值
    private String password;
    private String name;
    private Integer age;

    @TableField(value = "email")
    private String mail;

    @TableField(exist = false)
    private String address;

    @Version
    private Integer version;

    @TableLogic
    private  Integer deleted;

    private SexEnum sex;

}