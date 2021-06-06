#!/usr/bin/env sh
sam build -t sam.jvm.yaml
sam local start-api --template .aws-sam/build/template.yaml