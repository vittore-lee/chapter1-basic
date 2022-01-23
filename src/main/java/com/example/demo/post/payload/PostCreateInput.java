package com.example.demo.post.payload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter //lombok이 해당 필드에 대한 기본 getter생성
        // 단순히 필드를 리턴하는 것, 메소드 이름은 get+필드이름,
        // 필드 타입이 boolean 이라면 is+필드이름
//@Setter  void를 리턴타입으로 갖고 있으며, 필드와 같은 타입의 파리미터 하나를 가짐
        // 단순히 필등 값을 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 파라미터가 없는 생성자를 생성
// 필드가 final로 생성되어 있는 경우 필드를 초기화 할 수 없으므로 에러가 발생
// => NoArgsConstructor(force=true)를 통해 final필드를 0,false, null등으로
//    강제 초기화 시켜서 생성자를 생성 가능
@AllArgsConstructor
// 모든 필드에 대한 생성자를 자동으로 생성
// @NonNull이 마크되어 있다면 생성자 내에서 null-check로직을 자동으로 생성
public class PostCreateInput {
    private String comment;
    private String UserName;
    private String pw;

}
