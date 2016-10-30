# CognitiveGuru

Tags: Kotlin, Spring

## Setup

1. Rename application.properties.template to application.properties
2. Set appropriate values
3. Build docker image :

```sh
./gradlew build buildDocker
```

4. Run project:

```sh
docker run -i -t cognitiveguru:latest /bin/bash
```
