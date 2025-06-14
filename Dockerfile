# JDK 17 베이스 이미지 사용
FROM openjdk:17

# 애플리케이션 파일을 저장할 디렉토리 설정
WORKDIR /app

# 빌드된 jar 파일을 컨테이너로 복사
COPY target/api-yummy-0.0.1-SNAPSHOT.jar /app/api-yummy.jar

# 컨테이너가 시작될 때 실행될 명령어
ENTRYPOINT ["java", "-jar", "/app/api-yummy.jar"]