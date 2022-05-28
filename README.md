# STANDER

git clone https://github.com/zmfpdl64/STANDER.git

#기본 환경 설정

JDK 11버전 설정

<h3>환경변수 설정</h3>

JAVA_HOME -> JDK 위치 연결

Path 추가

%JAVA_HOME%\bin\

#DB 설정

마리아 DB

resources/application.properties 설정

    spring.datasource.driverClassName=org.mariadb.jdbc.Driver
    spring.datasource.url=jdbc:mariadb://127.0.0.1:3307/stander //stander는 세션의 이름이다.
    spring.datasource.username=woojin //사용할 관리자의 아이디
    spring.datasource.password=1234  //사용할 관리자의 비밀번호 

HeidiSQL을 이용한 MariaDB 연결

https://jung-max.github.io/2020/06/24/Web-3_SpringBoot-MariaDb%EC%97%B0%EA%B2%B0/

설명이 잘 되어 있어 이걸 보고 진행해도 좋다.

<img src="img/database.jpg">

<h1>프로젝트명: STANDER </h1>

<h2> 무인카페 시스템</h2>
<h3>무인카페 시스템 프로젝트를 진행하게 된 계기:</h3>
<h3>대학생이라 스터디 카페를 많이 이용하는데 이용하면서 불편한 점들이 있었다.</h3>
<h3>1. 퇴실할 때 사용자가 퇴실하기 버튼을 누르지 않는다면 시간을 날려버리는 경우가 존재합니다.
퇴실처리를 위하여 다시 카페를 가는 번거로움이 있습니다. 그래서 사용자가 사용을 다하고 퇴실을 눌러 퇴실을 할 수 있게 설계하였습니다.</h3>
<h3>2. 많은 스터디 카페가 자사 앱, 웹을 가지고 있지 않아 예약하는데 있어 번거로움이 존재합니다. </h3>
<h3>3. 앱, 웹을 같은 db를 연동하여 앱, 웹을 통합한 시스템을 구현하고 싶었습니다.</h3>

<h2>무인 카페 시스템 원리</h2>

![image](https://user-images.githubusercontent.com/69797420/170821938-52fd04aa-b46b-43bb-bd98-d792a5be91e8.png)



![image](https://user-images.githubusercontent.com/69797420/170821953-00fb6f58-c560-4559-ab70-2354c46ab1e8.png)

<h1> 무인 카페 시스템 메인 기능</h1>

<h3>1. 회원가입</h3>
![join](https://user-images.githubusercontent.com/69797420/170821105-ac4451cd-bbc1-42ca-a916-48cc8a11fc67.gif)

<h3>2. 로그인</h3>
<h3>3. 예약하기</h3>

<h3>4. 결제하기</h3>
<h3>5. 마이 페이지</h3>





![except_reserve](https://user-images.githubusercontent.com/69797420/170821103-3b416e1c-265d-425d-af32-cf8c566bbe96.gif)

![login_havetime](https://user-images.githubusercontent.com/69797420/170821107-2121278b-b9ab-4bad-905a-4b22d21d9d32.gif)
![login_notime](https://user-images.githubusercontent.com/69797420/170821109-4c2132aa-c9da-4800-842c-78a116d5306e.gif)
![preserve](https://user-images.githubusercontent.com/69797420/170821111-60f42a37-bc4b-45e1-b04c-514631de6b8f.gif)



