package total.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("table_user")//注意： 如果实体类没有与表名对应，需要使用@TableName指定表名。
public class User {
    @TableId(type = IdType.AUTO)//@TableId(type=IdType.AUTO)表示主键是自增的
    private Integer id;

    private String user_name;

    private String password;
}
