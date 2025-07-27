FROM ghcr.io/graalvm/graalvm-community:21 AS graalvm-builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests=true -Dpackaging=native-image

FROM scratch
COPY --from=graalvm-builder /app/target/afbb-ws afbb-ws
EXPOSE 8080
CMD ["afbb-ws"]