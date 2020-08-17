# delivery-food

## Environment
- Java: JDK 11
- Spring Boot: 2.3.3.RELEASE
- MySQL: 8.0.17
- Gradle

## Service Architecture

## DB ERD

## 기능 요구사항

## 프로젝트 중요 요소
- 올바른 도메인 설계
    - 약한 의존성, 강한 응집도
- 아래의 프로그래밍 요구사항을 지키며 코드 작성
- 나쁜 냄새가 나는 코드에 대한 리팩토링
- 성능 최적화
    - 캐싱 전략
    - 인덱스와 쿼리 튜닝
    - DB 호출을 최소화
    - 비동기 프로그래밍을 활용

## 프로그래밍 요구사항
- 객체지향 5원칙을 지키면서 프로그래밍한다.
- 객체지향 생활 체조 원칙을 지키면서 프로그래밍한다.
- 사용자가 입력한 값에 대한 모든 예외 처리를 한다.
- 모든 로직에 단위 테스트를 구현한다.
- 객체지향 생활 체조 원칙
    - 규칙 1: 한 메서드에 오직 한 단계의 들여쓰기만 한다.
    - 규칙 2: else 예약어를 쓰지 않는다.
    - 규칙 3: 모든 원시값과 문자열을 포장한다.
    - 규칙 4: 한 줄에 점을 하나만 찍는다.
    - 규칙 5: 줄여쓰지 않는다(축약 금지).
    - 규칙 6: 모든 엔티티를 작게 유지한다.
    - 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
    - 규칙 8: 일급 콜렉션을 쓴다.
    - 규칙 9: 게터/세터/프로퍼티를 쓰지 않는다.
- 코딩 컨벤션
    - [Google Java Style Guide](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml)

## 브랜치 전략
- [우아한 형제들 기술 블로그: 우린 Git-flow를 사용하고 있어요](http://woowabros.github.io/experience/2017/10/30/baemin-mobile-git-branch-strategy.html)

## Test
