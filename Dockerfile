# Stage 1: build WAR bằng Maven (Java 17)
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -B -DskipTests clean package

# Stage 2: chạy Tomcat 11 (Jakarta Servlet 6.x)
FROM tomcat:11.0-jdk17

# (Tuỳ chọn) Tắt shutdown port 8005 để gọn log
RUN sed -i 's/port="8005"/port="-1"/' /usr/local/tomcat/conf/server.xml

# Xoá app mặc định và deploy WAR vào ROOT (context '/')
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Render sẽ đặt biến môi trường PORT; cấu hình Tomcat nghe trên $PORT lúc khởi động
ENV PORT=8080
EXPOSE 8080
CMD bash -lc 'sed -i "s/port=\"8080\"/port=\"${PORT}\"/" /usr/local/tomcat/conf/server.xml && catalina.sh run'