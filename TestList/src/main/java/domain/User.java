package domain;



import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("todolist")
public class User {
//    @TableId(type = IdType.AUTO)//@TableId(type=IdType.AUTO)表示主键是自增的
    private Integer id;

    private String user_name;

    private String password;
}
