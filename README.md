# 싱글톤,팩토리,멀티쓰레드를 이용한 소켓 서버

작성자: 김형중, hjkim188@gscdn.com

## TL;DR

- 서버종류를 팩토리패턴으로 정의하여 Tpye설정에 따라 구분

- LogMgr를 싱글톤으로 구현

  ​

### 기능

- Type1 서버 :  랜덤으로 반복문 실행 후 종료되는 순서를 Log로 표시(멀티쓰레드여부 확인)
- Type2 서버 : get 접속시 index.html 리턴