package total.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Context implements Serializable {
    //界面
    /*
        用户名(待定)
        编号(项目)
        创建时间
        内容
        判断是否完成
     */
    private String userName;
    //编号
    private Integer id;
    //创建时间
    private Date createTime;

    private String  context;

    // 0为未完成,1为完成,-1为失败(需要写个枚举类)
    private Integer situation;

    private Integer contextID;
}
