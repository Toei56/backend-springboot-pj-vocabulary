package com.tonson.eng.mapper;

import com.tonson.eng.Controller.request.UserRegisterResponse;
import com.tonson.eng.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    mapper
//    เมื่อทำการ register เข้ามา และมีบางข้อมูลที่อ่อนไหว และไม่ต้องการส่งข้อมูลนั้นกลับไป จึงทำการ mapper เลือกเฉพาะข้อมูลที่ต้องการส่งกลับไป
    UserRegisterResponse USER_REGISTER_RESPONSE(User user);
}
