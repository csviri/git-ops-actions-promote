FROM openjdk:11
WORKDIR "~/build/"
COPY . .
RUN ./mvnw clean install -DskipTests

FROM openjdk:11
RUN mkdir "/runnable"
COPY --from=0 "~/build/target/promote-action.jar" "/runnable"
COPY entrypoint.sh /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]
