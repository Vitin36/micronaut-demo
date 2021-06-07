FROM 775846179576.dkr.ecr.us-east-1.amazonaws.com/graalvm AS builder
WORKDIR /home/app
COPY build/layers/libs /home/app/libs
COPY build/layers/resources /home/app/resources
COPY build/layers/application.jar /home/app/application.jar
RUN native-image --verbose -H:Class=io.micronaut.function.aws.runtime.MicronautLambdaRuntime -H:Name=application --no-fallback -cp /home/app/libs/*.jar:/home/app/resources:/home/app/application.jar
FROM 775846179576.dkr.ecr.us-east-1.amazonaws.com/graalvm
WORKDIR /function
COPY --from=builder /home/app/application /function/func
RUN echo "#!/bin/sh" >> bootstrap && echo "set -euo pipefail" >> bootstrap && echo "./func -Djava.library.path=$(pwd)" >> bootstrap
RUN chmod 777 bootstrap
RUN chmod 777 func
RUN zip -j function.zip bootstrap func
