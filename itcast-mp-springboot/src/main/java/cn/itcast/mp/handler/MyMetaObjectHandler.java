package cn.itcast.mp.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    //在插入数据时填充
    @Override
    public void insertFill(MetaObject metaObject) {
        //获取到password的值，再朝廷判断，如果为空，就填充，不为空不做处理
        Object password = getFieldValByName("password", metaObject);
        if(null == password){
            setFieldValByName("password","888888",metaObject);
        }
    }

    //在更新数据时填充
    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
