FROM gradle:4.3-jdk-alpine

ADD --chown=gradle . /code

WORKDIR /code

EXPOSE 8080

CMD [ "gradle" , "clean" , "spring-boot:run" ]
