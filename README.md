# Something Wrong using sbt-assembly and ScalaPB

## Usage

```
# assembly the server
sbt assembly
# run the server
java -jar target/scala-2.12/scalapb-test-assembly-0.1.0-SNAPSHOT.jar
```

## Error

You will get the error following:
```
Exception in thread "main" io.grpc.ManagedChannelProvider$ProviderNotFoundException: No functional server found. Try adding a dependency on the grpc-netty or grpc-netty-shaded artifact
        at io.grpc.ServerProvider.provider(ServerProvider.java:55)
        at io.grpc.ServerBuilder.forPort(ServerBuilder.java:41)
        at com.ukonnra.scalapb.Server$.main(Server.scala:21)
        at com.ukonnra.scalapb.Server.main(Server.scala)
```