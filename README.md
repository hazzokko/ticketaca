# 🎫 Team Ticketaca : 전시 예약 서비스
* 미니 팀 프로젝트 4 : 기존 미니프로젝트3 스프링프레임워크 이용하여 고도화하기
* zip 파일을 import 하면 작동함을 참고
* java 1.8 / STS 3.8.4
----

## ✍ 변경사항
### 전체파트 : 기존 프로젝트 → 스프링 프레임워크를 이용하여 고도화

* 기존 : user / admin 으로 분리된 Controller, Service, DAO 기능
* 현재 : 기능 별로 각각 Controller, Service, DAO 활용
<br>

* 기존 : System.out.print() 메소드를 이용 정보를 콘솔에 출력 → 작성하지 않은 경우가 있어 확인이 안되는 정보가 있었음.
* 현재 : 스프링 AOP 기능을 이용하여 Around로 컨트롤러 메서드 실행 전, 후, 예외처리 경우 로그가 출력되도록 작업
<br>

* 기존 : ajax를 이용할 때도 1개의 Controller로 처리하고, text 데이터로 주고 받았음.
* 현재 : REST API를 위해 기능에 맞는 REST controller를 추가하거나 @ResponseBody를 추가하여 활용 / JSON 데이터로 주고받음.
<br>

* 기존 : 개발자가 담당한 기능의 VO마다 변수명 제 각각
* 현재 : DB 필드명 SnakeCase를 → CamelCase로 통일
<br>

* 기존 : 썸네일의 경우, 원본 사이즈 이미지를 불러와서 태그를 통해 강제로 줄여서 보여줌
* 현재 : Thumbnailator 라이브러리를 사용해 제공
<br>

* 기존 : DAO에서 쿼리문을 작성하여 처리
* 현재 : MyBatis를 이용하여 xml로 쿼리문 을 따로 처리
<br>

* 기존 : 필터 클래스를 이용하여 Encoding 설정
* 현재 : web.xml에 필터를 설정
<br>

### Order 파트 (예매관련 기능)
* user
* 기존 : 상품 상세조회 → 예매하기 클릭 시 예매수량/ 가격 입력 후 바로 결제
* 현재 : 결제 수단 추가로 상품 상세조회 > 수량 입력 > 결제 정보 확인 후 결제 수단 입력 하고 결제 확정
<br>

* 기존 : 결제기능 card로 고정된 형태
* 현재 : 결제 입력 페이지 추가하고, kakaoPay QR결제(처럼 보이게) 추가
<br>

* Admin
* 기존 : 검색기능 미 구현
* 현재 : 검색기능 구현 > DAO 에 검색 분류 분기를 넣어서 mapper의 쿼리를 실행하여 검색값 반환
<br>

### QNA 파트 (문의관련 기능)
* 기존 : user/ admin → 프론트 페이지 디자인 미 적용
* 현재 : tiles 를 이용하여 디자인 적용 완료.
<br>

* admin : 답변 등록 후, 상세보기 화면에서 pno 값 중복 조회 오류가 걸리지 않도록
* 답변 여부 확인하여 답변글 등록 버튼 활성화
* REST API를 구현하여 ajax를 사용
<br>

* user: 문의 글 수정 → 수정 여부 알림과 함께, 유저 세션정보 담아 목록으로 이동
* admin : 답변 글 등록 후 → 등록 여부 알림과 함께 목록으로 이동 구현
<br>

### Exh 파트 (전시관련 기능)
* 이미지 파일 업로드 → 다중 파일 업로드 기능으로 변경
* 코드의 단순화로 유지보수성 증가
* 이미지 파일 업로드시
* jsp에서 ajax를 사용하여 파일명을 잘라서 컨트롤러에 전송하여
* 컨트롤러의 메서드 추가 구현없이 파일 처리를 실현
<br>

----
## 🛠 트러블 슈팅
### HTTP 500 ERROR
* 🦴 원인1: 기존 프로젝트에서는 JSP내에 Header와 Footer를 include로 연결을 해두었기 때문에 발생.
* 👍 해결1: JSP 파일에서 include를 삭제하면 해결.
* 🦴 원인2: JSP 파일에 태그 라이브러리에 코드 오류가 있을 때 발생.
* 👍 해결2: 각 JSP 파일에 태그라이브러리 코드 오류 수정하면 해결.
![image](https://github.com/Kade-Jeon/ticketaca_spring/assets/58586365/aaa9244a-ae2e-4770-8fe4-31bc6f243901)

### HTTP 400 ERROR
* 🦴 원인: form으로 전송되는 데이터가 DB 필드의 데이터타입과 맞지 않는 데이터 형식이 전송될 경우 발생.
* 👍 해결: input 태그의 value로 들어가는 부분에 사족을 지우자..... (int만 받는 곳에 공백과 한글이 들어감..)
![image](https://github.com/Kade-Jeon/ticketaca_spring/assets/58586365/f04a3ca5-a88f-44a9-889e-e5de7c3789f0)
![image](https://github.com/Kade-Jeon/ticketaca_spring/assets/58586365/7660c4aa-f9b5-4459-b7f8-8fd73ad89d04)
![image](https://github.com/Kade-Jeon/ticketaca_spring/assets/58586365/6df3e507-aa16-4155-b377-07ffe8d02f97)

### JSON ERROR
* 🦴 원인: ajax를 사용하였을 때 기능이 작동이 안됨.
* 👍 해결: pom.xml에 JSON관련 라이브러리가 주석 처리가 되어 있었음
![image](https://github.com/Kade-Jeon/ticketaca_spring/assets/58586365/0b56ca60-3bd4-434e-9a92-21a075e70496)
![image](https://github.com/Kade-Jeon/ticketaca_spring/assets/58586365/69ed89c4-93ae-4132-8e51-261dc168f184)

### VO 받을 때 Null ERROR
* 🦴 원인: 컨트롤러를 통해 VO를 받아올 때 Null 값이 뜨는 오류.
* 👍 해결: MultiPart 관련 내용이 servlet-Context.xml이 누락되어, 추가하여 해결.
