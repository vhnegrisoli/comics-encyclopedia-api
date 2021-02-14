FROM gradle:6.6.1-jdk11 AS build
COPY --chown=gradle:gradle . /usr/comics-encyclopedia-api/src
WORKDIR /usr/comics-encyclopedia-api/src
RUN gradle build -x test --no-daemon
EXPOSE 8080
CMD ["gradle", "bootRun"]