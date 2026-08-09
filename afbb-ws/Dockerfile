FROM ghcr.io/graalvm/graalvm-community:25
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests=true -Dpackaging=native-image
ENTRYPOINT ["/app/target/afbb-ws"]
