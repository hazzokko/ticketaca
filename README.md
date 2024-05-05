# 🎫 [Ticketaca] 전시회 티켓 예약 플랫폼
* 미니 팀 프로젝트 4 : 기존 미니프로젝트3 스프링프레임워크 이용하여 고도화하기
* zip 파일을 import 하면 작동
----

## 🔧 사용 기술 및 개발환경
**Tech Stack** : `Java8`, `Spring Framework`, `Oracle`, `Maven`, `MyBatis`, `JSP`, `JSON`, `Tiles`, `Git`, `JavaScript`, `Thumbnailator`, `STS3.8.4`

**Architecture** : `Spring MVC`
<br>
<br>

## 🧑 Team (총 4명)
- 로그인 및 회원가입 1명
- **전시회 서비스 총괄 1명**
- 예약 및 즐겨찾기 1명
- Q&A 기능 1명
<br>

## ⭐️ 주요 기능
- **관리자** : 회원 검색, 회원 전체 주문 목록, 회원 Q&A 목록 및 답변, 등록 전시 관리 가능
- **회원** : 전시회 정보 확인 및 예약, 찜(즐겨찾기), 결제 내역 확인, 문의하기 가능
<br>

## ✋ 내가 담당한 부분
- 서비스 기획 및 ERD 설계
- 전시회 서비스 관련 REST API 및 프론트 총괄 구현
    - 전체/진행 중/마감 임박/전시 예정/전시 종료 목록 출력(쿼리 작성, 페이징 등)
    - 전시회 등록, 세부 정보 출력, 수정, 삭제
- 다중 파일 업로드 기능 개발
<br>

## 🏗 ERD

<img src="https://github.com/hazzokko/ticketaca/assets/134523731/27a564c2-7ddf-4341-92c1-74a0fd2e66f5" />
<img src="https://github.com/hazzokko/ticketaca/assets/134523731/af25b401-75be-450c-859e-d015203dd809" />
<br>

## ✍ 변경사항
### 전체파트 : 기존 프로젝트 → 스프링 프레임워크를 이용하여 고도화

<table>
  <th>기존</th>
  <th>변경</th>
  <tr>
    <td>user / admin 으로 분리된 Controller, Service, DAO 기능</td>
    <td>기능 별로 각각 Controller, Service, DAO 활용</td>
  </tr>
  <tr>
    <td>System.out.print() 메소드를 이용 정보를 콘솔에 출력 → 작성하지 않은 경우가 있어 확인이 안되는 정보가 있었음</td>
    <td>스프링 AOP 기능을 이용하여 Around로 컨트롤러 메서드 실행 전, 후, 예외처리 경우 로그가 출력되도록 작업</td>
  </tr>
  <tr>
    <td>ajax를 이용할 때도 1개의 Controller로 처리하고, text 데이터로 주고 받았음</td>
    <td>REST API를 위해 기능에 맞는 REST controller를 추가하거나 @ResponseBody를 추가하여 활용 / JSON 데이터로 주고받음</td>
  </tr>
  <tr>
    <td>개발자가 담당한 기능의 VO마다 변수명 제 각각</td>
    <td>DB 필드명, 변수명 등을 SnakeCase에서 CamelCase로 통일</td>
  </tr>
  <tr>
    <td>썸네일의 경우, 원본 사이즈 이미지를 불러와서 태그를 통해 강제로 줄여서 보여줌</td>
    <td>Thumbnailator 라이브러리를 사용해 이미지 리사이징 후 제공</td>
  </tr>
  <tr>
    <td>DAO에서 쿼리문을 작성하여 처리</td>
    <td>MyBatis를 이용하여 xml로 쿼리문 을 따로 처리</td>
  </tr>
  <tr>
    <td>필터 클래스를 이용하여 Encoding 설정</td>
    <td>web.xml에 필터를 설정</td>
  </tr>
</table>
<br>

### Order 파트 (예매관련 기능)
<table>
  <th>구분</th>
  <th>기존</th>
  <th>변경</th>
  <tr>
    <td>User</td>
    <td>상품 상세조회 → 예매하기 클릭 시 예매수량/ 가격 입력 후 바로 결제</td>
    <td>결제 수단 추가로 상품 상세조회 > 수량 입력 > 결제 정보 확인 후 결제 수단 입력 하고 결제 확정</td>
  </tr>
  <tr>
    <td>User</td>
    <td>결제기능 card로 고정된 형태</td>
    <td>결제 입력 페이지 추가하고, kakaoPay QR결제(처럼 보이게) 추가</td>
  </tr>
  <tr>
    <td>Admin</td>
    <td>검색기능 미 구현</td>
    <td>검색기능 구현 → DAO 에 검색 분류 분기를 넣어서 mapper의 쿼리를 실행하여 검색값 반환</td>
  </tr>
</table>
<br>

### QNA 파트 (문의관련 기능)
<table>
  <th>구분</th>
  <th>기존</th>
  <th>변경</th>
  <tr>
    <td>공통</td>
    <td>프론트 페이지 디자인 미 적용</td>
    <td>tiles 를 이용하여 디자인 적용 완료</td>
  </tr>
  <tr>
    <td>User</td>
    <td>문의 글 수정</td>
    <td>수정 여부 알림과 함께, 유저 세션정보 담아 목록으로 이동</td>
  </tr>
  <tr>
    <td>Admin</td>
    <td>답변 등록</td>
    <td>답변 등록 후, 상세보기 화면에서 pno 값 중복 조회 오류가 걸리지 않도록 답변 여부 확인하여 답변글 등록 버튼 활성화 및 등록 여부 알림과 함께 목록으로 이동 구현</td>
  </tr>
</table>
<br>

### Exh 파트 (전시관련 기능)
<table>
  <th>기존</th>
  <th>변경</th>
  <tr>
    <td>이미지 파일 업로드</td>
    <td>다중 파일 업로드 기능으로 변경, 이미지 파일 업로드 시 jsp에서 ajax를 사용하여 파일명을 잘라서 컨트롤러에 전송하여 컨트롤러의 메서드 추가 구현없이 파일 처리를 실현</td>
  </tr>
</table>
<br>

----
## 🛠 트러블 슈팅
**문제 1** : [do Handle() ERR : null (new를 통한 객체 미생성)](https://hazzokko.tistory.com/43)

**문제 2** : [Oracle Date 타입 Map에 추가 안되는 오류](https://hazzokko.tistory.com/45)

**문제 3** : [File 웹 업로드와 데이터베이스 저장의 차이](https://hazzokko.tistory.com/46)
<br>
<br>

## 📌 이미지

<img width=70% src="https://github.com/hazzokko/ticketaca/assets/134523731/37fe25b1-d574-4f17-922c-1057a1dbbf18" />
