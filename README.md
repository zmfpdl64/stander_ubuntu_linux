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

member의 database 형식으로 이것들을 똑같이 설정해주고 

id요소는 오른쪽 클릭을 통해 index추가하여 primary속성을 추가해준다.

<img width="80%" src="img/testing_gif/로그인 시간 없음.gif">
![로그인 시간 없음](https://user-images.githubusercontent.com/69797420/170821059-fe2057ea-78b9-41bd-b225-aea7569408e0.gif)![로그인 시간 있음](https://user-images.githubusercontent.com/69797420/170821062-1abff7a9-5fb2-4f05-88be-fee26f7c40b4.gif)
![예약하기 근본](https://user-images.githubusercontent.com/69797420/170821064-456e4904-4dda-485b-bb98-0d7b06afc110.gif)
![중복예약, 중복좌석 예외처리](https://user-images.githubusercontent.com/69797420/170821065-6d1ea298-a5b5-4a6a-9ccf-83de0a266e54.gif)
![회원가입](https://user-images.githubusercontent.com/69797420/170821067-77518d4a-3e5c-4f1d-a7b6-693353eee0b3.gif)


