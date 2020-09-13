package sejinHan25.user;

import lombok.Data;


@Data
public class UserDTO {
   private int seq;
   private String id;
   private String pw;
   private String nickname;
   private String email;
   private int birthyear;
   private int google;
   private int kakao;
   private int github;
   
}